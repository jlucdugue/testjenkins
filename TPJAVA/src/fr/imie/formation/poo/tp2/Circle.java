/**
 * 
 */
package fr.imie.formation.poo.tp2;

/**
 * @author imie
 *
 */
public class Circle extends Shape {

	float radius;
	
	/**
	 * 
	 */
	public Circle() {
		// TODO Auto-generated constructor stub
	}
	
	

	/**
	 * @param radius
	 */
	public Circle(float radius) {
		super();
		this.radius = radius;
	}



	/* (non-Javadoc)
	 * @see fr.imie.formation.poo.tp2.Shape#area()
	 */
	@Override
	public float area() {
		//precision decrease
		return (float)(Math.PI*Math.pow(radius,2));
	}

	/* (non-Javadoc)
	 * @see fr.imie.formation.poo.tp2.Shape#perimeter()
	 */
	@Override
	public float perimeter() {
		//voluntary precision decrease
		return (float)(Math.PI*radius*2);
	}

}
