package fr.imie.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.imie.model.Personne;
import fr.imie.model.Promotion;

/**
 * Session Bean implementation class PersonneService
 */
@Stateless
public class SchoolService implements SchoolServiceLocal {

	@PersistenceContext
	EntityManager entityManager;

	/**
	 * Default constructor.
	 */
	public SchoolService() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Personne findPersonById(Personne personne) {
		return entityManager.find(Personne.class, personne.getId());
	}

	@Override
	public List<Personne> findPersonAll() {
		return entityManager.createNamedQuery("Personne.findAll")
				.getResultList();
	}

	@Override
	public void deletePerson(Personne personne) {
		personne = entityManager.merge(personne);
		entityManager.remove(personne);
	}

	@Override
	public Personne updatePerson(Personne personne) {
		return entityManager.merge(personne);

	}

	@Override
	public Personne createPerson(Personne personne) {
		entityManager.persist(personne);
		return personne;
		
	}

	@Override
	public List<Promotion> findAllClass() {
		return entityManager.createNamedQuery("Promotion.findAll")
				.getResultList();
	}

	@Override
	public Promotion findPromotionById(Promotion promotion) {
		return entityManager.find(Promotion.class, promotion.getId());
	}

}
















