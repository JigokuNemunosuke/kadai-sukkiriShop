package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Account;
import model.RegisterUserLogic;

/**
 * Servlet implementation class RegisterUser
 */
@WebServlet("/RegisterUser")
public class RegisterUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterUserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//フォワード先変数宣言
		String forwardPath = null;
		
		//actionの値をリクエストパラメータから取得
		String action = request.getParameter("action");
		
		//action処理分岐
		if (action == null) {
			//フォワード先を登録画面に設定
			forwardPath = "/WEB-INF/jsp/registerForm.jsp";
			
			//「登録」リクエストの処理
		} else if (action.equals("done")) {
			//セッションスコープに保存されたユーザー情報を取得
			HttpSession session = request.getSession();
			Account account = (Account) session.getAttribute("account");
				
			//登録処理の呼び出し
			RegisterUserLogic bo = new RegisterUserLogic();
			boolean result = bo.execute(account);
			
			//登録可否
			if (result) {				
			//不要になったセッションスコープ内のユーザー情報インスタンスを削除
			session.removeAttribute("account");
				
			//フォワード先を登録完了画面に設定
			forwardPath = "/WEB-INF/jsp/registerDone.jsp";
			}
			
			//forwardPathの設定先へフォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher(forwardPath);
			dispatcher.forward(request, response);
		} else {
			//登録済みエラー
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//リクエストパラメータを取得
		request.setCharacterEncoding("UTF-8");
		String userId = request.getParameter("userId");
		String pass = request.getParameter("pass");
		String mail = request.getParameter("mail");
		String name = request.getParameter("name");
		int age;
	    String tmp = request.getParameter("age");
	    if (tmp == null || tmp.length() == 0) {
	      age = -1;
	    } else {
	      try {
	        age = Integer.parseInt(tmp);
	      } catch (NumberFormatException e) {
	        age = -1;
	      }
	    }
		
		//登録するユーザー情報を設定
		Account account = new Account(userId, pass, mail, name, age);
		
		//セッションスコープにユーザー情報を保存
		HttpSession session = request.getSession();
		session.setAttribute("account", account);
		
		//登録確認画面にフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/registerConfirm.jsp");
		dispatcher.forward(request, response);
	}
}