package org.palhaveli.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.palhaveli.model.Group;

public class GroupDbHelper {
	private Connection connection;
	
	public GroupDbHelper(Connection connection){
		this.connection=connection;
	}
	
	public List<Group> getGroups(){
		List<Group> groups=new ArrayList<Group>();
		
		try{
			Statement statement=connection.createStatement();
			String sql="SELECT * FROM group_master;";
			ResultSet resultSet=statement.executeQuery(sql);
			
			while(resultSet.next()){
				Group group=new Group();
				group.setGroupId(resultSet.getString("group_id"));
				group.setGroupName(resultSet.getString("group_name"));
				
				groups.add(group);
			}
			
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
		
		return groups;
	}
	
	public void deleteGroup(String groupId){
		try{
			Statement statement=connection.createStatement();
			
			String sql="DELETE FROM group_vaishnav WHERE group_id="+groupId+";";
			System.out.println("Delete group membership details: "+sql);
			statement.executeUpdate(sql);
			
			sql="DELETE FROM group_master WHERE group_id="+groupId+";";
			System.out.println("Delete group: "+sql);
			statement.executeUpdate(sql);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void addGroup(String groupName){
		try{
			Statement statement=connection.createStatement();
			String sql="INSERT INTO group_master(group_name) VALUES ('"+groupName+"');";
			
			statement.executeUpdate(sql);			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
