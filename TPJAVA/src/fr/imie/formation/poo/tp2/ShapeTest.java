/**
 * 
 */
package fr.imie.formation.poo.tp2;

import java.awt.image.RescaleOp;

/**
 * @author imie
 *
 */
public class ShapeTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Rectangle rectangle = new Rectangle(10,20);
		System.out.format("aire du rectangle :%f\n",rectangle.area());
		printPerimeterOf(rectangle);
		Circle circle = new Circle(10);
		System.out.format("aire du cercle :%f\n",circle.area());
		printPerimeterOf(circle);
		Carre carre = new Carre(20);
		printPerimeterOf(carre);
	}
	
	public static void printPerimeterOf(Shape shape){
		System.out.format("aire de %s :%f\n",shape.getClass().getSimpleName(), shape.perimeter());
	}

}
