package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sessionbeans.KorisnikDaoLocal;
import sessionbeans.PrijateljiDaoBean;
import sessionbeans.PrijateljiDaoLocal;

import com.fasterxml.jackson.databind.ObjectMapper;

import entity.Korisnik;
import entity.Prijatelji;


/**
 * Servlet implementation class UserAccServlet
 */
public class UserAccServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@EJB
	private KorisnikDaoLocal korisnikDao;
	
	@EJB
	private PrijateljiDaoLocal prijateljiDao;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserAccServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Korisnik k = (Korisnik) request.getSession().getAttribute("korisnik");
		if(k != null) {
			String query = "select k2 from Prijatelji p, Korisnik k, Korisnik k2 where k2.id = p.id_drugog and p.id_prvog = k.id and k.id = " + k.getId();
			
			List<Prijatelji> list = prijateljiDao.findBy(query);
			request.getSession().setAttribute("prijatelji", list);
		}
		getServletContext().getRequestDispatcher("/details.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		String ime = request.getParameter("ime");
		String prezime = request.getParameter("prezime");
		String password = request.getParameter("password");
		
		System.out.println(ime + " " + password + prezime);
		
		Korisnik k = (Korisnik) request.getSession().getAttribute("korisnik");
		if(ime != null) {
			k.setImeKorisnika(ime);	
		}
		
		if(password != null) {
			k.setLozinkaKorisnika(password);	
		}
		
		if(prezime != null) {
			k.setPrezimeKorisnika(prezime);	
		}
        
		Korisnik izmenjenK = korisnikDao.merge(k);
		System.out.println(izmenjenK.getImeKorisnika());
		boolean success = true;
		
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
