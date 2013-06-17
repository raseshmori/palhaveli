package org.palhaveli;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.palhaveli.db.DbManager;
import org.palhaveli.db.LoginDbHelper;

/**
 * Servlet implementation class LoginServ
 */
@WebServlet("/LoginServ")
public class LoginServ extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public LoginServ() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userName=request.getParameter("username");
		String password=request.getParameter("password");
		
		if(userName==null || userName.equals("") || password==null || password.equals("")){
			response.sendRedirect("login.jsp?errorMsg=1");
			return;
		}
		
		Connection connection=DbManager.getConnection();
		if(connection==null){
			response.sendRedirect("login.jsp?errorMsg=2");
			return;
		}
		
		LoginDbHelper loginDb=new LoginDbHelper(connection);
		boolean isValidLogin=loginDb.validateUser(userName, password);
		
		if(!isValidLogin){
			response.sendRedirect("login.jsp?errorMsg=1");
			return;
		}
		
		HttpSession session=request.getSession();
		session.setAttribute("user", userName);
		
		response.sendRedirect("HomeServ");
		return;
	}

}
