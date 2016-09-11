package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sessionbeans.PrijateljiDaoLocal;

import com.fasterxml.jackson.databind.ObjectMapper;

import entity.Korisnik;

/**
 * Servlet implementation class FriendServlet
 */
public class FriendServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	@EJB
	private PrijateljiDaoLocal prijateljiDao;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FriendServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		if(request.getParameter("ukloni") != null) {
			String email = request.getParameter("email");
			
			System.out.println(email);
			
			Korisnik k = (Korisnik) request.getSession().getAttribute("korisnik");
			
			boolean success = true;
			
			success = prijateljiDao.removeFromFriends(k.getId(), email);
			
			ObjectMapper mapper = new ObjectMapper();
			PrintWriter out = response.getWriter();
			
			if(success == true) {
				String s = mapper.writeValueAsString("true");
				response.setContentType("application/json; charset=UTF-8");
				out.write(s);
			} else {
				String s = mapper.writeValueAsString("false");
				response.setContentType("application/json; charset=UTF-8");
				out.write(s);
			}  	
		} else if(request.getParameter("pretraga")!= null ) {
			String ime = request.getParameter("ime");
			String prezime = request.getParameter("prezime");
			
			boolean success = true;
			
			List<Korisnik> list = prijateljiDao.searchFriends(ime, prezime);
			
			for (Korisnik korisnik : list) {
				System.out.println(korisnik.getImeKorisnika());
			}
			
			ObjectMapper mapper = new ObjectMapper();
			PrintWriter out = response.getWriter();
			
			if(success == true) {
				String s = mapper.writeValueAsString(list);
				response.setContentType("application/json; charset=UTF-8");
				out.write(s);
			} else {
				String s = mapper.writeValueAsString("false");
				response.setContentType("application/json; charset=UTF-8");
				out.write(s);
			}
		} else if(request.getParameter("add") != null) {
			String email = request.getParameter("email");
			
			System.out.println(email);
			
			Korisnik k = (Korisnik) request.getSession().getAttribute("korisnik");
			
			boolean success = true;
			
			success = prijateljiDao.addToFriends(k.getId(), email);
			
			ObjectMapper mapper = new ObjectMapper();
			PrintWriter out = response.getWriter();
			
			if(success == true) {
				String s = mapper.writeValueAsString("true");
				response.setContentType("application/json; charset=UTF-8");
				out.write(s);
			} else {
				String s = mapper.writeValueAsString("false");
				response.setContentType("application/json; charset=UTF-8");
				out.write(s);
			}  	
		}
		
	}
}
