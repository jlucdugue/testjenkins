/**
 * 
 */
package fr.imie.formation.poo.tp1;

/**
 * @author imie
 *
 */
public class Launcher {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Person alice = new Person("Alice");
		alice.sayHello();
		
		
		Professor professor = new Professor("tournesol", 10000);
		professor.sayHello();
		
		Student student = new Student("bob");
		student.sayHello();
		Student student2 = new Student("l'eponge");
		student2.sayHello();
		professor.sayHello(student);
		professor.sayHello(student2);

	}

}
