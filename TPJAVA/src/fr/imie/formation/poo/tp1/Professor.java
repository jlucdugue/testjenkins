package fr.imie.formation.poo.tp1;

public class Professor extends Person {
	
	private int salary;

	public Professor(String nameParam,int salaryParam) {
		super(nameParam);
		this.salary= salaryParam;
	}

	
	public void sayHello(Student student) {
		System.out.format(" hello %s, i am your professor and my name is %s\n",student.getName(),this.getName());
	}
	
	
	
}
