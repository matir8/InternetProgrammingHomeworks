package org.elsys.netprog.servlet;

import java.io.IOException;
import java.util.HashMap;
import javax.servlet.ServletConfig;
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
    ServletConfig config = null;

    private HashMap<String, String> db = new HashMap<>();

    /**
     * Default constructor. 
     */
    public Servlet() {
        // TODO Auto-generated constructor stub

    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");

		response.getOutputStream().println("<html><body>");

		response.getOutputStream().println("<ul>");
		for (String key : db.keySet()) {
			response.getOutputStream().println(String.format("<li>%s : %s</li>", key, db.get(key)));
		}
		response.getOutputStream().println("</ul>");

		response.getOutputStream().println(
			"<form method='POST'>" +
				"<input type='text' name='key' placeholder='Key' />" +
				"<input type='text' name='value' placeholder='Value' /> " +
				"<input type='submit' />" +
			"</form>"
		);

		response.getOutputStream().println("</body></html>");
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		String key = request.getParameter("key");
		String value = request.getParameter("value");

		if (db.containsKey(key)) {
			response.getOutputStream().println("<html><body><h1>DB already contains record with the same key</h1></body></html>");
		} else if (key.isEmpty() || value.isEmpty()) {
			response.getOutputStream().println("<html><body><h1>Key or value can not be empty</h1></body></html>");
		} else {
			db.put(key, value);
			response.sendRedirect("/Servlet");
		}
	}

}
