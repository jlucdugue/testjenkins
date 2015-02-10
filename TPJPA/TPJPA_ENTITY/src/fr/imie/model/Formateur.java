package fr.imie.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the "formateur" database table.
 * 
 */
@Entity
@Table(name="formateur")
@NamedQuery(name="Formateur.findAll", query="SELECT f FROM Formateur f")
public class Formateur extends Personne implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="diplome")
	private String diplome;

	public Formateur() {
	}

	public String getDiplome() {
		return this.diplome;
	}

	public void setDiplome(String diplome) {
		this.diplome = diplome;
	}

}