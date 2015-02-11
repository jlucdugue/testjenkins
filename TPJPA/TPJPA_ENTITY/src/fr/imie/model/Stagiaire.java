package fr.imie.model;

import java.io.Serializable;

import javax.persistence.*;

/**
 * The persistent class for the "stagiaire" database table.
 * 
 */
@Entity
@Table(name = "stagiaire")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@NamedQuery(name = "Stagiaire.findAll", query = "SELECT s FROM Stagiaire s")
public class Stagiaire extends Personne implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name = "experience")
	private String experience;

	// uni-directional many-to-one association to Promotion
	@ManyToOne
	@JoinColumn(name = "promotion_id")
	private Promotion promotion;

	public Stagiaire() {
	}

	public String getExperience() {
		return this.experience;
	}

	public void setExperience(String experience) {
		this.experience = experience;
	}

	public Promotion getPromotion() {
		return promotion;
	}

	public void setPromotion(Promotion promotion) {
		this.promotion = promotion;
	}

}