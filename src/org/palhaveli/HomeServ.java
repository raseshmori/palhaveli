package org.palhaveli;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.palhaveli.db.DbManager;
import org.palhaveli.db.GroupDbHelper;
import org.palhaveli.model.Group;

/**
 * Servlet implementation class HomeServ
 */
@WebServlet("/HomeServ")
public class HomeServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HomeServ() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) {
		Connection connection=DbManager.getConnection();
		
		try{
			if(connection==null){
				response.sendRedirect("home.jsp?errorMsg=1");
				return;
			}
			
			GroupDbHelper groupDb=new GroupDbHelper(connection);
			List<Group> groups=groupDb.getGroups();
			
			request.setAttribute("groups", groups);
			getServletContext().getRequestDispatcher("/home.jsp").forward(request, response);
			return;
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
