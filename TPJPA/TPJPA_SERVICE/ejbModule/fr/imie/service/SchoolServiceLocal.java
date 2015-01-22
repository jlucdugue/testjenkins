package fr.imie.service;

import java.util.List;

import javax.ejb.Local;

import fr.imie.model.Personne;
import fr.imie.model.Promotion;

@Local
public interface SchoolServiceLocal {

	public Personne findPersonById(Personne personne);

	public List<Personne> findPersonAll();

	public void deletePerson(Personne personne);

	public Personne updatePerson(Personne personne);

	public Personne createPerson(Personne personne);

	public List<Promotion> findAllClass();

}
