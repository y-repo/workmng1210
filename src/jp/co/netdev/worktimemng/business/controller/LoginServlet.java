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
import jp.co.netdev.worktimemng.business.service.LoginService;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet(description = "ログインに関する処理を行う", urlPatterns = { "/LoginServlet" })
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// DTOクラスのインスタンス取得
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
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//セッションオブジェクト取得
		HttpSession session = request.getSession(true);

		//各クラスのインスタンス取得
		WorkDTO dto = new WorkDTO();
		LoginService service = new LoginService(dto);

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
			RequestDispatcher rd = request.getRequestDispatcher("./MenuServlet");
			rd.forward(request, response);

		//ユーザ情報がDB情報と合致しなかった場合、または正しくユーザ情報が入力されなかった場合
		} else {
			//ログイン画面へフォワード
			RequestDispatcher rd = request.getRequestDispatcher("./index.jsp");
			rd.forward(request, response);
		}
	}
}
