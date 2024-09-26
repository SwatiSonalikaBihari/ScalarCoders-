package com.jsp.jdbctest;

import java.io.IOException;

import javax.servlet.GenericServlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;


@WebServlet("/login")
public class LogInVerificationServlet extends GenericServlet {

	public LogInVerificationServlet() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		System.out.println("log in validation");
		RequestDispatcher rd= req.getRequestDispatcher("/login.jsp");
		rd.forward(req, res);
		
	}

}
