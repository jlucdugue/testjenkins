/**
 * 
 */
package fr.imie.tpjdbc.presentation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import fr.imie.tpjdbc.DTO.PersonneDTO;
import fr.imie.tpjdbc.DTO.PromotionDTO;
import fr.imie.tpjdbc.service.EcoleService;
import fr.imie.tpjdbc.service.IEcoleService;

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
		//IPersonneDAO personneDAO = new PersonneDAO();
		//IPromotionDAO promotionDAO = new PromotionDAO();

		IEcoleService service = new EcoleService();
		
		// TP1
		Boolean endAppli = false;
		List<PersonneDTO> personneDTOs = null;
		Integer input = null;

		while (!endAppli) {

			System.out.println("-------------------------");
			System.out.println("1 : lister tous les users");
			System.out.println("2 : detail d'un user     ");
			System.out.println("3 : insérer un user      ");
			System.out.println("4 : supprimer un user    ");
			System.out.println("5 : modifier un user     ");
			System.out.println("6 : supprimer une promo  ");
			System.out.println("-------------------------");

			Integer menuNumber = inputInteger();
			PersonneDTO selectedPersonne = null;
			if (menuNumber == 0) {
				endAppli = true;
				break;
			}

			switch (menuNumber) {
			case 1:
				personneDTOs = viewPersonne(service);
				break;

			case 2:
				// TP 2
				selectedPersonne = viewAndSelectPersonne(service);
				System.out
						.format("nom : %s \nprenom :  %s\ndateNaiss : %s\ntel %s\npromotion : %s\n\n",
								selectedPersonne.getNom(), selectedPersonne
										.getPrenom(), selectedPersonne
										.getDateNaiss(), selectedPersonne
										.getTel(), selectedPersonne
										.getPromotionDTO().getLibelle());
				break;
			case 3:

				PersonneDTO personneDTO = new PersonneDTO();
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
				//personneDTO = personneDAO.insert(personneDTO);
				personneDTO = service.insertPersonne(personneDTO);
				
				System.out.format("nouvel id inséré : %s\n",
						personneDTO.getId());
				break;

			case 4:

				selectedPersonne = viewAndSelectPersonne(service);
				//personneDAO.delete(selectedPersonne);
				service.deletePersonne(selectedPersonne);
				break;

			case 5:

				selectedPersonne = viewAndSelectPersonne(service);
				//selectedPersonne = personneDAO.findById(selectedPersonne);
				selectedPersonne = service.findPersonneById(selectedPersonne);				
				System.out.format("nom :  %s", selectedPersonne.getNom());
				if (questionModif()) {
					selectedPersonne.setNom(scanner.nextLine());
				}

				System.out.format("prenom :  %s", selectedPersonne.getPrenom());
				if (questionModif()) {
					selectedPersonne.setPrenom(scanner.nextLine());
				}

				System.out.format("date de naisance :  %s",
						selectedPersonne.getDateNaiss());
				if (questionModif()) {
					selectedPersonne.setDateNaiss(inputDate());
				}

				System.out.format("tel :  %s", selectedPersonne.getTel());
				if (questionModif()) {
					selectedPersonne.setTel(scanner.nextLine());
				}

				//personneDAO.update(selectedPersonne);
				service.updatePersonne(selectedPersonne);

				break;
			case 6:
				//List<PromotionDTO> promotionDTOs = promotionDAO.findAll();
				List<PromotionDTO> promotionDTOs = service.findAllPromotions();

				Integer numLigne = 1;

				for (PromotionDTO promotionDTO : promotionDTOs) {
					System.out.format("%d : %s \n", numLigne++,
							promotionDTO.getLibelle());
				}

				input = null;
				input = inputInteger();
				PromotionDTO selectedPromotion =  promotionDTOs.get(input - 1);
				//promotionDAO.delete(selectedPromotion);
				service.deletePromotion(selectedPromotion);
				
				
				
				break;
			default:
				break;
			}
		}

		System.out.println("End");

	}

	private Boolean questionModif() {
		Boolean retour = false;
		System.out.print(" || modifier le champ o/n : ");
		String input = scanner.nextLine();
		if (input.compareTo("o") == 0) {
			retour = true;
		}
		System.out.println();
		return retour;
	}

	private List<PersonneDTO> viewPersonne(IEcoleService ecoleService) {
		List<PersonneDTO> personneDTOs;
		personneDTOs = ecoleService.findAllPersonne();
		Integer numLigne = 1;

		for (PersonneDTO personneDTO : personneDTOs) {
			System.out.format("%d : %s | %s | %s\n", numLigne++, personneDTO
					.getNom(), personneDTO.getPrenom(), personneDTO
					.getPromotionDTO() != null ? personneDTO.getPromotionDTO()
					.getLibelle() : "");
		}
		return personneDTOs;

	}

	private PersonneDTO viewAndSelectPersonne(IEcoleService ecoleService) {

		List<PersonneDTO> personneDTOs = viewPersonne(ecoleService);

		Integer input = null;
		input = inputInteger();
		PersonneDTO selectedPersonne = personneDTOs.get(input - 1);
		return selectedPersonne;
	}

	private Integer inputInteger() {
		Boolean goodInput = false;
		Integer input = null;
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
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Boolean goodInput = false;
		Date input = null;
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
