package fr.imie.tpjdbc.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import fr.imie.tpjdbc.AJDBC;
import fr.imie.tpjdbc.DTO.PersonneDTO;
import fr.imie.tpjdbc.DTO.PromotionDTO;

public class EcoleServiceProxy extends AJDBC implements IEcoleService {

	private IEcoleService real = null;
	private static EcoleServiceProxy instance = null;
	private Connection masterConnection = null;

	public static synchronized EcoleServiceProxy getInstance(IEcoleService real) {
		if (instance == null) {
			instance = new EcoleServiceProxy(real);
		}
		return instance;
	}

	/**
	 * @param real
	 */
	private EcoleServiceProxy(IEcoleService real) {
		super();
		this.real = real;
	}

	@Override
	public PersonneDTO insertPersonne(PersonneDTO personneDTO) {
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
			retour = real.insertPersonne(personneDTO);
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
	public void deletePersonne(PersonneDTO selectedPersonne) {
		// PersonneDTO retour= null;
		Connection connection = null;
		try {
			if (masterConnection == null) {
				connection = provideConnection();
				connection.setAutoCommit(false);
				real.setConnection(connection);
			} else {
				real.setConnection(masterConnection);
			}
			real.deletePersonne(selectedPersonne);
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

		// return retour;
	}

	@Override
	public PersonneDTO findPersonneById(PersonneDTO selectedPersonne) {
		PersonneDTO retour = null;
		Connection connection = null;
		try {
			if (masterConnection == null) {
				connection = provideConnection();
				connection.setAutoCommit(false);
				real.setConnection(connection);
			} else {
				real.setConnection(connection);
			}
			retour = real.findPersonneById(selectedPersonne);
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
	public void updatePersonne(PersonneDTO selectedPersonne) {
		// PersonneDTO retour= null;
		Connection connection = null;
		try {
			if (masterConnection == null) {
				connection = provideConnection();
				connection.setAutoCommit(false);
				real.setConnection(connection);
			} else {
				real.setConnection(masterConnection);
			}
			real.updatePersonne(selectedPersonne);
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
	public List<PromotionDTO> findAllPromotions() {
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
			dtos = real.findAllPromotions();
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
	public void deletePromotion(PromotionDTO selectedPromotion) {
		Connection connection = null;
		try {
			if (masterConnection == null) {
				connection = provideConnection();
				connection.setAutoCommit(false);
				real.setConnection(connection);
			} else {
				real.setConnection(masterConnection);
			}
			real.deletePromotion(selectedPromotion);
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
	public List<PersonneDTO> findAllPersonne() {
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
			dtos = real.findAllPersonne();
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
	public void setConnection(Connection connection) {
		masterConnection = connection;

	}

	@Override
	public Connection getConnection() {
		return masterConnection;
	}

}
