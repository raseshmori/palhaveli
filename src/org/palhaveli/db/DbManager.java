package org.palhaveli.db;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbManager {
	private static Connection connection=null;		
	
	private static boolean connect(){
		try{
			Class.forName("com.mysql.jdbc.Driver");
			connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/palhaveli","root","rootuser");			
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public static Connection getConnection(){
		try{
			if(connection==null || connection.isClosed()){
				if(!connect())
					return null;
			}
		}catch(Exception e){
			return null;
		}
		return connection;
	}
}
