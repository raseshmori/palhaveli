package org.palhaveli.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.palhaveli.model.Group;
import org.palhaveli.model.Member;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

public class MemberDbHelper {
	private Connection connection;
	
	public MemberDbHelper(Connection connection){
		this.connection=connection;
	}
	
	public int addMember(Member member){
		try{
			Statement statement=connection.createStatement();
			String sql="INSERT INTO vaishnav(first_name, middle_name, last_name, flat_apt, apartment, landmark, road, region, city, state, country, pincode, mobile, home_no, email_id, caste, home_town, profession, family_members, brahmsambandh, thakorji, is_duplicate) VALUES(" +
					"'"+member.getFirstName()+"',"+
					"'"+member.getMiddleName()+"',"+
					"'"+member.getLastName()+"',"+
					"'"+member.getFlatApt()+"',"+
					"'"+member.getApartment()+"',"+
					"'"+member.getLandmark()+"',"+
					"'"+member.getRoad()+"',"+
					"'"+member.getRegion()+"',"+
					"'"+member.getCity()+"',"+
					"'"+member.getState()+"',"+
					"'"+member.getCountry()+"',"+
					member.getPincode()+","+
					member.getMobile()+","+
					member.getHome()+","+
					"'"+member.getEmail()+"',"+
					"'"+member.getCaste()+"',"+
					"'"+member.getHomeTown()+"',"+
					"'"+member.getProfession()+"',"+
					member.getFamilyMembers()+","+
					(member.isBramsambandh()?1:0)+","+
					(member.isThakorji()?1:0)+","+
					(member.isDuplicate()?1:0)+");";
			
			System.out.println("addMember: "+sql);
			
			statement.executeUpdate(sql);
			
		}catch(Exception e){
			e.printStackTrace();
			return -1;
		}
		
		int memberId=getMemberId(member);
		
		if(memberId == -1)
			return -1;		// Member details not entered.
		
		if(insertGroupMembership(memberId, member.getGroupIds())){
			return 0;		// Success
		}
		
		return 1;			// Group membership not successful
	}
	
	public int updateMember(Member member){
		int memberId=getMemberId(member);
		try{
			
			member.setMemberId(String.valueOf(memberId));
			
			Statement statement=connection.createStatement();
			String sql="UPDATE vaishnav SET " +
					"first_name='"+member.getFirstName()+"',"+
					"middle_name='"+member.getMiddleName()+"',"+
					"last_name='"+member.getLastName()+"',"+
					"flat_apt='"+member.getFlatApt()+"',"+
					"apartment='"+member.getApartment()+"',"+
					"landmark='"+member.getLandmark()+"',"+
					"road='"+member.getRoad()+"',"+
					"region='"+member.getRegion()+"',"+
					"city='"+member.getCity()+"',"+
					"state='"+member.getState()+"',"+
					"country='"+member.getCountry()+"',"+
					"pincode="+member.getPincode()+","+
					"mobile="+member.getMobile()+","+
					"home_no="+member.getHome()+","+
					"email_id='"+member.getEmail()+"',"+
					"caste='"+member.getCaste()+"',"+
					"home_town='"+member.getHomeTown()+"',"+
					"profession='"+member.getProfession()+"',"+
					"family_members="+member.getFamilyMembers()+","+
					"brahmsambandh="+(member.isBramsambandh()?1:0)+","+
					"thakorji="+(member.isThakorji()?1:0)+" WHERE id="+member.getMemberId()+";";
			
			System.out.println("update Member: "+sql);
			
			statement.executeUpdate(sql);
			
		}catch(Exception e){
			e.printStackTrace();
			return -1;
		}				
		
		if(memberId == -1)
			return -1;		// Member details not entered.
		
		if(updateGroupMembership(memberId, member.getGroupIds())){
			return 0;		// Success
		}
		
		return 1;			// Group membership not successful
	}
	
