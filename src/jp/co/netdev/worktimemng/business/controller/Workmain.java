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
import jp.co.netdev.worktimemng.business.service.WorkService;

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
		// 各クラスのインスタンス取得
		WorkDTO dto = new WorkDTO();

		//ログアウト処理
		dto.setF1((String) request.getParameter("logoutflg"));
		if (dto.getF1() != null) {
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

		//メインメニュー画面へフォワードする処理
		dto.setF2((String) request.getParameter("menuflg"));
		if (dto.getF2() != null) {
				RequestDispatcher rd = request.
						getRequestDispatcher("./menu.jsp");
				rd.forward(request, response);
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

		//セッションオブジェクト取得
		HttpSession session = request.getSession(true);

		//各クラスのインスタンス取得
		WorkDTO dto = new WorkDTO();
		WorkService service = new WorkService();

		//社員コードとパスワードをDTOに保存
		dto.setScode((String) request.getParameter("scode"));
		dto.setPasswd((String) request.getParameter("passwd"));

		//社員コードとパスワードのチェック
		try {
			service.checkdata(dto.getScode(),dto.getPasswd());
		} catch (Exception e) {
			e.printStackTrace();
		}

		//セッションにdtoを格納
		session.setAttribute("dto", dto);

		//ユーザ情報がDB情報と合致した場合
		if (dto.getResult() > 0 && dto.getErrmsg() == null) {
			//メニュー画面へフォワード
			RequestDispatcher rd = request.getRequestDispatcher("./menu.jsp");
			rd.forward(request, response);

		//ユーザ情報がDB情報と合致しなかった場合、または正しくユーザ情報が入力されなかった場合
		} else {
			//ログイン画面へフォワード
			RequestDispatcher rd = request.getRequestDispatcher("./index.jsp");
			rd.forward(request, response);
		}
	}
}
