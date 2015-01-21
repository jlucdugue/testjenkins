package fr.imie.presentation;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.imie.model.Personne;
import fr.imie.service.PersonneServiceLocal;

/**
 * Servlet implementation class PersonDelete
 */
@WebServlet("/PersonDelete")
public class PersonDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@EJB
	PersonneServiceLocal personneService;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PersonDelete() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idString = request.getParameter("id");
		Integer id = Integer.valueOf(idString);
		Personne personne = new Personne();
		personne.setId(id);
		personneService.delete(personne);
		response.sendRedirect("PersonList");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
