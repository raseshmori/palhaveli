package org.palhaveli;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.palhaveli.db.DbManager;
import org.palhaveli.db.GroupDbHelper;

/**
 * Servlet implementation class DeleteGroup
 */
@WebServlet("/GroupServ")
public class GroupServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GroupServ() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String groupId=request.getParameter("groupid");
		if(groupId==null || groupId.equals("")){
			response.sendRedirect("HomeServ");
			return;
		}
		
		Connection connection=DbManager.getConnection();
		try{
			GroupDbHelper groupDb=new GroupDbHelper(connection);
			groupDb.deleteGroup(groupId);
		}catch(Exception e){
			e.printStackTrace();
		}
		response.sendRedirect("HomeServ");
		return;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String groupName=request.getParameter("groupname");
		if(groupName==null || groupName.equals("")){
			response.sendRedirect("addgroup.jsp?errorMsg=1");
			return;
		}
		
		Connection connection=DbManager.getConnection();
		try{
			GroupDbHelper groupDb=new GroupDbHelper(connection);
			groupDb.addGroup(groupName);
		}catch(Exception e){
			e.printStackTrace();
			response.sendRedirect("addgroup.jsp?errorMsg=2");
			return;
		}
		response.sendRedirect("HomeServ");
		return;
	}

}
