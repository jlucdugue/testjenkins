package fr.imie.tpjdbc.DAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import fr.imie.tpjdbc.AJDBC;
import fr.imie.tpjdbc.DTO.PromotionDTO;

public class PromotionDAOProxy extends AJDBC implements IPromotionDAO {

	private IPromotionDAO real = null;
	private static PromotionDAOProxy instance = null;
	private Connection masterConnection=null;

	private PromotionDAOProxy(IPromotionDAO real) {
		super();
		this.real = real;

	}

	public static synchronized IPromotionDAO getInstance(IPromotionDAO real) {
		if (instance == null) {
			instance = new PromotionDAOProxy(real);
		}
		return instance;
	}

	@Override
	public void setConnection(Connection connection) {
		this.masterConnection =connection;

	}

	@Override
	public Connection getConnection() {
		return masterConnection;
	}

	@Override
	public PromotionDTO findById(PromotionDTO dto) {
		PromotionDTO retour= null;
		Connection connection = null;
		try {
			if (masterConnection == null) {
				connection = provideConnection();
				connection.setAutoCommit(false);
				real.setConnection(connection);
			} else {
				real.setConnection(masterConnection);
			}
			retour = real.findById(dto);

			if (masterConnection == null) {
				connection.commit();
			}

		} catch (Exception e) {
			try {
				if (masterConnection == null) {
					connection.rollback();
				}

			} catch (SQLException e1) {
				throw new RuntimeException(e1);
			}
			throw new RuntimeException(e);
		} finally {

			real.setConnection(null);

			if (masterConnection == null) {
				closeConnection(connection);
			}
		}

		return retour;
	}

	@Override
	public List<PromotionDTO> findAll() {
		List<PromotionDTO> dtos = null;
		Connection connection = null;
		try {
			if (masterConnection == null) {
				connection = provideConnection();
				connection.setAutoCommit(false);
				real.setConnection(connection);
			} else {
				real.setConnection(masterConnection);
			}
			dtos = real.findAll();
			if (masterConnection == null) {
				connection.commit();
			}
		} catch (Exception e) {
			try {

				if (masterConnection != null) {
					connection.rollback();
				}

			} catch (SQLException e1) {
				throw new RuntimeException(e1);
			}
			throw new RuntimeException(e);
		} finally {
			real.setConnection(null);
			if (masterConnection == null) {
				closeConnection(connection);
			}
		}

		return dtos;
	}

	@Override
	public void delete(PromotionDTO dto) {
		Connection connection = null;
		try {
			if (masterConnection == null) {
				connection = provideConnection();
				connection.setAutoCommit(false);
				real.setConnection(connection);
			} else {
				real.setConnection(masterConnection);
			}
			real.delete(dto);

			if (masterConnection == null) {
				connection.commit();
			}

		} catch (Exception e) {
			try {
				if (masterConnection == null) {
					connection.rollback();
				}

			} catch (SQLException e1) {
				throw new RuntimeException(e1);
			}
			throw new RuntimeException(e);
		} finally {

			real.setConnection(null);

			if (masterConnection == null) {
				closeConnection(connection);
			}
		}

	}

}
