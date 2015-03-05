package fr.imie;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TodoDAO {

	public Integer count() {
		Integer retour = null;
		Connection jdbcConnection;
		try {
			jdbcConnection = DriverManager.getConnection(
					"jdbc:postgresql://localhost:5432/test", "postgres",
					"postgres");

			Statement statement = jdbcConnection.createStatement();
			ResultSet resultSet = statement
					.executeQuery("select count(id) from todo");

			if (resultSet.next()) {
				retour = resultSet.getInt(1);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return retour;

	}

}
