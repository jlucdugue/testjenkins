/**
 * 
 */
package fr.imie.tpjdbc.presentation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Scanner;

import fr.imie.tpjdbc.DAO.IPersonneDAO;
import fr.imie.tpjdbc.DAO.PersonneDAO;
import fr.imie.tpjdbc.DTO.PersonneDTO;

/**
 * @author imie
 *
 */
public class Presentation implements IPresentation {

	
	Scanner scanner ;
	/**
	 * 
	 */
	public Presentation() {
		scanner = new Scanner(System.in);
	}

	/* (non-Javadoc)
	 * @see fr.imie.tpjdbc.presentation.IPresentation#start()
	 */
	@Override
	public void start(){
		Connection connection = null;
		Statement statement = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			
			// TP1
			Boolean endAppli = false;
			while(!endAppli){
				
				//instantiation du DAO
				IPersonneDAO personneDAO = new PersonneDAO();
				//utilisation du DAO
				List<PersonneDTO> personneDTOs = personneDAO.findAll();
				
				
				Integer numLigne=1;
				
				for (PersonneDTO personneDTO : personneDTOs) {
					System.out.format("%d : %s | %s\n", numLigne++, personneDTO.getNom(),
							personneDTO.getPrenom());
				}
					
				
				connection = DriverManager.getConnection(
						"jdbc:postgresql://localhost:5432/imie", "postgres",
						"postgres");
			
				// TP 2.1
				Boolean goodInput = false;
				Integer input = null;
				while (!goodInput) {
					try {
						System.out.println("saisir le numero de ligne d'une personne");
						String rawInput = scanner.nextLine();
						input = Integer.valueOf(rawInput);
						goodInput=true;
					} catch (NumberFormatException e) {
						System.out.println("mauvais format");
					}
				}

				if(input==0){
					endAppli=true;
					break;
				}
				//récupérer l'ID dans le tableau de conservation des id à partir de la saisie du numéro de ligne
				String query = "select nom, prenom from personne where id="
						.concat(personneDTOs.get(input-1).getId().toString());
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
				preparedStatement.setInt(1, personneDTOs.get(input-1).getId());
				resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) {
					System.out.format("%s | %s\n", resultSet.getString("nom"),
							resultSet.getString("prenom"));
				}

				resultSet.close();
				preparedStatement.close();
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
