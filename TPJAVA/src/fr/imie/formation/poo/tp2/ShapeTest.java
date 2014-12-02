/**
 * 
 */
package fr.imie.formation.poo.tp2;

import javax.management.RuntimeErrorException;

/**
 * @author imie
 *
 */
public class ShapeTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Rectangle rectangle = null;
		Triangle triangle = null;
		try {
			rectangle = new Rectangle(10, 20);
			printInformationOf(rectangle);
			Circle circle = new Circle(10);
			printInformationOf(circle);
			Carre carre = new Carre(20);
			printInformationOf(carre);
			triangle = new Triangle(10, 15, 30);
			printInformationOf(triangle);
			RegularPolygon regularPolygon = new RegularPolygon(100, 1);
			printInformationOf(regularPolygon);
		} catch (IMIEException e) {
			System.out.println(e.getMessage());
		} catch (IllegalArgumentException e) {
			 //System.out.println("exception d'initialisation des Shapes");
			 throw new RuntimeException("exception technique  : le developpeur doit les croissants",e);

		}
	}

	public static void printInformationOf(Shape shape) throws IMIEException {
		try {
			System.out.format("perimetre de %s :%f\n", shape.getClass()
					.getSimpleName(), shape.perimeter());
			System.out.format("aire de %s :%f\n", shape.getClass()
					.getSimpleName(), shape.area());
		} catch (NullPointerException e) {
			throw new IMIEException("impossible d'afficher un objet vide", e);
		}
	}

}
