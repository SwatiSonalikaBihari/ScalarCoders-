package com.jsp.jdbctest;

import java.io.IOException;

import javax.servlet.GenericServlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;


@WebServlet("/register")
public class RegisterServlet extends GenericServlet {

	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		System.out.println("register servlet");
		RequestDispatcher rd= req.getRequestDispatcher("/register.jsp");
		rd.forward(req, res);
		
	}

	

}
