/**
 * 
 */
package fr.imie.formation.poo.tp2;

/**
 * @author imie
 *
 */
public class RegularPolygon extends Shape {

	int sideNumber;
	float radius;
	float sideWidth;

	/**
	 * 
	 */
	public RegularPolygon() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param sideNumber : le nombre de côté
	 * @param radius : le rayon du polygone
	 */
	public RegularPolygon(int sideNumber, float radius) {
		super();
		this.sideNumber = sideNumber;
		this.radius = radius;
		this.sideWidth = (float)(2*radius* Math.sin(Math.PI/sideNumber));
		
	}

	
	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.imie.formation.poo.tp2.Shape#area()
	 */
	@Override
	public float area() {
	    Triangle fakeTriangle =  new Triangle(radius,radius,sideWidth);
		// TODO Auto-generated method stub
		return fakeTriangle.area()*sideNumber;
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.imie.formation.poo.tp2.Shape#perimeter()
	 */
	@Override
	public float perimeter() {
		// TODO Auto-generated method stub
		return sideNumber*sideWidth;
	}
	

}
