/**
 * 
 */
package fr.imie.tpjdbc.presentation;

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

	Scanner scanner;

	/**
	 * 
	 */
	public Presentation() {
		scanner = new Scanner(System.in);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.imie.tpjdbc.presentation.IPresentation#start()
	 */
	@Override
	public void start() {

		try {

			// TP1
			Boolean endAppli = false;
			while (!endAppli) {

				// instantiation du DAO
				IPersonneDAO personneDAO = new PersonneDAO();
				// utilisation du DAO
				List<PersonneDTO> personneDTOs = personneDAO.findAll();

				Integer numLigne = 1;

				for (PersonneDTO personneDTO : personneDTOs) {
					System.out.format("%d : %s | %s\n", numLigne++,
							personneDTO.getNom(), personneDTO.getPrenom());
				}

				// TP 2.1
				Boolean goodInput = false;
				Integer input = null;
				while (!goodInput) {
					try {
						System.out
								.println("saisir le numero de ligne d'une personne");
						String rawInput = scanner.nextLine();
						input = Integer.valueOf(rawInput);
						goodInput = true;
					} catch (NumberFormatException e) {
						System.out.println("mauvais format");
					}
				}

				if (input == 0) {
					endAppli = true;
					break;
				}

				PersonneDTO selectedPersonne = personneDTOs.get(input - 1);
				selectedPersonne = personneDAO.findById(selectedPersonne);
				System.out.format("nom : %s \nprenom :  %s\ndateNaiss : %s\ntel %s\n\n",
						selectedPersonne.getNom(),
						selectedPersonne.getPrenom(),
						selectedPersonne.getDateNaiss(),
						selectedPersonne.getTel());

			}

		} catch (Exception e) {
			throw new RuntimeException("erreure applicative", e);
		} finally {
			if (scanner != null) {
				scanner.close();
			}
		}

		System.out.println("End");

	}

}
