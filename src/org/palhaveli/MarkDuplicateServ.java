package org.palhaveli;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.palhaveli.db.DbManager;
import org.palhaveli.db.MemberDbHelper;

/**
 * Servlet implementation class MarkDuplicateServ
 */
@WebServlet("/MarkDuplicateServ")
public class MarkDuplicateServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MarkDuplicateServ() {
        super();
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
		Connection connection=DbManager.getConnection();
		MemberDbHelper memberDb=new MemberDbHelper(connection);
		if(memberDb.markDuplicate()){
			response.sendRedirect("markduplicate.jsp?errorMsg=1");
		}else{
			response.sendRedirect("markduplicate.jsp?errorMsg=2");
		}
	}

}
