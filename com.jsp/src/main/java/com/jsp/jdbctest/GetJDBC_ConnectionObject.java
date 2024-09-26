package com.jsp.jdbctest;

import java.sql.Connection;
import java.sql.DriverManager;


public class GetJDBC_ConnectionObject {

	
		public static Connection getConnection()
		{
			Connection con=null;
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hyscaler","root", "root");
		
			} catch (Exception e) {
				System.out.println("Exception occur");
			}
			return con;
		
	}

}
