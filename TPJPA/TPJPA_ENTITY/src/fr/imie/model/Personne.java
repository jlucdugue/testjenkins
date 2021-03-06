package fr.imie.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the "personne" database table.
 * 
 */
@Entity
@Table(name="personne")
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
@NamedQuery(name="Personne.findAll", query="SELECT p FROM Personne p")
public class Personne implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="seqPers",sequenceName="personne_id_seq")
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="seqPers")
	@Column(name="id")
	private int id;

	@Temporal(TemporalType.DATE)
	@Column(name="datenaiss")
	private Date datenaiss;

	@Column(name="nom")
	private String nom;

	@Column(name="password")
	private String password;

	@Column(name="prenom")
	private String prenom;

	@Column(name="tel")
	private String tel;

	public Personne() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDatenaiss() {
		return this.datenaiss;
	}

	public void setDatenaiss(Date datenaiss) {
		this.datenaiss = datenaiss;
	}

	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPrenom() {
		return this.prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getTel() {
		return this.tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

}