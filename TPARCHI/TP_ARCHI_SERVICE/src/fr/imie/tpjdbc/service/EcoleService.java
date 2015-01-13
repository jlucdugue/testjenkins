package fr.imie.tpjdbc.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import fr.imie.tpjdbc.AJDBC;
import fr.imie.tpjdbc.AbstractFactory;
import fr.imie.tpjdbc.DAO.IPersonneDAO;
import fr.imie.tpjdbc.DAO.IPromotionDAO;
import fr.imie.tpjdbc.DTO.PersonneDTO;
import fr.imie.tpjdbc.DTO.PromotionDTO;

public class EcoleService extends AJDBC implements IEcoleService {

	private IPersonneDAO personneDAO = null;
	private IPromotionDAO promotionDAO = null;
	private Connection connection = null;
	private static EcoleService instance;

	/**
	 * 
	 */
	private EcoleService(AbstractFactory factory) {
		super();
		personneDAO = factory.createPersonneDAO();
		promotionDAO = factory.createPromotionDAO();
	}

	public static synchronized EcoleService getInstance(AbstractFactory factory) {
		if (instance == null) {
			instance = new EcoleService(factory);
		}
		return instance;
	}

	@Override
	public PersonneDTO insertPersonne(PersonneDTO personneDTO) {
		return personneDAO.insert(personneDTO);
	}

	@Override
	public void deletePersonne(PersonneDTO selectedPersonne) {
		personneDAO.delete(selectedPersonne);

	}

	@Override
	public PersonneDTO findPersonneById(PersonneDTO selectedPersonne) {
		PersonneDTO personneDTO = personneDAO.findById(selectedPersonne);
		compeletePersonneDTO(personneDTO);
		return personneDTO;
	}

	/**
	 * @param personneDTO
	 */
	private void compeletePersonneDTO(PersonneDTO personneDTO) {
		if (personneDTO.getPromotionDTO() != null
				&& personneDTO.getPromotionDTO().getId() != null) {
			PromotionDTO findedPromotion = promotionDAO.findById(personneDTO
					.getPromotionDTO());
			personneDTO.setPromotionDTO(findedPromotion);
		}
	}

	@Override
	public void updatePersonne(PersonneDTO selectedPersonne) {
		personneDAO.update(selectedPersonne);

	}

	@Override
	public List<PromotionDTO> findAllPromotions() {
		return promotionDAO.findAll();
	}

	@Override
	public void deletePromotion(PromotionDTO selectedPromotion) {

		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		PersonneDTO retour = null;
		try {
			// IPersonneDAO personneDAO = new PersonneDAO();
			PersonneDTO findParameter = new PersonneDTO();
			findParameter.setPromotionDTO(selectedPromotion);
			personneDAO.setConnection(connection);
			List<PersonneDTO> findedPersonne = personneDAO
					.findByDTO(findParameter);

			for (PersonneDTO personneDTO : findedPersonne) {
				personneDTO.setPromotionDTO(null);
				personneDAO.update(personneDTO);
			}
			personneDAO.setConnection(null);

			promotionDAO.setConnection(connection);
			promotionDAO.delete(selectedPromotion);
			promotionDAO.setConnection(null);

		} finally {
			try {
				if (resultSet != null && !resultSet.isClosed()) {
					resultSet.close();
				}
				if (preparedStatement != null && !preparedStatement.isClosed()) {
					preparedStatement.close();
				}
			} catch (SQLException e) {
				throw new RuntimeException("erreur applicative", e);
			}
		}
	}

	@Override
	public List<PersonneDTO> findAllPersonne() {
		List<PersonneDTO> personneDTOs = personneDAO.findAll();
		for (PersonneDTO personneDTO : personneDTOs) {
			compeletePersonneDTO(personneDTO);
		}
		return personneDTOs;
	}

	@Override
	public Boolean checkAuthentification(PersonneDTO personneDTO) {
		List<PersonneDTO> findedPersonneDTOs = personneDAO.findByDTO(personneDTO);
		Boolean retour=null;
		if(findedPersonneDTOs.isEmpty()){
			retour = false;
		}else{
			retour = true;
		}
		return retour;

	}
	
	@Override
	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	@Override
	public Connection getConnection() {
		return connection;
	}



}
