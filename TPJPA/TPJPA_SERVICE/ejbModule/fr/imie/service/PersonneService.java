package fr.imie.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.imie.model.Personne;

/**
 * Session Bean implementation class PersonneService
 */
@Stateless
public class PersonneService implements PersonneServiceLocal {

	@PersistenceContext
	EntityManager entityManager;

	/**
	 * Default constructor.
	 */
	public PersonneService() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Personne findById(Personne personne) {
		return entityManager.find(Personne.class, personne.getId());
	}

	@Override
	public List<Personne> findAll() {
		return entityManager.createNamedQuery("Personne.findAll")
				.getResultList();
	}

	@Override
	public void delete(Personne personne) {
		personne = entityManager.merge(personne);
		entityManager.remove(personne);
	}

	@Override
	public Personne update(Personne personne) {
		return entityManager.merge(personne);

	}

	@Override
	public Personne create(Personne personne) {
		entityManager.persist(personne);
		return personne;
		
	}

}
















