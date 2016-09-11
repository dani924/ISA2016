package entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "sto")
public class Sto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5962399667895757432L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "sto_id", unique = true, nullable = false)
	private Integer id;

	@Column(name = "hor", unique = false, nullable = false)
	private Integer hor;

	@Column(name = "ver", unique = false, nullable = false)
	private Integer ver;

	@OneToOne
	@JoinColumn(name = "restoran_id", referencedColumnName = "restoran_id", nullable = false)
	private Restoran restoran;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getHor() {
		return hor;
	}

	public void setHor(Integer hor) {
		this.hor = hor;
	}

	public Integer getVer() {
		return ver;
	}

	public void setVer(Integer ver) {
		this.ver = ver;
	}

	public Restoran getRestoran() {
		return restoran;
	}

	public void setRestoran(Restoran restoran) {
		this.restoran = restoran;
	}
    
	public Sto() {
		super();
	}
	
	public Sto(Integer h, Integer v, Restoran r) {
		this.hor = h;
		this.ver = v;
		this.restoran = r;
	}
}
