package jp.co.netdev.worktimemng.business.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.co.netdev.worktimemng.business.model.Datacheck;
import jp.co.netdev.worktimemng.dao.MembersDAO;

/**
 * 各種処理ロジックの設定、画面遷移を司るクラス。
 * Servlet implementation class Workmain.
 * @author yoshi.
 * @version 1.0
 */
@WebServlet("/Workmain")
public class Workmain extends HttpServlet {
	/**
	 * シリアルバージョンUID。
	 */
	private static final long serialVersionUID = 1L;

    /**
     * 上位クラスのコンストラクタを呼び出す。
     * @see HttpServlet#HttpServlet()
     */
    public Workmain() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request,
	 * 									 HttpServletResponse response)
	 * @param request リクエストオブジェクト。
	 * @param response レスポンスオブジェクト。
	 * @throws javax.servlet.ServletException サーブレット内の何らかのエラーが発生した場合。
	 * @throws java.io.IOException 入出力処理でエラーが発生した場合。
	 */
	protected void doGet(HttpServletRequest request, 
			HttpServletResponse response)throws ServletException, IOException {
		// TODO Auto-generated method stub
		//ログアウト処理
		String flg1 = (String) request.getParameter("logoutflg");
		int flgnum;
		if (flg1 != null) {
			flgnum = Integer.parseInt(flg1);
			if (flgnum == 1) {
				//セッション有無確認し、あればセッションを破棄
				HttpSession session = request.getSession(false);
				if (session != null) {
					session.invalidate();
				}
				//ログイン画面へフォワード
				RequestDispatcher rd = request.
						getRequestDispatcher("./index.jsp");
				rd.forward(request, response);
			}
		}

		//メインメニュー画面へフォワードする処理
		String flg2 = (String) request.getParameter("menuflg");
		if (flg2 != null) {
			flgnum = Integer.parseInt(flg2);
			if (flgnum == 1) {
				RequestDispatcher rd = request.
						getRequestDispatcher("./menu.jsp");
				rd.forward(request, response);
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request,
	 * 										 HttpServletResponse response)
	 * @param request リクエストオブジェクト。
	 * @param response レスポンスオブジェクト。
	 * @throws javax.servlet.ServletException サーブレット内の何らかのエラーが発生した場合。
	 * @throws java.io.IOException 入出力処理でエラーが発生した場合。
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//beansのインスタンスを取得
		Datacheck beans = new Datacheck();
		MembersDAO memersdao = new MembersDAO();

		//セッションオブジェクトの取得
		HttpSession session = request.getSession(true);

		//フォームからたデータを取得して変数に格納
		String scode = (String) request.getParameter("scode");
		String password = (String) request.getParameter("passwd");

		//SQL実行結果の格納用
		int result = 0;

		//フォームから受け取ったデータをbeansに保存
		beans.setScode(scode);
		beans.setPasswd(password);

		//社員コードとパスワードの空チェック
		beans.checkvalues();

		//IDとパスワードが入力されていればDBとデータを照合する
		if (beans.getErrmsg() == null) {
			try {
				//DBに接続
				memersdao.getConnection();

				//ユーザ情報をDBに問い合わせ、結果を格納する
				result = memersdao.memberSerch(scode, password);

			} catch (Exception e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
		}

		//セッションにbeansオブジェクトを保存
		session.setAttribute("beans", beans);

		//ユーザがDBに登録されているか確認
		if (result > 0 && beans.getErrmsg() == null) {
			//サーブレットからメニュー画面へフォワード
			RequestDispatcher rd = request.getRequestDispatcher("./menu.jsp");
			rd.forward(request, response);
		} else if (result == 0 && beans.getErrmsg() == null) {
			//社員コードかパスワードが間違っていたら、エラーメッセージを格納してログイン画面に戻す。
			beans.setErrmsg("IDまたはパスワードが違います。");
			RequestDispatcher rd = request.getRequestDispatcher("./index.jsp");
			rd.forward(request, response);
		} else if (beans.getErrmsg() != null) {
			//beans側でエラーメッセージが格納されていれば、ログイン画面にそのまま戻す
			RequestDispatcher rd = request.getRequestDispatcher("./index.jsp");
			rd.forward(request, response);
		}
	}
}
