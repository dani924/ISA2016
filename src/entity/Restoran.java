package entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "restoran")
public class Restoran implements Serializable {
 
	/**
	 * 
	 */
	private static final long serialVersionUID = 210362633685112084L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "restoran_id", unique = true, nullable = false)
	private Integer id;

	@Column(name = "naziv", unique = true, nullable = false)
	private String naziv;

	@Column(name = "vrsta", unique = false, nullable = false)
	private String vrsta;

	@Column(name = "adresa", unique = false, nullable = false)
	private String adresa;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getVrsta() {
		return vrsta;
	}

	public void setVrsta(String vrsta) {
		this.vrsta = vrsta;
	}

	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}
    
	public Restoran() {
		super();
	}
	
	public Restoran(String naziv, String vrsta, String adresa) {
		super();
		this.adresa = adresa;
		this.naziv = naziv;
		this.vrsta = vrsta;
	}
}
