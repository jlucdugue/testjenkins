package fr.imie.tpjdbc.service;

import java.util.List;

import fr.imie.tpjdbc.Itransactional;
import fr.imie.tpjdbc.DTO.PersonneDTO;
import fr.imie.tpjdbc.DTO.PromotionDTO;

public interface IEcoleService extends Itransactional {

	PersonneDTO insertPersonne(PersonneDTO personneDTO);

	void deletePersonne(PersonneDTO selectedPersonne);

	PersonneDTO findPersonneById(PersonneDTO selectedPersonne);

	void updatePersonne(PersonneDTO selectedPersonne);

	List<PromotionDTO> findAllPromotions();

	void deletePromotion(PromotionDTO selectedPromotion);

	List<PersonneDTO> findAllPersonne();

}
