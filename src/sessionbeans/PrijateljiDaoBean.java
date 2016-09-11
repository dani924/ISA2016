package sessionbeans;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.Query;

import entity.Korisnik;
import entity.Prijatelji;


@Stateless
@Local(PrijateljiDaoLocal.class)
public class PrijateljiDaoBean extends GenericDaoBean<Prijatelji, Integer>
       implements PrijateljiDaoLocal{

	@Override
	public boolean removeFromFriends(Integer id_prvog, String email) {
		
		Query qp = em.createQuery("select k from Korisnik k where k.korisnickoImeKorisnika = '" + email + "'");
		Korisnik k = (Korisnik) qp.getSingleResult();
		
		Query q = em.createQuery("delete from Prijatelji p where p.id_prvog = " + id_prvog + " and p.id_drugog = " + k.getId());
		int deletedCount =  q.executeUpdate();
		if(deletedCount != 0) {
			return true;
		} else
		return false;
	}

	@Override
	public List<Korisnik> searchFriends(String ime, String prezime) {
		
		System.out.println("pozvani su " + ime + " i " + prezime);
		
		String q = "select k from Korisnik k where ";
		
		if(!ime.equals("") && !prezime.equals("")) {
			q = q + " k.imeKorisnika = '" + ime + "' and k.prezimeKorisnika = '" + prezime + "'";
		} else 
		if(!ime.equals("")) {
			q = q + " k.imeKorisnika = '" + ime + "'";
		} else 
		if(!prezime.equals("")) {
			q = q + " k.prezimeKorisnika = '" + prezime + "'";
		}
		
		Query qp = em.createQuery(q);
		List<Korisnik> listKorisnik = (List<Korisnik>) qp.getResultList();
		
		return listKorisnik;
	}

	@Override
	public boolean addToFriends(Integer id_prvog, String email) {
		
		Query qp = em.createQuery("select k from Korisnik k where k.korisnickoImeKorisnika = '" + email + "'");
		Korisnik k = (Korisnik) qp.getSingleResult();
		
		Prijatelji p = new Prijatelji(id_prvog, k.getId());
		
		em.persist(p);
		
		return true;
	}
    
	
}
