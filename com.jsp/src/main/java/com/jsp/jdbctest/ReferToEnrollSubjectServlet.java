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

@WebServlet("/enroll")
public class ReferToEnrollSubjectServlet extends HttpServlet {

	public ReferToEnrollSubjectServlet() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("Enroll form");
		String subject=req.getParameter("subject");
		System.out.println(subject);
		try {
			Connection con=GetJDBC_ConnectionObject.getConnection();
			PreparedStatement ps=con.prepareStatement("select * from course where course_name=? ");
			ps.setString(1, subject);
			ResultSet rs=ps.executeQuery();
			rs.next();
			int id=rs.getInt(1);
			System.out.println(id);
			Cookie[] cookies = req.getCookies();
			String email=null;
			String password=null;
			for(Cookie c:cookies)
			{
				String cname=c.getName();
				if(cname.equals("email")) email=c.getValue();
				if(cname.equals("password")) password=c.getValue();
			}
			System.out.println("email : "+email);
			System.out.println("password : " +password);
			
			
			boolean b=checkErollment(email, password, id);
			System.out.println(b);
			if(!b)
			{
				addEnroll(email, password, id,req,resp);
				RequestDispatcher rd=req.getRequestDispatcher("/sucessenroll.jsp");
				rd.forward(req, resp);
			}
			else
			{
				RequestDispatcher rd=req.getRequestDispatcher("/alreadylogin.jsp");
				rd.forward(req, resp);
			}
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		
		
	}
	
	private boolean checkErollment(String email,String password,int id)
	{
		try {
			Connection con=GetJDBC_ConnectionObject.getConnection();
			PreparedStatement ps=con.prepareStatement("select * from enrollment where email=? and password=? and "
					+ "course_id=?");
			ps.setString(1,email);
			ps.setString(2, password);
			ps.setInt(3, id);
			ResultSet rs=ps.executeQuery();
			return rs.next();
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
	}
	
	private void addEnroll(String email,String password,int courseid, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		try {
			Connection con2=GetJDBC_ConnectionObject.getConnection();
			PreparedStatement ps1=con2.prepareStatement("insert into enrollment "
					+ "value (?,?,?)");
			ps1.setString(1, email);
			ps1.setString(2, password);
			ps1.setInt(3, courseid);
			ps1.executeUpdate();
			con2.close();
		} catch (Exception e) {
			System.out.println(e);
			RequestDispatcher rd=req.getRequestDispatcher("/failedenroll.jsp");
			rd.forward(req, resp);
		}
		//RequestDispatcher rd=req.getRequestDispatcher("/sucessenroll.jsp");
		
	}

}
