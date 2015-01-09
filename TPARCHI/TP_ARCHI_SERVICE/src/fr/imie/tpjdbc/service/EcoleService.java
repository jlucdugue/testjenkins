package fr.imie.tpjdbc.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import fr.imie.tpjdbc.AJDBC;
import fr.imie.tpjdbc.DAO.IPersonneDAO;
import fr.imie.tpjdbc.DAO.IPromotionDAO;
import fr.imie.tpjdbc.DAO.PersonneDAO;
import fr.imie.tpjdbc.DAO.PromotionDAO;
import fr.imie.tpjdbc.DTO.PersonneDTO;
import fr.imie.tpjdbc.DTO.PromotionDTO;

public class EcoleService extends AJDBC implements IEcoleService {

	private IPersonneDAO personneDAO = PersonneDAO.getInstance();
	private IPromotionDAO promotionDAO = PromotionDAO.getInstance();
	private static EcoleService instance;

	/**
	 * 
	 */
	private EcoleService() {
		super();
		// TODO Auto-generated constructor stub
	}

	public static synchronized EcoleService getInstance() {
		if (instance == null) {
			instance = new EcoleService();
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

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		PersonneDTO retour = null;
		try {

			connection = provideConnection();

			connection.setAutoCommit(false);

			// IPersonneDAO personneDAO = new PersonneDAO();
			PersonneDTO findParameter = new PersonneDTO();
			findParameter.setPromotionDTO(selectedPromotion);
			List<PersonneDTO> findedPersonne = personneDAO.findByDTO(
					findParameter, connection);

			for (PersonneDTO personneDTO : findedPersonne) {
				personneDTO.setPromotionDTO(null);
				personneDAO.update(personneDTO, connection);
			}

			promotionDAO.delete(selectedPromotion, connection);

			connection.commit();

		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				throw new RuntimeException("erreur applicative", e1);
			}
			throw new RuntimeException("erreur applicative", e);
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
			closeConnection(connection);
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

}
