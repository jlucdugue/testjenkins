package fr.imie.service;

import java.util.List;

import javax.ejb.Local;

import fr.imie.model.Personne;

@Local
public interface PersonneServiceLocal {

	Personne findById(Personne personne);

	List<Personne> findAll();

	void delete(Personne personne);

	Personne update(Personne personne);

	Personne create(Personne personne);

}
