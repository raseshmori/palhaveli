package org.palhaveli;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
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
 * Servlet implementation class MemberServ
 */
@WebServlet("/MemberServ")
public class MemberServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberServ() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	private boolean validateInput(String firstName, String middleName, String lastName, String flatapt, String apartment, String landmark, String road, String region, String city, String state, String country, String mobile, String home, String email, String caste, String hometown, String profession, String members, String pincode, String brahmsambandh, String thakorji){
		if(firstName==null || firstName.length()==0)
			return false;
		
		if(middleName==null || middleName.length()==0)
			return false;
		
		if(lastName==null || lastName.length()==0)
			return false;
		
		if(flatapt==null || flatapt.length()==0)
			return false;
		
		if(apartment==null || apartment.length()==0)
			return false;
		
		if(landmark==null || landmark.length()==0)
			return false;
		
		if(road==null || road.length()==0)
			return false;
		
		if(region==null || region.length()==0)
			return false;
		
		if(city==null || city.length()==0)
			return false;
		
		if(state==null || state.length()==0)
			return false;
		
		if(country==null || country.length()==0)
			return false;
		
		if(mobile==null || mobile.length()==0)
			return false;
		
		if(email==null || email.length()==0)
			return false;
		
		if(caste==null || caste.length()==0)
			return false;
		
		if(home==null || home.length()==0)
			return true;
		
		if(hometown==null || hometown.length()==0)
			return true;
		
		if(profession==null || profession.length()==0)
			return false;
		
		if(members==null || members.length()==0)
			return false;
		
		if(pincode==null || pincode.length()==0)
			return false;
		
		if(brahmsambandh==null || brahmsambandh.length()==0)
			return false;
		
		if(thakorji==null || thakorji.length()==0)
			return false;
		
		return true;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String operation=request.getParameter("operation");
		
		if(operation==null || operation.length()==0){
			response.sendRedirect("home.jsp");
			return;
		}else if(operation.equalsIgnoreCase("add") || operation.equalsIgnoreCase("edit")){
			String firstName=request.getParameter("firstname");
			String middleName=request.getParameter("middlename");
			String lastName=request.getParameter("lastname");
			String flatapt=request.getParameter("flatapt");
			String apartment= request.getParameter("apartment");
			String landmark=request.getParameter("landmark");
			String road=request.getParameter("road");
			String region=request.getParameter("region");
			String city=request.getParameter("city");
			String state=request.getParameter("state");
			String country=request.getParameter("country");
			String mobile=request.getParameter("mobile");
			String home=request.getParameter("home");
			String email=request.getParameter("email");
			String caste=request.getParameter("caste");
			String hometown=request.getParameter("hometown");
			String profession=request.getParameter("profession");
			String members=request.getParameter("familymembers");
			String pincode=request.getParameter("pincode");
			
			if(!validateInput(firstName, middleName, lastName, flatapt, apartment, landmark, road, region, city, state, country, mobile, home, email, caste, hometown, profession, members, pincode, request.getParameter("brahmsambandh"), request.getParameter("thakorji"))){
				response.sendRedirect("addmember.jsp?errorMsg=4");
				return;
			}
			
			boolean brahmsambandh=request.getParameter("brahmsambandh").equals("yes");
			boolean thakorji=request.getParameter("thakorji").equals("yes");
			
			Connection connection=DbManager.getConnection();
			GroupDbHelper groupDb=new GroupDbHelper(connection);
			
			List<Group> groups=groupDb.getGroups();
			List<String> groupIds=new ArrayList<String>();
			
			for(Group group : groups){
				if(request.getParameter(group.getGroupId()) != null)
					groupIds.add(group.getGroupId());
			}
			
			Member member=new Member();
			member.setBramsambandh(brahmsambandh);
			member.setCaste(caste);
			member.setCity(city);
			member.setCountry(country);
			member.setDuplicate(false);
			member.setEmail(email);
			member.setFamilyMembers(members);
			member.setFirstName(firstName);
			member.setFlatApt(flatapt);
			member.setGroupIds(groupIds);
			member.setHome(home);
			member.setHomeTown(hometown);
			member.setLandmark(landmark);
			member.setLastName(lastName);
			member.setMobile(mobile);
			member.setPincode(pincode);
			member.setProfession(profession);
			member.setRegion(region);
			member.setRoad(road);
			member.setState(state);
			member.setThakorji(thakorji);
			member.setApartment(apartment);
			member.setMiddleName(middleName);
			
			MemberDbHelper memberDb=new MemberDbHelper(connection);
			
			int retValue=-2;
			
			if(operation.equalsIgnoreCase("add"))
				retValue=memberDb.addMember(member);
			else
				retValue=memberDb.updateMember(member);
			
			if(retValue==-1){
				response.sendRedirect("addmember.jsp?errorMsg=1");
				return;
			}else if(retValue==0){
				response.sendRedirect("addmember.jsp?errorMsg=2");
				return;
			}else if(retValue==1){
				response.sendRedirect("addmember.jsp?errorMsg=3");
				return;
			}
		}else if(operation.equals("delete")){
			Connection connection=DbManager.getConnection();
			MemberDbHelper memberDb=new MemberDbHelper(connection);
			String memberId=request.getParameter("memberid");
			
			GroupDbHelper groupDb=new GroupDbHelper(connection);
			List<Group> groups=groupDb.getGroups();
			request.setAttribute("groups", groups);			
			
			if(memberDb.deleteMember(memberId)){
				RequestDispatcher requestDispatcher=getServletContext().getRequestDispatcher("/home.jsp?errorMsg=3");
				requestDispatcher.forward(request, response);
				return;
			}
			else{
				RequestDispatcher requestDispatcher=getServletContext().getRequestDispatcher("/home.jsp?errorMsg=4");
				requestDispatcher.forward(request, response);
				return;
			}
		}
	}

}
