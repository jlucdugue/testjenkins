/**
 * 
 */
package fr.imie.tpjdbc.presentation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

		// instantiation du DAO
					IPersonneDAO personneDAO = new PersonneDAO();
		
		// TP1
		Boolean endAppli = false;
		List<PersonneDTO> personneDTOs = null;
		
		while (!endAppli) {

			
			System.out.println("-------------------------");
			System.out.println("1 : lister tous les users");
			System.out.println("2 : detail d'un user     ");
			System.out.println("3 : insérer un user      ");
			System.out.println("-------------------------");
			
			Integer menuNumber = inputInteger();
			
			switch (menuNumber) {
			case 1:
				// utilisation du DAO
				personneDTOs = personneDAO.findAll();

				Integer numLigne = 1;

				for (PersonneDTO personneDTO : personneDTOs) {
					System.out.format("%d : %s | %s\n", numLigne++,
							personneDTO.getNom(), personneDTO.getPrenom());
				}
				break;

			case 2:
				// TP 2
				Boolean goodInput = false;
				Integer input = null;
				input = inputInteger();

				if (input == 0) {
					endAppli = true;
					break;
				}

				PersonneDTO selectedPersonne = personneDTOs.get(input - 1);
				selectedPersonne = personneDAO.findById(selectedPersonne);
				System.out.format(
						"nom : %s \nprenom :  %s\ndateNaiss : %s\ntel %s\n\n",
						selectedPersonne.getNom(), selectedPersonne.getPrenom(),
						selectedPersonne.getDateNaiss(), selectedPersonne.getTel());
			case 3:
				
				PersonneDTO personneDTO =  new PersonneDTO();
				System.out.print("nom :  ");
				String nom = scanner.nextLine();
				personneDTO.setNom(nom);
				System.out.print("prenom :  ");
				String prenom = scanner.nextLine();
				personneDTO.setPrenom(prenom);
				System.out.print("tel :  ");
				String tel = scanner.nextLine();
				personneDTO.setTel(tel);
				System.out.print("date de naissance :  ");
				Date date = inputDate();
				personneDTO.setDateNaiss(date);
				personneDTO=personneDAO.insert(personneDTO);
				System.out.format("nouvel id inséré : %s\n",personneDTO.getId());
			default:
	
			}
			
			
			

			

		}

		System.out.println("End");

	}

	private Integer inputInteger() {
		Boolean goodInput=false;
		Integer input=null;
		while (!goodInput) {
			try {
				System.out.println("saisir un numero");
				String rawInput = scanner.nextLine();
				input = Integer.valueOf(rawInput);
				goodInput = true;
			} catch (NumberFormatException e) {
				System.out.println("mauvais format");
			}
		}
		return input;
	}
	
	private Date inputDate() {
		SimpleDateFormat simpleDateFormat= new SimpleDateFormat("dd/MM/yyyy");
		Boolean goodInput=false;		
		Date input=null;
		while (!goodInput) {
			try {
				String rawInput = scanner.nextLine();
				input = simpleDateFormat.parse(rawInput);
				goodInput = true;
			} catch (ParseException e) {
				System.out.println("mauvais format");
			}
		}
		return input;
	}

}
