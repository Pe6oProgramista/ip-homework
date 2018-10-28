package org.elsys.netprog.servlet;

import java.io.IOException;
import java.text.Normalizer.Form;
import java.util.HashMap;
import java.util.Map;

import javax.naming.spi.DirStateFactory.Result;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Servlet
 */
@WebServlet("/Servlet")
public class Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public Servlet() {
        // TODO Auto-generated constructor stub
    }
    
    private HashMap<String, String> key_values = new HashMap<>();
    private String form = "<!DOCTYPE html>\r\n" + 
    		"<html>\r\n" + 
    		"  <head>\r\n" + 
    		"    <title>Servlet</title>\r\n" + 
    		"  </head>\r\n" + 
    		"  <body>\r\n" + 
    		"    <form action=\"Servlet\" method=\"post\">\r\n" + 
    		"      Key: <input type=\"text\" name=\"key\"><br>\r\n" + 
    		"      Value: <input type=\"text\" name=\"value\"><br>\r\n" + 
    		"      <input type=\"submit\" value=\"Submit\">\r\n" + 
    		"    </form> \r\n" +
    		"	 <p style=\"border-bottom: 1px solid black;\">Key  -  Value</p><br>\r\n";
    
    


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String result = "";
		for(Map.Entry<String, String> entry : key_values.entrySet()) {
			result += "<p>" + entry.getKey() + " - " + entry.getValue() + "</p><br>\r\n";
		}
		response.getWriter().write(form + result + "</body></html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		key_values.put(request.getParameter("key"), request.getParameter("value"));
		doGet(request, response);
	}

}
