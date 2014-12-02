package fr.imie.tpjdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 * 
 */

/**
 * @author imie
 *
 */
public class Launcher {

	/**
	 * fonction de démarrage de l'application utilisation de JDBC pour faire les
	 * différentes requêtes recherche des personnes en utilisant un statement +
	 * executeQuery recherche d'une personne en construisant dynamiquement la
	 * requête + executeQuery recherche d'une personne en utilisant un
	 * preparedStatement + Execute Query libération des ressources dans le
	 * finally
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Connection connection = null;
		Statement statement = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Scanner scanner = new Scanner(System.in);
		try {

			// TP1
			connection = DriverManager.getConnection(
					"jdbc:postgresql://localhost:5432/imie", "postgres",
					"postgres");
			statement = connection.createStatement();
			resultSet = statement
					.executeQuery("select nom, prenom from personne");
			while (resultSet.next()) {
				System.out.format("%s | %s\n", resultSet.getString("nom"),
						resultSet.getString("prenom"));
			}

			statement.close();
			resultSet.close();

			// TP 2.1
			Boolean goodInput = false;
			Integer input = null;
			while (!goodInput) {
				try {
					System.out.println("saisir l'id d'une personne");
					String rawInput = scanner.nextLine();
					input = Integer.valueOf(rawInput);
					goodInput=true;
				} catch (NumberFormatException e) {
					System.out.println("mauvais format");
				}
			}

			String query = "select nom, prenom from personne where id="
					.concat(input.toString());
			statement = connection.createStatement();
			resultSet = statement.executeQuery(query);
			while (resultSet.next()) {
				System.out.format("%s | %s\n", resultSet.getString("nom"),
						resultSet.getString("prenom"));
			}

			resultSet.close();
			statement.close();

			// TP 2.2
			preparedStatement = connection
					.prepareStatement("select nom, prenom from personne where id=?");
			preparedStatement.setInt(1, input);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				System.out.format("%s | %s\n", resultSet.getString("nom"),
						resultSet.getString("prenom"));
			}

			resultSet.close();
			preparedStatement.close();

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
				if (preparedStatement != null && !preparedStatement.isClosed()) {
					preparedStatement.close();
				}
				if (connection != null && !connection.isClosed()) {
					connection.close();
				}
				if (scanner != null) {
					scanner.close();
				}
			} catch (SQLException e) {
				throw new RuntimeException("erreure applicative", e);
			}
		}

		System.out.println("End");
	}

}
