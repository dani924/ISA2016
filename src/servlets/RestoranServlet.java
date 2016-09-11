package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import sessionbeans.KorisnikDaoLocal;
import sessionbeans.RestoranDaoLocal;
import entity.Korisnik;
import entity.Restoran;


/**
 * Servlet implementation class RestoranServlet
 */
public class RestoranServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	@EJB
	private RestoranDaoLocal restoranDao;
	
	@EJB
	private KorisnikDaoLocal korisnikDao;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RestoranServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("postavi") != null) {
		    Integer id = Integer.parseInt(request.getParameter("id"));
		    Restoran r = restoranDao.findById(id);
		    request.getSession().setAttribute("restoran", r);
		    
		    List<Korisnik> list = korisnikDao.findBy("SELECT k FROM Korisnik k WHERE k.ulogaKorisnika=2");
		    request.getSession().setAttribute("menadzeri", list);
		    
		    getServletContext().getRequestDispatcher("/PostaviMenadzer.jsp").forward(request, response);
		} else if (request.getParameter("detalji") != null){
			Integer id = Integer.parseInt(request.getParameter("id"));
			Restoran r = restoranDao.findById(id);
			request.getSession().setAttribute("restoranDetalji", r);
			getServletContext().getRequestDispatcher("/RestoranDetalji.jsp").forward(request, response);
		} if(request.getParameter("rezervisi") != null) {
		    Integer id = Integer.parseInt(request.getParameter("id"));
		    Restoran r = restoranDao.findById(id);
		    request.getSession().setAttribute("restoranRezervacija", r);
		    
		    getServletContext().getRequestDispatcher("/RezervacijaVreme.jsp").forward(request, response);
		} 
		else {
			List<Restoran> listRestoran = restoranDao.findAll();
		    request.getSession().setAttribute("restorani", listRestoran);
			getServletContext().getRequestDispatcher("/Restorani.jsp").forward(request, response);	
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("dodaj") != null) {
			request.setCharacterEncoding("UTF-8");
			String naziv = request.getParameter("naziv");
			String vrsta = request.getParameter("vrsta");
			String adresa = request.getParameter("adresa");
			
			Restoran r = new Restoran(naziv, vrsta, adresa);
		    restoranDao.persist(r);
		    
		    ObjectMapper mapper = new ObjectMapper();
			PrintWriter out = response.getWriter();
			
			boolean success = true;
			if(success == true) {
				String s = mapper.writeValueAsString("true");
				response.setContentType("application/json; charset=UTF-8");
				out.write(s);
			} else {
				String s = mapper.writeValueAsString("false");
				response.setContentType("application/json; charset=UTF-8");
				out.write(s);
			}	
		} else if(request.getParameter("postavi") != null) {
			request.setCharacterEncoding("UTF-8");
			String email = request.getParameter("email");
			List<Korisnik> list = korisnikDao.findBy("SELECT k FROM Korisnik k WHERE k.korisnickoImeKorisnika='" + email + "'");
			
			Restoran r = (Restoran) request.getSession().getAttribute("restoran");
			
			list.get(0).setRestoranKorisnik(r.getId());
			
			korisnikDao.merge(list.get(0));
			
			ObjectMapper mapper = new ObjectMapper();
			PrintWriter out = response.getWriter();
			
			boolean success = true;
			if(success == true) {
				String s = mapper.writeValueAsString("true");
				response.setContentType("application/json; charset=UTF-8");
				out.write(s);
			} else {
				String s = mapper.writeValueAsString("false");
				response.setContentType("application/json; charset=UTF-8");
				out.write(s);
			}
		} else if(request.getParameter("sort") != null) {
			request.setCharacterEncoding("UTF-8");
			String param = request.getParameter("param");
			
			List<Restoran> list = restoranDao.findBy("SELECT r FROM Restoran r ORDER BY r." + param);
		    
		    ObjectMapper mapper = new ObjectMapper();
			PrintWriter out = response.getWriter();
			
			boolean success = true;
			if(success == true) {
				String s = mapper.writeValueAsString(list);
				response.setContentType("application/json; charset=UTF-8");
				out.write(s);
			} else {
				String s = mapper.writeValueAsString("false");
				response.setContentType("application/json; charset=UTF-8");
				out.write(s);
			}	
		} else if(request.getParameter("rezervisi") != null) {
			request.setCharacterEncoding("UTF-8");
			String datum = request.getParameter("datum");
			String vreme = request.getParameter("vreme");
			String trajanje = request.getParameter("trajanje");
			
			
		    ObjectMapper mapper = new ObjectMapper();
			PrintWriter out = response.getWriter();
			
			boolean success = true;
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
