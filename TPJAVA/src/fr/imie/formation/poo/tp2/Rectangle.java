/**
 * 
 */
package fr.imie.formation.poo.tp2;

/**
 * @author imie
 *
 */
public class Rectangle extends Shape {

	private float width;
	private float height;
	
	/**
	 * 
	 */
	public Rectangle() {
		// TODO Auto-generated constructor stub
	}
	
	

	/**
	 * @param width
	 * @param height
	 */
	public Rectangle(float width, float height) {
		super();
		this.width = width;
		this.height = height;
	}



	@Override
	public float area() {
		return height*width;
	}

	@Override
	public float perimeter() {
		return (height+width)*2f;
	}

}
