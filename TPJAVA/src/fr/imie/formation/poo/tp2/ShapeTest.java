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
		System.out.format("périmètre du rectangle :%f\n",rectangle.perimeter());
		System.out.format("aire du rectangle :%f\n",rectangle.area());
	}

}
