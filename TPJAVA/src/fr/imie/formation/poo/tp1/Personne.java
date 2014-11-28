/**
 * 
 */
package fr.imie.formation.poo.tp1;

/**
 * @author imie
 *
 */
/**
 * @author imie
 *
 */
public class Personne {
	private String name;

	/**
	 * 
	 */
	public Personne() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 
	 */
	public Personne(String nameParam){
		this.name = nameParam;
	}
	

	/**
	 * @param toto : blea bla
	 */
	public void sayHello( String toto){
		System.out.format("my name is %s",this.name);
	}
}
