package sessionbeans;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entity.Korisnik;

@Stateless
@Remote(Init.class)
public class InitBean implements Init {

	@PersistenceContext(unitName = "RestoranReservationApp")
	EntityManager em;
	
	public void init() {
		Korisnik korisnik = new Korisnik("Admin", "Admin", "admin@admin.com", "admin", 1, 1, true);
		em.persist(korisnik);
	}
}
