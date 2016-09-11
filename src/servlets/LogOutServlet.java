package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

public class LogOutServlet extends HttpServlet {

	private static final long serialVersionUID = -9042476143848933537L;
	
	private static Logger log = Logger.getLogger(LogOutServlet.class);

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {
			
			HttpSession session = request.getSession();
			session.invalidate();
			response.sendRedirect(response.encodeRedirectURL("./login.jsp"));
			//getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
		
		} catch (IOException e) { //| ServletException e) {
			log.error(e);
		}
	}

	protected void doPost(HttpServletRequest request, 	HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
