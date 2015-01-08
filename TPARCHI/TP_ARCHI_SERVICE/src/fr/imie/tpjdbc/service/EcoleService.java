package fr.imie.tpjdbc.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import fr.imie.tpjdbc.DAO.IPersonneDAO;
import fr.imie.tpjdbc.DAO.IPromotionDAO;
import fr.imie.tpjdbc.DAO.PersonneDAO;
import fr.imie.tpjdbc.DAO.PromotionDAO;
import fr.imie.tpjdbc.DTO.PersonneDTO;
import fr.imie.tpjdbc.DTO.PromotionDTO;

public class EcoleService implements IEcoleService {

	private IPersonneDAO personneDAO = new PersonneDAO();
	private IPromotionDAO promotionDAO = new PromotionDAO();

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
		return personneDAO.findById(selectedPersonne);
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

			connection = DriverManager.getConnection(
					"jdbc:postgresql://localhost:5432/imie", "postgres",
					"postgres");
			connection.setAutoCommit(false);

//			IPersonneDAO personneDAO = new PersonneDAO();
			PersonneDTO findParameter = new PersonneDTO();
			findParameter.setPromotionDTO(selectedPromotion);
			List<PersonneDTO> findedPersonne = personneDAO.findByDTO(
					findParameter, connection);

			for (PersonneDTO personneDTO : findedPersonne) {
				personneDTO.setPromotionDTO(null);
				personneDAO.update(personneDTO, connection);
			}
			
			promotionDAO.delete(selectedPromotion,connection);
			
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
				if (connection != null && !connection.isClosed()) {
					connection.close();
				}
			} catch (SQLException e) {
				throw new RuntimeException("erreur applicative", e);
			}
		}
	}

	@Override
	public List<PersonneDTO> findAllPersonne() {
		return personneDAO.findAll();
	}

}
