package fr.imie.tpjdbc;

import fr.imie.tpjdbc.DAO.IPersonneDAO;
import fr.imie.tpjdbc.DAO.IPromotionDAO;
import fr.imie.tpjdbc.DAO.PersonneDAO;
import fr.imie.tpjdbc.DAO.PromotionDAO;
import fr.imie.tpjdbc.presentation.IPresentation;
import fr.imie.tpjdbc.presentation.Presentation;
import fr.imie.tpjdbc.service.EcoleService;
import fr.imie.tpjdbc.service.IEcoleService;

public class ConcreteFactory implements AbstractFactory {

	@Override
	public IPresentation createPresentation() {
		return new Presentation(this);
	}	


	@Override
	public IPersonneDAO createPersonneDAO() {
		return PersonneDAO.getInstance();
	}

	@Override
	public IPromotionDAO createPromotionDAO() {
		return PromotionDAO.getInstance();
	}


	@Override
	public IEcoleService createEcoleService() {
		return EcoleService.getInstance(this);
	}

}
