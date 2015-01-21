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
 * Servlet implementation class OneUser
 */
@WebServlet("/PersonForm")
public class PersonForm extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	PersonneServiceLocal personneService;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PersonForm() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String idString = request.getParameter("id");
		if (idString != null) {
			Integer id = Integer.valueOf(idString);
			Personne personne = new Personne();
			personne.setId(id);
			personne = personneService.findById(personne);
			request.setAttribute("personne", personne);
		}
		request.getRequestDispatcher("/WEB-INF/personForm.jsp").forward(
				request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String idString = request.getParameter("id");
		Integer id = null;
		if (idString != null && !idString.isEmpty()) {
			id = Integer.valueOf(idString);
		}
		String lastName = request.getParameter("lastName");
		String firstName = request.getParameter("firstName");
		Personne personne = new Personne();
		if (id != null) {
			personne.setId(id);
		}
		personne.setNom(lastName);
		personne.setPrenom(firstName);

		if (id != null) {
			personneService.update(personne);
		} else {
			personneService.create(personne);
		}

		response.sendRedirect("PersonList");

	}

}
