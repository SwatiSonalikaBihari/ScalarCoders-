package com.jsp.jdbctest;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.GenericServlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/validateuser")
public class LogInValidationCheckWithDataBase  extends HttpServlet{

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email=req.getParameter("email");
		String password=req.getParameter("password");
		System.out.println(email+" "+password);
		try {
			Connection con=GetJDBC_ConnectionObject.getConnection();
			PreparedStatement ps=con.prepareStatement("select * from student where email=? and password=? ");
			ps.setString(1, email);
			ps.setString(2, password);
			ResultSet rs=ps.executeQuery();
			if(rs.next())
			{
				Cookie cookie1=new Cookie("email", email);
				Cookie cookie2=new Cookie("password", password);
				cookie1.setMaxAge(3600);
				cookie2.setMaxAge(3600);
				resp.addCookie(cookie2);
				resp.addCookie(cookie1);
				RequestDispatcher rd=req.getRequestDispatcher("/sucessfullogin.jsp");
				rd.forward(req, resp);
				
			}
			else
			{
				RequestDispatcher rd=req.getRequestDispatcher("/failedlogin.jsp");
				rd.forward(req, resp);
			}
			
		} catch (Exception e) {
			System.out.println(e);


		}
		
	}

	

}
