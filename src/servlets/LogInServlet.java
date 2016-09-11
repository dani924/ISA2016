package servlets;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.persistence.NoResultException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import entity.Korisnik;
import entity.Restoran;
import sessionbeans.KorisnikDaoLocal;
import sessionbeans.RestoranDaoLocal;

/**
 * Servlet implementation class LogInServlet
 */
public class LogInServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private static Logger log = Logger.getLogger(LogInServlet.class);

	@EJB
	private KorisnikDaoLocal korisnikDao;
	
	@EJB
	private RestoranDaoLocal restoranDao;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogInServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		try {
			super.init(config);
			
		} catch (ServletException e) {
			e.printStackTrace();
		}
	}
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
		try {
			String korisnickoIme = request.getParameter("korisnickoIme");
			String lozinka = request.getParameter("lozinka");
			
			System.out.println(korisnickoIme + "   " + lozinka);
			
			if ((korisnickoIme == null) || (korisnickoIme.equals("")) || (lozinka == null) || (lozinka.equals(""))) {
				response.sendRedirect(response.encodeRedirectURL("./login.jsp"));
				return;
			}
			
			Korisnik korisnik = korisnikDao.findKorisnikSaKorisnickimImenomILozinkom(korisnickoIme, lozinka);
			System.out.println(korisnik.toString());
			/*if(korisnik.isAutentificated() == false) {
				response.sendRedirect(response.encodeRedirectURL("./login.jsp"));
				return;
			}*/
			
			if (korisnik != null) {	
				HttpSession session = request.getSession(true);
				session.setAttribute("korisnik", korisnik);
				log.info("Korisnik " + korisnik.getKorisnickoImeKorisnika() + " se prijavio.");
				
				List<Restoran> list = restoranDao.findAll();
				session.setAttribute("restoraniHome", list);
				getServletContext().getRequestDispatcher("/home.jsp").forward(request, response);
			}
			
		} catch (EJBException e) {
			if (e.getCause().getClass().equals(NoResultException.class)) {
				response.sendRedirect(response.encodeRedirectURL("./login.jsp"));
			} else {
				throw e;
			}
		} catch (ServletException e) {
			log.error(e);
			throw e;
		} catch (IOException e) {
			log.error(e);
			throw e;
		}
	}

}