	public boolean insertGroupMembership(int memberId, List<String> groupIds){
		try{
			Statement statement=connection.createStatement();
			String sql;
			
			for(String groupId : groupIds){
				sql="INSERT INTO group_vaishnav(vaishnav_id, group_id) VALUES("+memberId+","+groupId+")";
				System.out.println("insertGroupMembership: "+sql);
				statement.executeUpdate(sql);
			}
			
			return true;
		}catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean updateGroupMembership(int memberId, List<String> groupIds){
		try{
			Statement statement=connection.createStatement();
			String sql;
			
			GroupDbHelper groupDb=new GroupDbHelper(connection);
			List<Group> groups=groupDb.getGroups();
			
			for(Group group : groups){
				String groupId=group.getGroupId();
				if(groupIds.contains(groupId)){
					try{
						sql="INSERT INTO group_vaishnav(vaishnav_id, group_id) VALUES("+memberId+","+groupId+")";
						System.out.println("updateGroupMembership: "+sql);
						statement.executeUpdate(sql);
					}catch(MySQLIntegrityConstraintViolationException e){
						System.out.println("Already a member of group: "+groupId);
					}
				}else{
					try{
						sql="DELETE FROM group_vaishnav WHERE vaishnav_id="+memberId+" AND group_id="+groupId+";";
						System.out.println("deleteGroupMembership: "+sql);
						statement.executeUpdate(sql);
					}catch(MySQLIntegrityConstraintViolationException e){
						System.out.println("Was never a member of group: "+groupId);
					}
				}
			}
			
			return true;
		}catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}
	
	private Member setDetails(Member member){
		if(member.getApartment().equals("NA"))
			member.setApartment("");
		
		if(member.getCaste().equals("NA"))
			member.setCaste("");
		
		if(member.getCity().equals("NA"))
			member.setCity("");
		
		if(member.getCountry().equals("NA"))
			member.setCountry("");
		
		if(member.getEmail().equals("NA"))
			member.setEmail("");
		
		if(member.getFamilyMembers().equals("0"))
			member.setFamilyMembers("");
		
		if(member.getFirstName().equals("NA"))
			member.setFirstName("");
		
		if(member.getFlatApt().equals("NA"))
			member.setFlatApt("");
		
		if(member.getHome().equals("0"))
			member.setHome("");
		
		if(member.getHomeTown().equals("NA"))
			member.setHomeTown("");
		
		if(member.getLandmark().equals("NA"))
			member.setLandmark("");
		
		if(member.getLastName().equals("NA"))
			member.setLastName("");
		
		if(member.getMiddleName().equals("NA"))
			member.setMiddleName("");
		
		if(member.getMobile().equals("0"))
			member.setMobile("");
		
		if(member.getPincode().equals("0"))
			member.setPincode("");
		
		if(member.getProfession().equals("NA"))
			member.setProfession("");
		
		if(member.getRegion().equals("NA"))
			member.setRegion("");
		
		if(member.getRoad().equals("NA"))
			member.setRoad("");
		
		if(member.getState().equals("NA"))
			member.setState("");
		
		return member;
	}
	
	public int getMemberId(Member member){
		try{
			Statement statement=connection.createStatement();
			String sql="SELECT id FROM vaishnav WHERE first_name='"+member.getFirstName()+"' AND " +
													"last_name='"+member.getLastName()+"' AND " +
													"flat_apt='"+member.getFlatApt()+"' AND " +
													"landmark='"+member.getLandmark()+"' AND " +
													"road='"+member.getRoad()+"' AND " +
													"region='"+member.getRegion()+"' AND " +
													"city='"+member.getCity()+"' AND " +
													"state='"+member.getState()+"' AND " +
													"country='"+member.getCountry()+"';";
			
			System.out.println("getMemberId: "+sql);
			
			ResultSet resultSet=statement.executeQuery(sql);
			
			if(resultSet.next()){
				return resultSet.getInt("id");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return -1;
	}
	
	public List<Member> getMemberDetails(List<Integer> groupIds, boolean includeOthers){
		
		List<Member> memberDetails=new ArrayList<Member>();
		
		try{
			Statement statement=connection.createStatement();
			
			StringBuffer sql=new StringBuffer();
			
			if(groupIds != null && groupIds.size()>0 || includeOthers){
				sql.append("SELECT * FROM group_vaishnav WHERE group_id IN (");
				for(Integer groupId : groupIds){
					sql.append(groupId+",");
				}
				sql.replace(sql.length()-1, sql.length(), "");
				sql.append(") ORDER BY vaishnav_id;");
				
				StringBuffer vaishnavIdString=new StringBuffer();
				ResultSet resultSet=null;
				HashMap<Integer, Member> members=new HashMap<Integer, Member>();
				
				if(groupIds != null && groupIds.size()>0){
					System.out.println("sql: "+sql);
					
					resultSet=statement.executeQuery(sql.toString());																				
					
					while(resultSet.next()){
						int id=resultSet.getInt("vaishnav_id");
						Member member=members.get(id);
						
						if(member == null){
							member=new Member();
							member.setMemberId(String.valueOf(id));
							vaishnavIdString.append(id+",");
						}
						
						member.addGroupId(resultSet.getString("group_id"));
						
						members.put(id, member);
					}
					
					System.out.println("MemberDbHelper: getMemberDetails: vaishnavIdString: "+vaishnavIdString);
					
					if(vaishnavIdString.length()>0)
						vaishnavIdString.replace(vaishnavIdString.length()-1, vaishnavIdString.length(), "");
				}	
				sql=new StringBuffer();
				sql.append("SELECT * FROM vaishnav");
				
				if(vaishnavIdString.length()==0 && !includeOthers)
					return memberDetails;
				
				sql.append(" WHERE id IN ("+vaishnavIdString+")");
				
				if(includeOthers && groupIds!=null && groupIds.size()>0){
					sql.append(" OR id NOT IN ( SELECT vaishnav_id FROM group_vaishnav )");
				}else if(includeOthers){
					sql=new StringBuffer();
					sql.append(" SELECT * FROM vaishnav WHERE id NOT IN ( SELECT vaishnav_id FROM group_vaishnav )");
				}
				
				System.out.println("Member details sql: "+sql.toString());
				
				resultSet=statement.executeQuery(sql.toString());
				
				while(resultSet.next()){
					String memberId=resultSet.getString("id");
					Member member=members.get(Integer.parseInt(memberId));
					if(member == null){
						member=new Member();
						member.setGroupIds(null);
					}
					member.setMemberId(memberId);
					member.setApartment(resultSet.getString("apartment"));
					member.setBramsambandh(resultSet.getBoolean("brahmsambandh"));
					member.setCaste(resultSet.getString("caste"));
					member.setCity(resultSet.getString("city"));
					member.setCountry(resultSet.getString("country"));
					member.setDuplicate(resultSet.getBoolean("is_duplicate"));
					member.setEmail(resultSet.getString("email_id"));
					member.setFamilyMembers(resultSet.getString("family_members"));
					member.setFirstName(resultSet.getString("first_name"));
					member.setFlatApt(resultSet.getString("flat_apt"));
					member.setHome(resultSet.getString("home_no"));
					member.setHomeTown(resultSet.getString("home_town"));
					member.setLandmark(resultSet.getString("landmark"));
					member.setLastName(resultSet.getString("last_name"));
					member.setMiddleName(resultSet.getString("middle_name"));
					member.setMobile(resultSet.getString("mobile"));
					member.setPincode(resultSet.getString("pincode"));
					member.setProfession(resultSet.getString("profession"));
					member.setRegion(resultSet.getString("region"));
					member.setRoad(resultSet.getString("road"));
					member.setState(resultSet.getString("state"));
					member.setThakorji(resultSet.getBoolean("thakorji"));
					
					member=setDetails(member);
					
					memberDetails.add(member);
				}
			}else{
				sql.append("SELECT * FROM vaishnav");
				
				System.out.println("Member Details sql: "+sql.toString());
				
				ResultSet resultSet=statement.executeQuery(sql.toString());
				
				while(resultSet.next()){
					String memberId=resultSet.getString("id");
					Member member=new Member();
					member.setMemberId(memberId);
					member.setApartment(resultSet.getString("apartment"));
					member.setBramsambandh(resultSet.getBoolean("brahmsambandh"));
					member.setCaste(resultSet.getString("caste"));
					member.setCity(resultSet.getString("city"));
					member.setCountry(resultSet.getString("country"));
					member.setDuplicate(resultSet.getBoolean("is_duplicate"));
					member.setEmail(resultSet.getString("email_id"));
					member.setFamilyMembers(resultSet.getString("family_members"));
					member.setFirstName(resultSet.getString("first_name"));
					member.setFlatApt(resultSet.getString("flat_apt"));
					member.setHome(resultSet.getString("home_no"));
					member.setHomeTown(resultSet.getString("home_town"));
					member.setLandmark(resultSet.getString("landmark"));
					member.setLastName(resultSet.getString("last_name"));
					member.setMiddleName(resultSet.getString("middle_name"));
					member.setMobile(resultSet.getString("mobile"));
					member.setPincode(resultSet.getString("pincode"));
					member.setProfession(resultSet.getString("profession"));
					member.setRegion(resultSet.getString("region"));
					member.setRoad(resultSet.getString("road"));
					member.setState(resultSet.getString("state"));
					member.setThakorji(resultSet.getBoolean("thakorji"));
					
					String groupSql="SELECT group_id FROM group_vaishnav WHERE vaishnav_id="+member.getMemberId();
					
					Statement groupStatement= connection.createStatement();
					ResultSet groupResult=groupStatement.executeQuery(groupSql);
					while(groupResult.next()){
						member.addGroupId(groupResult.getString("group_id"));
					}
					
					memberDetails.add(member);
				}
			}
			
			return memberDetails;
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return null;
	}
	
	public boolean markDuplicate(){
		try{
			Statement statement=connection.createStatement();
			String sql="select distinct vo.id from vaishnav vo, vaishnav vi where vo.id != vi.id and vo.flat_apt=vi.flat_apt and vo.last_name=vi.last_name and vo.apartment=vi.apartment and vo.id not in ("+
						"select distinct vo.id from vaishnav vo, vaishnav vi where vo.id != vi.id and vo.flat_apt=vi.flat_apt and vo.last_name=vi.last_name and vo.apartment=vi.apartment group by vo.flat_apt, vo.last_name, vo.apartment);";
			
			System.out.println("Executing for mark duplicate: "+sql);
			
			ResultSet resultSet=statement.executeQuery(sql);
			
			StringBuffer duplicateIds=new StringBuffer();
			while(resultSet.next()){
				duplicateIds.append(resultSet.getString(1)+",");
			}
			
			if(duplicateIds.toString().equals(""))
				return true;
			
			duplicateIds.replace(duplicateIds.length()-1, duplicateIds.length(), "");
			
			sql="UPDATE vaishnav SET is_duplicate=1 WHERE id in ("+duplicateIds+");";
			
			System.out.println("Marking duplicates: "+sql);
			
			if(statement.executeUpdate(sql) > 0)
				return true;
			
			return false;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}		
	}
	
	public List<Member> getPrintDetails(String idsToPrint){
		List<Member> addresses=new ArrayList<Member>();
		
		try{
			Statement statement=connection.createStatement();
			String sql="SELECT first_name, middle_name, last_name, flat_apt, apartment, landmark, road, region, city, pincode FROM vaishnav WHERE id IN ("+idsToPrint+") AND is_duplicate=0;";
			
			ResultSet resultSet=statement.executeQuery(sql);
			
			StringBuffer buff=new StringBuffer();
			
			while(resultSet.next()){
				buff.replace(0, buff.length(), "");
				String firstName=resultSet.getString("first_name");
				String lastName=resultSet.getString("last_name");
				
				String name=(firstName==null || firstName.equals("") || firstName.equals("NA"))?" ":firstName+" ";
				//name+=(middleName==null || middleName.equals("") || middleName.equals("NA"))?" ":middleName+" ";
				name+=(lastName==null || lastName.equals("") || lastName.equals("NA"))?"":lastName;
				
				String flatNo=resultSet.getString("flat_apt");
				String apartment=resultSet.getString("apartment");
				String landmark=resultSet.getString("landmark");
				String road=resultSet.getString("road");
				String region=resultSet.getString("region");
				String city=resultSet.getString("city");
				String pincode=resultSet.getString("pincode");
				
				flatNo=(flatNo==null || flatNo.equals("") || flatNo.equals("NA"))?"":flatNo+",";
				apartment=(apartment==null || apartment.equals("") || apartment.equals("NA"))?"":apartment;
				landmark=(landmark==null || landmark.equals("") || landmark.equals("NA"))?"":landmark;
				road=(road==null || road.equals("") || road.equals("NA"))?"":road+",";
				region=(region==null || region.equals("") || region.equals("NA"))?"":region;
				city=(city==null || city.equals("") || city.equals("NA"))?"":city+"-";
				pincode=(pincode==null || pincode.equals("") || pincode.equals("0"))?"":pincode;
				
				Member member=new Member();
				member.setFirstName(name);
				member.setFlatApt(flatNo);
				member.setApartment(apartment);
				member.setLandmark(landmark);
				member.setRoad(road);
				member.setRegion(region);
				member.setCity(city);
				member.setPincode(pincode);
				
				addresses.add(member);
			}
			
			return addresses;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	public Member getMemberDetails(String memberId){
		Member member=new Member();
		
		try{
			Statement statement=connection.createStatement();
			String sql="SELECT * FROM vaishnav WHERE id="+memberId;
			
			ResultSet resultSet=statement.executeQuery(sql);
			
			if(resultSet.next()){
				member.setMemberId(resultSet.getString("id"));
				member.setApartment(resultSet.getString("apartment"));
				member.setBramsambandh(resultSet.getBoolean("brahmsambandh"));
				member.setCaste(resultSet.getString("caste"));
				member.setCity(resultSet.getString("city"));
				member.setCountry(resultSet.getString("country"));
				member.setDuplicate(resultSet.getBoolean("is_duplicate"));
				member.setEmail(resultSet.getString("email_id"));
				member.setFamilyMembers(resultSet.getString("family_members"));
				member.setFirstName(resultSet.getString("first_name"));
				member.setFlatApt(resultSet.getString("flat_apt"));
				member.setHome(resultSet.getString("home_no"));
				member.setHomeTown(resultSet.getString("home_town"));
				member.setLandmark(resultSet.getString("landmark"));
				member.setLastName(resultSet.getString("last_name"));
				member.setMiddleName(resultSet.getString("middle_name"));
				member.setMobile(resultSet.getString("mobile"));
				member.setPincode(resultSet.getString("pincode"));
				member.setProfession(resultSet.getString("profession"));
				member.setRegion(resultSet.getString("region"));
				member.setRoad(resultSet.getString("road"));
				member.setState(resultSet.getString("state"));
				member.setThakorji(resultSet.getBoolean("thakorji"));
				
				String groupSql="SELECT group_id FROM group_vaishnav WHERE vaishnav_id="+member.getMemberId();
				
				Statement groupStatement= connection.createStatement();
				ResultSet groupResult=groupStatement.executeQuery(groupSql);
				while(groupResult.next()){
					member.addGroupId(groupResult.getString("group_id"));
				}
			}
			
			return member;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean deleteMember(String memberId){
		try{
			Statement statement=connection.createStatement();
			String sql="DELETE FROM group_vaishnav WHERE vaishnav_id="+memberId+";";
			System.out.println("deleteMember: group membership: "+sql);
			statement.executeUpdate(sql);
			
			sql="DELETE FROM vaishnav WHERE id="+memberId+";";
			System.out.println("deleteMember: member: "+sql);
			statement.executeUpdate(sql);
			return true;
		}catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}
}
