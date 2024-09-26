package com.jsp.jdbctest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;


public class DemoJdbcTest {

	public static void main(String[] args) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306","root", "root");
			Statement st=con.createStatement();
			st.execute("create database hyscaler");
			con.close();
			System.out.println("database created");
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
