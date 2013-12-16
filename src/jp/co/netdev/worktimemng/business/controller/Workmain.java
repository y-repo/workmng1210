package jp.co.netdev.worktimemng.business.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.co.netdev.worktimemng.business.model.WorkDTO;
import jp.co.netdev.worktimemng.business.service.ConnectDB;
import jp.co.netdev.worktimemng.business.service.DataCheck;
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

		//セッションオブジェクトの取得
		HttpSession session = request.getSession(true);

		//各クラスのインスタンスを取得
		WorkDTO dto = new WorkDTO();
		MembersDAO membersdao = new MembersDAO();
		DataCheck datacheck = new DataCheck();
		ConnectDB connection = new ConnectDB();

		//社員コードとパスワードを取得し、DTOに格納
		dto.setScode((String) request.getParameter("scode"));
		dto.setPasswd((String) request.getParameter("passwd"));

		//社員コードとパスワードの空チェック
		datacheck.checkvalues(dto.getScode(),dto.getPasswd());

		//IDとパスワードが入力されていればDBとデータを照合する
		if (dto.getErrmsg() == null) {
			try {
				//DBに接続
				connection.getConnection();

				//ユーザ情報をDBに問い合わせ、結果を格納する
				dto.setResult(membersdao.memberSerch(dto.getScode(), dto.getPasswd()));

			} catch (Exception e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
		}

		//セッションにdtoを格納
		session.setAttribute("dto", dto);

		//ユーザがDBに登録されているか確認
		if (dto.getResult() > 0 && dto.getErrmsg() == null) {
			//サーブレットからメニュー画面へフォワード
			RequestDispatcher rd = request.getRequestDispatcher("./menu.jsp");
			rd.forward(request, response);
		} else if (dto.getResult() == 0 && dto.getErrmsg() == null) {
			//社員コードかパスワードが間違っていたら、エラーメッセージを格納してログイン画面に戻す。
			dto.setErrmsg("IDまたはパスワードが違います。");
			RequestDispatcher rd = request.getRequestDispatcher("./index.jsp");
			rd.forward(request, response);
		} else if (dto.getErrmsg() != null) {
			//beans側でエラーメッセージが格納されていれば、ログイン画面にそのまま戻す
			RequestDispatcher rd = request.getRequestDispatcher("./index.jsp");
			rd.forward(request, response);
		}
	}
}
