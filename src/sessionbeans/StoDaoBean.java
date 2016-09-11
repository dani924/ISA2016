package sessionbeans;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.Query;

import entity.Korisnik;
import entity.Restoran;
import entity.Sto;

@Stateless
@Local(StoDaoLocal.class)
public class StoDaoBean extends GenericDaoBean<Sto, Integer> implements
		StoDaoLocal {

	@Override
	public boolean removeTables(Restoran r) {
		Integer id = r.getId();
		Query q = em.createQuery("delete from Sto s WHERE s.restoran.id = " + id);
		int deletedCount =  q.executeUpdate();
		if(deletedCount != 0) {
			return true;
		} else
		return false;
	}

}
