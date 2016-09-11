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

import entity.Korisnik;
import entity.Restoran;
import entity.Sto;
import sessionbeans.KorisnikDaoLocal;
import sessionbeans.RestoranDaoLocal;
import sessionbeans.StoDaoLocal;

/**
 * Servlet implementation class RestoranMenadzerServlet
 */
public class RestoranMenadzerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	@EJB
	private RestoranDaoLocal restoranDao;
	
	@EJB
	private KorisnikDaoLocal korisnikDao;
	
	@EJB
	private StoDaoLocal stoDao;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RestoranMenadzerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Korisnik k = (Korisnik) request.getSession().getAttribute("korisnik");
		Restoran r = null;
		if(k.getRestoranKorisnik() != null) {
		    r = restoranDao.findById(k.getRestoranKorisnik());	
		}
		
		request.getSession().setAttribute("restoran", r);
		
		if(request.getParameter("izbor").equals("restoran")) {
			getServletContext().getRequestDispatcher("/Restoran.jsp").forward(request, response);
		} else if(request.getParameter("izbor").equals("jelovnik")) {
			getServletContext().getRequestDispatcher("/Jelovnik.jsp").forward(request, response);
		} else if(request.getParameter("izbor").equals("konfiguracija")) {
			getServletContext().getRequestDispatcher("/Konfiguracija.jsp").forward(request, response);
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
			System.out.println(naziv);
			String sq = "SELECT r FROM Restoran r WHERE r.naziv = '" + naziv + "'";
			List<Restoran> listRestoran = restoranDao.findBy(sq);
			System.out.println(sq);
			System.out.println("ve;" + listRestoran.size());
			Restoran r = listRestoran.get(0);
		    System.out.println(r.getId());
			r.setAdresa(adresa);
		    r.setNaziv(naziv);
		    r.setVrsta(vrsta);
			
			restoranDao.merge(r);
		    
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
		} else if(request.getParameter("konf") != null) {
			request.setCharacterEncoding("UTF-8");
			Integer h = Integer.parseInt(request.getParameter("h"));
			Integer v = Integer.parseInt(request.getParameter("v"));
			
			System.out.println(h + " " + v);
		    
			Korisnik k = (Korisnik) request.getSession().getAttribute("korisnik");
			Restoran r = restoranDao.findById(k.getRestoranKorisnik());
			System.out.println(r.getNaziv());
			
			stoDao.removeTables(r);
			
			Sto sto = new Sto();
			for(int i = 1; i <= h; i++) {
				for(int j = 1; j <= v; j++) {
					sto = new Sto(i , j, r);
					stoDao.merge(sto);
				}
			}
			
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
