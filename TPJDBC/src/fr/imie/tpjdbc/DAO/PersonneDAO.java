/**
 * 
 */
package fr.imie.tpjdbc.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
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
		List<PersonneDTO> dtos = new ArrayList<PersonneDTO>();
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
				personneDTO.setDateNaiss(resultSet.getDate("datenaiss"));
				personneDTO.setTel(resultSet.getString("tel"));
				dtos.add(personneDTO);
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

		return dtos;
	}

}
