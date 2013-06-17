package org.palhaveli.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class LoginDbHelper {
	private Connection connection;
	
	public LoginDbHelper(Connection connection){
		this.connection=connection;
	}
	
	public boolean validateUser(String userName, String password){
		try{
			Statement statement=connection.createStatement();
			String sql="SELECT * FROM user_master WHERE user_name='"+userName.trim()+"' AND user_pass='"+password.trim()+"';";
			ResultSet resultSet=statement.executeQuery(sql);
			
			if(resultSet.next())
				return true;
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}
}
