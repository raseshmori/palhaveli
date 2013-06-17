package org.palhaveli;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

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
 * Servlet implementation class GetMembers
 */
@WebServlet("/GetMembers")
public class GetMembers extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetMembers() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean isAllMembers=request.getParameter("all").equals("on")?true:false;
		boolean includeOthers=(request.getParameter("nogroup") != null && request.getParameter("nogroup").equals("on"))?true:false;				
		
		Connection connection=DbManager.getConnection();
		MemberDbHelper memberDb=new MemberDbHelper(connection);
		
		List<Member> memberDetails=null;
		
		if(isAllMembers){
			memberDetails=memberDb.getMemberDetails(null, false);
		}else{			
			GroupDbHelper groupDb=new GroupDbHelper(connection);
			List<Group> groups=groupDb.getGroups();
			
			List<Integer> groupIds=new ArrayList<Integer>();
			
			for(Group group : groups){
				String paramValue=request.getParameter(group.getGroupId());
				if(paramValue != null && paramValue.equalsIgnoreCase("on"))
					groupIds.add(Integer.parseInt(group.getGroupId()));
			}
			
			memberDetails=memberDb.getMemberDetails(groupIds, includeOthers);
			
		}
		
		request.setAttribute("members", memberDetails);
		getServletContext().getRequestDispatcher("/showmembers.jsp").forward(request, response);
		return;
	}

}
