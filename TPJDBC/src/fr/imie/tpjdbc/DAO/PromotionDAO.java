package fr.imie.tpjdbc.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.imie.tpjdbc.DTO.PersonneDTO;
import fr.imie.tpjdbc.DTO.PromotionDTO;

public class PromotionDAO implements IPromotionDAO {

	public PromotionDAO() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<PromotionDTO> findAll() {
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		List<PromotionDTO> retour = new ArrayList<PromotionDTO>();
		try {

			connection = DriverManager.getConnection(
					"jdbc:postgresql://localhost:5432/imie", "postgres",
					"postgres");

			statement = connection.createStatement();
			resultSet = statement
					.executeQuery("select id, libelle from promotion");

			while (resultSet.next()) {

				retour.add(buildDTO(resultSet));
			}

		} catch (SQLException e) {
			throw new RuntimeException("erreure applicative", e);
		} finally {
			try {
				if (resultSet != null && !resultSet.isClosed()) {
					resultSet.close();
				}
				if (statement != null && !statement.isClosed()) {
					statement.close();
				}
				if (connection != null && !connection.isClosed()) {
					connection.close();
				}

			} catch (SQLException e) {
				throw new RuntimeException("erreure applicative", e);
			}
		}

		return retour;
	}

	
	private PromotionDTO buildDTO(ResultSet resultSet) throws SQLException {
		PromotionDTO promotionDTO = new PromotionDTO();
		promotionDTO.setId(resultSet.getInt("id"));
		promotionDTO.setLibelle(resultSet.getString("libelle"));
		return promotionDTO;
	}

	@Override
	public PromotionDTO findById(PromotionDTO dto) {
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet=null;
		PromotionDTO retour=null;
		try {

			connection = DriverManager.getConnection(
					"jdbc:postgresql://localhost:5432/imie", "postgres",
					"postgres");

			preparedStatement = connection
					.prepareStatement("select id, libelle from promotion where id=?");
			preparedStatement.setInt(1, dto.getId());
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				retour = new PromotionDTO();
				retour.setId(resultSet.getInt("id"));
				retour.setLibelle(resultSet.getString("libelle"));
			}

			

		} catch (SQLException e) {
			throw new RuntimeException("erreure applicative", e);
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
				throw new RuntimeException("erreure applicative", e);
			}
		}

		return retour;

	}

	@Override
	public void delete(PromotionDTO dto) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		PersonneDTO retour = null;
		try {

			connection = DriverManager.getConnection(
					"jdbc:postgresql://localhost:5432/imie", "postgres",
					"postgres");
			connection.setAutoCommit(false);
			
			IPersonneDAO personneDAO = new PersonneDAO();
			PersonneDTO findParameter = new PersonneDTO();
			findParameter.setPromotionDTO(dto);
			List<PersonneDTO> findedPersonne =  personneDAO.findByDTO(findParameter,connection);
			
			for (PersonneDTO personneDTO : findedPersonne) {
				personneDTO.setPromotionDTO(null);
				personneDAO.update(personneDTO, connection);
			}

			preparedStatement = connection
					.prepareStatement("delete from promotion where id =?");
			preparedStatement.setInt(1, dto.getId());

			preparedStatement.executeUpdate();
			
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

}
