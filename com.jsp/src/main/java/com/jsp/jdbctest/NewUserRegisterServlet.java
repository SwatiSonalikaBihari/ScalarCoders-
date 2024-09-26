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

import com.mysql.cj.xdevapi.PreparableStatement;

@WebServlet("/registerservlet")
public class NewUserRegisterServlet extends GenericServlet {

	

	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		String name=req.getParameter("name");
		String email=req.getParameter("email");
		String mobile=req.getParameter("mobile");
		String password=req.getParameter("password");
		System.out.println(name+" "+email+" "+mobile+" "+password);
		System.out.println("before try block");
		boolean b=checkAvailability(email, mobile);
		if(b)
		{
			RequestDispatcher rd=req.getRequestDispatcher("/alreadypresent.jsp");
			rd.forward(req, res);
		}
		
			try {
				System.out.println("try block");
				Connection con=GetJDBC_ConnectionObject.getConnection();
				PreparedStatement ps=con.prepareStatement("insert into student values(?,?,?,?)");
				ps.setString(1,name);
				ps.setString(2, email);
				ps.setString(3, mobile);
				ps.setString(4, password);
				ps.executeUpdate();
				con.close();
				RequestDispatcher rd=req.getRequestDispatcher("/index.jsp");
				rd.forward(req, res);

			} catch (Exception e) {
				// TODO: handle exception
			}
		
		
		
	}
	
	private boolean checkAvailability(String email,String phone)
	{
		try {
			Connection con=GetJDBC_ConnectionObject.getConnection();
			PreparedStatement ps=con.prepareStatement("select * from student where email=? or phone=? ");
			ps.setString(1, email);
			ps.setString(2, phone);
			ResultSet rs=ps.executeQuery();
			return rs.next();
		} catch (Exception e) {
			System.out.println(e);

return false;
		}
	}
	

}

//Connection con1=GetJDBC_ConnectionObject.getConnection();
//PreparedStatement ps1= con1.prepareStatement("select * from student where email= ? or mobile =?");
//ps1.setString(1, email);
//ps1.setString(2, mobile);
//ResultSet rs=ps1.executeQuery();
//
//System.out.println(rs.next());
//RequestDispatcher rd=req.getRequestDispatcher("/index.jsp");
//rd.forward(req, res);
