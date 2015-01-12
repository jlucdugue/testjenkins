package fr.imie.tpjdbc.DAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import fr.imie.tpjdbc.AJDBC;
import fr.imie.tpjdbc.DTO.PersonneDTO;

public class PersonneDAOProxy extends AJDBC implements IPersonneDAO {

	private IPersonneDAO real = null;
	private static PersonneDAOProxy instance = null;
	private Connection masterConnection;

	public static synchronized IPersonneDAO getInstance(IPersonneDAO real) {
		if (instance == null) {
			instance = new PersonneDAOProxy(real);
		}
		return instance;
	}

	/**
	 * @param real
	 */
	private PersonneDAOProxy(IPersonneDAO real) {
		super();
		this.real = real;
	}

	@Override
	public void setConnection(Connection connection) {
		this.masterConnection = connection;
	}

	@Override
	public Connection getConnection() {
		throw new UnsupportedOperationException("pas sur un proxy");
	}

	@Override
	public List<PersonneDTO> findAll() {
		List<PersonneDTO> dtos = null;
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

		return dtos;
	}

	@Override
	public PersonneDTO findById(PersonneDTO dto) {
		PersonneDTO retour = null;
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
	public PersonneDTO insert(PersonneDTO dto) {
		PersonneDTO retour = null;
		Connection connection = null;
		try {
			if (masterConnection == null) {
				connection = provideConnection();
				connection.setAutoCommit(false);
				real.setConnection(connection);
			} else {
				real.setConnection(masterConnection);
			}
			retour = real.insert(dto);
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
	public PersonneDTO update(PersonneDTO dto) {
		PersonneDTO retour = null;
		Connection connection = null;
		try {
			if (masterConnection == null) {
				connection = provideConnection();
				connection.setAutoCommit(false);
				real.setConnection(connection);
			} else {
				real.setConnection(masterConnection);
			}
			retour = real.update(dto);
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
	public void delete(PersonneDTO dto) {

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

	@Override
	public PersonneDTO update(PersonneDTO dto, Connection connectionCaller) {
		PersonneDTO retour = null;
		Connection connection = null;
		try {
			if (masterConnection == null) {
				connection = provideConnection();
				connection.setAutoCommit(false);
				real.setConnection(connection);
			} else {
				real.setConnection(masterConnection);
			}
			retour = real.update(dto);
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
	public List<PersonneDTO> findByDTO(PersonneDTO findParameter) {
		List<PersonneDTO> retour = null;
		Connection connection = null;
		try {
			if (masterConnection == null) {
				connection = provideConnection();
				connection.setAutoCommit(false);
				real.setConnection(connection);
			} else {
				real.setConnection(masterConnection);
			}
			retour = real.findByDTO(findParameter);
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
	public List<PersonneDTO> findByDTO(PersonneDTO findParameter,
			Connection connectionCaller) {
		throw new UnsupportedOperationException("pas sur un proxy");
	}

}
