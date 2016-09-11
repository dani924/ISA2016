package sessionbeans;

import entity.Restoran;
import entity.Sto;

public interface StoDaoLocal extends GenericDaoLocal<Sto, Integer>  {
	public boolean removeTables(Restoran  r);
}
