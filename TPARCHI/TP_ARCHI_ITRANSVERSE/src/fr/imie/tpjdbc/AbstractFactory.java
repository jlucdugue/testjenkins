package fr.imie.tpjdbc;

import fr.imie.tpjdbc.DAO.IPersonneDAO;
import fr.imie.tpjdbc.DAO.IPromotionDAO;
import fr.imie.tpjdbc.presentation.IPresentation;
import fr.imie.tpjdbc.service.IEcoleService;


public interface AbstractFactory {
	
	public IEcoleService createEcoleService();
	public IPersonneDAO createPersonneDAO();
	public IPromotionDAO createPromotionDAO();
	public IPresentation createPresentation();
	

}
