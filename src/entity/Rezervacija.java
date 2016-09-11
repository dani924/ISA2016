package entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "rezervacija")
public class Rezervacija implements Serializable {
    
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "rezervacija_id", unique = true, nullable = false)
	private Integer id;

	@Column(name = "datum", unique = false, nullable = false)
	private Date datum;

	@Column(name = "trajanje", unique = false, nullable = false)
	private Integer trajanje;
	
	@Column(name = "status", unique = true, nullable = false)
	private String status;

	@Column(name = "vrsta", unique = false, nullable = false)
	private String vrsta;

	@Column(name = "adresa", unique = false, nullable = false)
	private String adresa;
}
