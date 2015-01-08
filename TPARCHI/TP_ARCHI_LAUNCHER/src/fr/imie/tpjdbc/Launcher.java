package fr.imie.tpjdbc;

import fr.imie.tpjdbc.presentation.IPresentation;
import fr.imie.tpjdbc.presentation.Presentation;

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
	 * différentes requêtes. recherche des personnes en utilisant un statement +
	 * executeQuery. recherche d'une personne en construisant dynamiquement la
	 * requête + executeQuery. recherche d'une personne en utilisant un
	 * preparedStatement + Execute Query. libération des ressources dans le
	 * finally
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		IPresentation presentation = new Presentation();
		presentation.start();
	}

}
