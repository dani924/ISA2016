package sessionbeans;

import java.util.List;

import entity.Korisnik;
import entity.Prijatelji;

public interface PrijateljiDaoLocal extends GenericDaoLocal<Prijatelji, Integer> {

	    public boolean removeFromFriends(Integer id_prvog, String email);
	    public List<Korisnik> searchFriends(String ime, String prezime);
	    public boolean addToFriends(Integer id_prvog, String email);
}