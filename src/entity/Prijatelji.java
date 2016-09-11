package entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(uniqueConstraints={
	    @UniqueConstraint(columnNames = {"id_prvog", "id_drugog"})},
	    name = "prijatelji")
@NamedQuery(name = "findFriendsOfUserId", query = "select p from Prijatelji p, Korisnik k where p.id_prvog = k.id and k_id = :id_user")
public class Prijatelji implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7039818958809788268L;
    
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "korisnik_id", unique = true, nullable = false)
	private Integer id_prijatelji;
    
	@Column(name = "id_prvog", unique = false, nullable = false)
	private Integer id_prvog;
	
	@Column(name = "id_drugog", unique = false, nullable = false)
	private Integer id_drugog;
	
	public Integer getId_prijatelji() {
		return id_prijatelji;
	}

	public void setId_prijatelji(Integer id_prijatelji) {
		this.id_prijatelji = id_prijatelji;
	}

	public Integer getId_prvog() {
		return id_prvog;
	}

	public void setId_prvog(Integer id_prvog) {
		this.id_prvog = id_prvog;
	}

	public Integer getId_drugog() {
		return id_drugog;
	}

	public void setId_drugog(Integer id_drugog) {
		this.id_drugog = id_drugog;
	}
	
	public Prijatelji() {
		super();
	}
	
	public Prijatelji(Integer id_prvog, Integer id_drugog) {
		super();
		this.id_prvog = id_prvog;
		this.id_drugog = id_drugog;
	}
}
