package org.palhaveli;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.palhaveli.db.DbManager;
import org.palhaveli.db.GroupDbHelper;
import org.palhaveli.db.MemberDbHelper;
import org.palhaveli.model.Group;
import org.palhaveli.model.Member;

/**
 * Servlet implementation class GetEditMember
 */
@WebServlet("/GetEditMember")
public class GetEditMember extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetEditMember() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String memberId=request.getParameter("memberid");
		if(memberId==null){
			response.sendRedirect("home.jsp");
			return;
		}else{
			Connection connection=DbManager.getConnection();
			MemberDbHelper memberDb=new MemberDbHelper(connection);
			Member member=memberDb.getMemberDetails(memberId);
			
			request.setAttribute("member", member);
			
			GroupDbHelper groupDb=new GroupDbHelper(connection);
			List<Group> groups=groupDb.getGroups();
			
			request.setAttribute("groups", groups);
			
			ServletContext context = getServletContext();
			RequestDispatcher requestDispatcher = context.getRequestDispatcher("/editmember.jsp");
			requestDispatcher.forward(request, response);
			return;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
