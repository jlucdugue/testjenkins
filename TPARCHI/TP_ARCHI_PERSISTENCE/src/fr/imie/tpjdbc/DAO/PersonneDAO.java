/**
 * 
 */
package fr.imie.tpjdbc.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import fr.imie.tpjdbc.AJDBC;
import fr.imie.tpjdbc.AbstractFactory;
import fr.imie.tpjdbc.DTO.PersonneDTO;
import fr.imie.tpjdbc.DTO.PromotionDTO;

/**
 * @author imie
 *
 */
public class PersonneDAO extends AJDBC implements IPersonneDAO {

	private static PersonneDAO instance = null;

	private Connection connection = null;

	/**
	 * 
	 */
	private PersonneDAO() {
		// TODO Auto-generated constructor stub
	}

	public static synchronized PersonneDAO getInstance() {
		if (instance == null) {
			instance = new PersonneDAO();
		}
		return instance;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.imie.tpjdbc.DAO.IPersonneDAO#findAll()
	 */
	@Override
	public List<PersonneDTO> findAll() {
		Statement statement = null;
		ResultSet resultSet = null;
		List<PersonneDTO> retour = new ArrayList<PersonneDTO>();
		try {

			statement = connection.createStatement();
			resultSet = statement
					.executeQuery("select id, nom, prenom, datenaiss, tel, promotion_id, password from personne");

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
			} catch (SQLException e) {
				throw new RuntimeException("erreure applicative", e);
			}
		}

		return retour;
	}

	@Override
	public PersonneDTO findById(PersonneDTO dto) {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		PersonneDTO retour = null;
		try {
			preparedStatement = connection
					.prepareStatement("select nom, prenom, id, datenaiss,tel,promotion_id from personne where id=?");
			preparedStatement.setInt(1, dto.getId());
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				retour = buildDTO(resultSet);
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
			} catch (SQLException e) {
				throw new RuntimeException("erreure applicative", e);
			}
		}

		return retour;
	}

	private PersonneDTO buildDTO(ResultSet resultSet) throws SQLException {
		PersonneDTO retour;
		retour = new PersonneDTO();
		retour.setId(resultSet.getInt("id"));
		retour.setNom(resultSet.getString("nom"));
		retour.setPrenom(resultSet.getString("prenom"));
		retour.setDateNaiss(resultSet.getDate("datenaiss"));
		retour.setTel(resultSet.getString("tel"));
		retour.setPassword(resultSet.getString("password"));

		PromotionDTO linkedPromotion = new PromotionDTO();
		linkedPromotion.setId(resultSet.getInt("promotion_id"));
		retour.setPromotionDTO(linkedPromotion);

		return retour;
	}

	@Override
	public PersonneDTO insert(PersonneDTO dto) {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		PersonneDTO retour = null;
		try {
			preparedStatement = connection
					.prepareStatement("insert into personne(nom, prenom, datenaiss,tel) values(?,?,?,?) returning nom, prenom, datenaiss,tel,id,promotion_id");
			preparedStatement.setString(1, dto.getNom());
			preparedStatement.setString(2, dto.getPrenom());
			Date dateNaiss = new Date(dto.getDateNaiss().getTime());
			preparedStatement.setDate(3, dateNaiss);
			preparedStatement.setString(4, dto.getTel());

			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				retour = buildDTO(resultSet);
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
			} catch (SQLException e) {
				throw new RuntimeException("erreure applicative", e);
			}
		}

		return retour;
	}

	@Override
	public PersonneDTO update(PersonneDTO dto) {
		if (dto.getNom().equals("test")) {
			throw new RuntimeException("pour de rire");
		}
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		PersonneDTO retour = null;
		try {

			preparedStatement = connection
					.prepareStatement("update personne set nom=?, prenom=?, datenaiss=?,tel=?, promotion_id=? where id=? returning nom, prenom, datenaiss,tel,id,promotion_id");
			preparedStatement.setString(1, dto.getNom());
			preparedStatement.setString(2, dto.getPrenom());
			Date dateNaiss = new Date(dto.getDateNaiss().getTime());
			preparedStatement.setDate(3, dateNaiss);
			preparedStatement.setString(4, dto.getTel());
			if (dto.getPromotionDTO() != null) {
				preparedStatement.setInt(5, dto.getPromotionDTO().getId());
			} else {
				preparedStatement.setNull(5, Types.INTEGER);
			}

			preparedStatement.setInt(6, dto.getId());

			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				retour = buildDTO(resultSet);
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

			} catch (SQLException e) {
				throw new RuntimeException("erreure applicative", e);
			}
		}

		return retour;
	}

	@Override
	public PersonneDTO update(PersonneDTO dto, Connection connectionCaller) {
		return null;
	}

	@Override
	public void delete(PersonneDTO dto) {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {

			preparedStatement = connection
					.prepareStatement("delete from personne where id =?");
			preparedStatement.setInt(1, dto.getId());

			preparedStatement.executeUpdate();

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

			} catch (SQLException e) {
				throw new RuntimeException("erreure applicative", e);
			}
		}
	}

	@Override
	public List<PersonneDTO> findByDTO(PersonneDTO findParameter) {
		return findByDTO(findParameter, connection);
	}

	@Override
	public List<PersonneDTO> findByDTO(PersonneDTO findParameter,
			Connection connectionCaller) {
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		List<PersonneDTO> retour = new ArrayList<PersonneDTO>();
		try {

			if (connectionCaller == null) {
				connection = provideConnection();
			} else {
				connection = connectionCaller;
			}

			statement = connection.createStatement();
			String query = "select id, nom, prenom, datenaiss, tel, promotion_id from personne";

			if (findParameter.getPromotionDTO() != null) {
				query = query.concat(" where promotion_id ="
						.concat(findParameter.getPromotionDTO().getId()
								.toString()));
			}

			resultSet = statement.executeQuery(query);

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

			} catch (SQLException e) {
				throw new RuntimeException("erreure applicative", e);
			}
			if (connectionCaller == null) {
				closeConnection(connection);
			}
		}

		return retour;

	}

	@Override
	public void setConnection(Connection connection) {
		this.connection = connection;

	}

	@Override
	public Connection getConnection() {
		return this.connection;
	}

}
