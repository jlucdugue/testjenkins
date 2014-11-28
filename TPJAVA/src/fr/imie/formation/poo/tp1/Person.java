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
public class Person {
	private String name;
	
	/**
	 * 
	 */
	public Person(String nameParam){
		this.name = nameParam;
	}
	

	/**
	 * @param toto : blea bla
	 */
	public void sayHello(){
		System.out.format("my name is %s\n",this.name);
	}


	public String getName() {
		return name;
	}
	
	
}
