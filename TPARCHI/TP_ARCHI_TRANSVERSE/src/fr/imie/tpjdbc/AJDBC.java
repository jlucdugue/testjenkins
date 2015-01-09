package fr.imie.tpjdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class AJDBC {

	public Connection provideConnection() throws SQLException {
		Connection connection = null;
		synchronized (DriverManager.class) {
			connection = DriverManager.getConnection(
					"jdbc:postgresql://localhost:5432/imie", "postgres",
					"postgres");
		}
		return connection;
	}
	
	public void closeConnection(Connection connection){
		try {
			if (connection != null && !connection.isClosed()) {
				connection.close();
			}

		} catch (SQLException e) {
			throw new RuntimeException("erreure applicative", e);
		}
	}

	
	
}
