package fr.imie.tpjdbc;

import java.util.Date;

import fr.imie.tpjdbc.DAO.IPersonneDAO;
import fr.imie.tpjdbc.DAO.PersonneDAO;
import fr.imie.tpjdbc.DTO.PersonneDTO;

public class TestInsert {

	public TestInsert() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		IPersonneDAO personneDAO = new PersonneDAO();
		PersonneDTO personneDTO = new PersonneDTO();
		personneDTO.setNom("testInsert");
		personneDTO.setPrenom("testInsert");
		personneDTO.setTel("0000000000");
		personneDTO.setDateNaiss(new Date());
		personneDTO = personneDAO.insert(personneDTO);
		System.out.println(personneDTO.getId());

	}

}
