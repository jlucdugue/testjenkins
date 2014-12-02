/**
 * 
 */
package fr.imie.formation.poo.tp2;

/**
 * @author imie
 *
 */
public class Triangle extends Shape {

	private float side1;
	private float side2;
	private float side3;
	
	/**
	 * 
	 */
	public Triangle() {
		// TODO Auto-generated constructor stub
	}
	
	

	/**
	 * @param side1
	 * @param side2
	 * @param side3
	 */
	public Triangle(float side1, float side2, float side3) {
		super();
		if(side1>side2+side3 || side2>side1+side3 || side3 > side2+side1){
			throw new IllegalArgumentException("un côté ne peut pas être plus grand que la somme des deux autres");
		}
		
		this.side1 = side1;
		this.side2 = side2;
		this.side3 = side3;
	}



	/* (non-Javadoc)
	 * @see fr.imie.formation.poo.tp2.Shape#area()
	 */
	@Override
	public float area() {
		float perimeter = perimeter();
		float retour = (float)(Math.sqrt(perimeter*(perimeter-side1)*(perimeter-side2)*(perimeter-side3)/16));
		return retour;
	}

	/* (non-Javadoc)
	 * @see fr.imie.formation.poo.tp2.Shape#perimeter()
	 */
	@Override
	public float perimeter() {
		float retour = side1+side2+side3;
		return retour;
	}

}
