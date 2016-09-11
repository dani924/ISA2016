package entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "korisnik")
@NamedQuery(name = "findKorisnikSaKorisnickimImenomILozinkom", query = "SELECT k FROM Korisnik k WHERE k.korisnickoImeKorisnika like :korisnickoIme AND k.lozinkaKorisnika LIKE :lozinka")
public class Korisnik implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7337024609699672749L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "korisnik_id", unique = true, nullable = false)
	private Integer id;

	@Column(name = "korisnik_ime", unique = false, nullable = false)
	private String imeKorisnika;

	@Column(name = "korisnik_prezime", unique = false, nullable = false)
	private String prezimeKorisnika;

	@Column(name = "korisnik_korisnicko_ime", unique = true, nullable = false)
	private String korisnickoImeKorisnika;

	@Column(name = "korisnik_lozinka", unique = false, nullable = false)
	private String lozinkaKorisnika;
	
	@Column(name = "korisnik_uloga", unique = false, nullable = false)
	private Integer ulogaKorisnika;
	
	@Column(name = "korisnik_restoran", unique = false, nullable = true)
	private Integer restoranKorisnik;
	
	@Column(name = "autentificated", unique = false, nullable = false)
	private boolean autentificated;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getImeKorisnika() {
		return imeKorisnika;
	}

	public void setImeKorisnika(String imeKorisnika) {
		this.imeKorisnika = imeKorisnika;
	}

	public String getPrezimeKorisnika() {
		return prezimeKorisnika;
	}

	public void setPrezimeKorisnika(String prezimeKorisnika) {
		this.prezimeKorisnika = prezimeKorisnika;
	}

	public String getKorisnickoImeKorisnika() {
		return korisnickoImeKorisnika;
	}

	public void setKorisnickoImeKorisnika(String korisnickoImeKorisnika) {
		this.korisnickoImeKorisnika = korisnickoImeKorisnika;
	}

	public String getLozinkaKorisnika() {
		return lozinkaKorisnika;
	}

	public void setLozinkaKorisnika(String lozinkaKorisnika) {
		this.lozinkaKorisnika = lozinkaKorisnika;
	}

	public Integer getUlogaKorisnika() {
		return ulogaKorisnika;
	}

	public void setUlogaKorisnika(Integer ulogaKorisnika) {
		this.ulogaKorisnika = ulogaKorisnika;
	}

	public Integer getRestoranKorisnik() {
		return restoranKorisnik;
	}

	public void setRestoranKorisnik(Integer restoranKorisnik) {
		this.restoranKorisnik = restoranKorisnik;
	}

	public boolean isAutentificated() {
		return autentificated;
	}

	public void setAutentificated(boolean autentificated) {
		this.autentificated = autentificated;
	}

	public Korisnik() {
		super();
	}

	public Korisnik(String imeKorisnika, String prezimeKorisnika, String korisnickoImeKorisnika, String lozinkaKorisnika,
			       Integer ulogaKorisnika, Integer restoranKorisnik, boolean autentificated) {
		super();
		this.imeKorisnika = imeKorisnika;
		this.korisnickoImeKorisnika = korisnickoImeKorisnika;
		this.lozinkaKorisnika = lozinkaKorisnika;
		this.prezimeKorisnika = prezimeKorisnika;
		this.ulogaKorisnika = ulogaKorisnika;
		this.restoranKorisnik = restoranKorisnik;
		this.autentificated = autentificated;
	}

	public String toString() {
		return "(Korisnik)[id=" + id + ",korisnicko ime=" + korisnickoImeKorisnika + "]";
	}
	
}
