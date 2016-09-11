package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Properties;

import javax.ejb.EJB;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sessionbeans.KorisnikDaoLocal;

import com.fasterxml.jackson.databind.ObjectMapper;

import entity.Korisnik;

/**
 * Servlet implementation class RegisterServlet
 */
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	@EJB
	private KorisnikDaoLocal korisnikDao;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getParameter("confirm") != null) {
			Integer id = Integer.parseInt(request.getParameter("id"));
			
			Korisnik k = korisnikDao.findById(id);
			k.setAutentificated(true);
			korisnikDao.merge(k);
			getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getParameter("register") != null) {
			request.setCharacterEncoding("UTF-8");
			String ime = request.getParameter("ime");
			String prezime = request.getParameter("prezime");
			String password = request.getParameter("password");
			String email = request.getParameter("email");
			Integer uloga = Integer.parseInt(request.getParameter("uloga"));
			
			Korisnik k = new Korisnik();
			k.setImeKorisnika(ime);
			k.setKorisnickoImeKorisnika(email);
			k.setLozinkaKorisnika(password);
			k.setPrezimeKorisnika(prezime);
			k.setUlogaKorisnika(uloga);
			k.setRestoranKorisnik(null);
			k.setAutentificated(false);
			
			korisnikDao.persist(k);
			
			Properties props = new Properties();
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.socketFactory.port", "465");
			props.put("mail.smtp.socketFactory.class",
					"javax.net.ssl.SSLSocketFactory");
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.port", "465");

			Session session = Session.getDefaultInstance(props,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication("io145236987@gmail.com", "145236987");
					}
				});

			try {
				Message message = new MimeMessage(session);
				message.setFrom(new InternetAddress("io145236987@gmail.com"));
				message.setRecipients(Message.RecipientType.TO,
						InternetAddress.parse(k.getKorisnickoImeKorisnika()));
				message.setSubject("Potvrda registracije");
				try {
					message.setText("Postovani," +
							"\n\n klikom na sledeci link potvrdicete vas nalog na ResroranReservationApp " + 
							 "http://localhost:8080/RestoranReservationApp/RegisterServlet?confirm=true&id=" + k.getId());
				} catch (MessagingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				Transport.send(message);
			} catch (MessagingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}finally {}
		}
				
		/*try{

	        Properties props = new Properties();
	        props.put("mail.smtp.host", "smtp.mail.yahoo.com"); // for gmail use smtp.gmail.com
	        props.put("mail.smtp.auth", "true");
	        props.put("mail.debug", "true"); 
	        props.put("mail.smtp.starttls.enable", "true");
	        props.put("mail.smtp.port", "465");
	        props.put("mail.smtp.socketFactory.port", "465");
	        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
	        props.put("mail.smtp.socketFactory.fallback", "false");

	        Session mailSession = Session.getInstance(props, new javax.mail.Authenticator() {

	            protected PasswordAuthentication getPasswordAuthentication() {
	                return new PasswordAuthentication("user2546@yahoo.com", "123654987");
	            }
	        });

	        mailSession.setDebug(true); // Enable the debug mode

	        Message msg = new MimeMessage( mailSession );

	        //--[ Set the FROM, TO, DATE and SUBJECT fields
	        msg.setFrom( new InternetAddress( "user2546@yahoo.com" ) );
	        msg.setRecipients( Message.RecipientType.TO,InternetAddress.parse("sekulic07@gmail.com") );
	        msg.setSentDate( new Date());
	        msg.setSubject( "Hello World!" );

	        //--[ Create the body of the mail
	        msg.setText( "Hello from my first e-mail sent with JavaMail" );

	        //--[ Ask the Transport class to send our mail message
	        Transport.send( msg );

	    }catch(Exception E){
	        System.out.println( "Oops something has gone pearshaped!");
	        System.out.println( E );
	    }
		*/
		
		/*Properties props = new Properties();
		Session session = Session.getDefaultInstance(props, null);

		javax.mail.Message msg = new MimeMessage(session);
		try {
			msg.setFrom(new InternetAddress("jiluj@polyfaust.com"));
			msg.setRecipients(RecipientType.TO, InternetAddress.parse("lepiya@polyfaust.com"));
			msg.setSubject("Test");
			msg.setText("This is a text message sent using JMS!");
			msg.setSentDate(new Date());
			
			// Sends the message
			Transport.send(msg);
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		
		/*
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");

		Session session = Session.getDefaultInstance(props,
			new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication("io145236987@gmail.com", "145236987");
				}
			});

		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("io145236987@gmail.com"));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse("jiluj@polyfaust.com"));
			message.setSubject("Testing Subject");
			try {
				message.setText("Dear Mail Crawler," +
						"\n\n No spam to my email, please!");
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			Transport.send(message);
		} catch (MessagingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}finally {}
		*/
		
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
