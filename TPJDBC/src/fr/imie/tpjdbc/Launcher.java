package fr.imie.tpjdbc;

import java.util.Scanner;

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

	private static Scanner scanner= new Scanner(System.in);
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
		//Scanner scanner =  new Scanner(System.in);
		IPresentation presentation = new Presentation();
		presentation.start();
	}

}
