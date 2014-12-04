/**
 * 
 */
package fr.imie.tpjdbc.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.imie.tpjdbc.DTO.PersonneDTO;

/**
 * @author imie
 *
 */
public class PersonneDAO implements IPersonneDAO {

	/**
	 * 
	 */
	public PersonneDAO() {
		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.imie.tpjdbc.DAO.IPersonneDAO#findAll()
	 */
	@Override
	public List<PersonneDTO> findAll() {
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		List<PersonneDTO> retour = new ArrayList<PersonneDTO>();
		try {

			connection = DriverManager.getConnection(
					"jdbc:postgresql://localhost:5432/imie", "postgres",
					"postgres");

			statement = connection.createStatement();
			resultSet = statement
					.executeQuery("select id, nom, prenom, datenaiss, tel from personne");

			while (resultSet.next()) {
				PersonneDTO personneDTO = new PersonneDTO();
				personneDTO.setId(resultSet.getInt("id"));
				personneDTO.setNom(resultSet.getString("nom"));
				personneDTO.setPrenom(resultSet.getString("prenom"));
				retour.add(personneDTO);
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

	@Override
	public PersonneDTO findById(PersonneDTO dto) {
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet=null;
		PersonneDTO retour=null;
		try {

			connection = DriverManager.getConnection(
					"jdbc:postgresql://localhost:5432/imie", "postgres",
					"postgres");

			preparedStatement = connection
					.prepareStatement("select nom, prenom, id, datenaiss,tel from personne where id=?");
			preparedStatement.setInt(1, dto.getId());
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				retour = new PersonneDTO();
				retour.setId(resultSet.getInt("id"));
				retour.setNom(resultSet.getString("nom"));
				retour.setPrenom(resultSet.getString("prenom"));
				retour.setDateNaiss(resultSet.getDate("datenaiss"));
				retour.setTel(resultSet.getString("tel"));
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
	public PersonneDTO insert(PersonneDTO dto) {
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet=null;
		PersonneDTO retour=null;
		try {

			connection = DriverManager.getConnection(
					"jdbc:postgresql://localhost:5432/imie", "postgres",
					"postgres");

			preparedStatement = connection
					.prepareStatement("insert into personne(nom, prenom, datenaiss,tel) values(?,?,?,?) returning nom, prenom, datenaiss,tel,id");
			preparedStatement.setString(1, dto.getNom());
			preparedStatement.setString(2, dto.getPrenom());
			Date dateNaiss = new Date(dto.getDateNaiss().getTime());
			preparedStatement.setDate(3, dateNaiss);
			preparedStatement.setString(4, dto.getTel());
			
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				retour = new PersonneDTO();
				retour.setId(resultSet.getInt("id"));
				retour.setNom(resultSet.getString("nom"));
				retour.setPrenom(resultSet.getString("prenom"));
				retour.setDateNaiss(resultSet.getDate("datenaiss"));
				retour.setTel(resultSet.getString("tel"));
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
	
	

}
