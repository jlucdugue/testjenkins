package fr.imie.presentation;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.imie.model.Personne;
import fr.imie.model.Promotion;
import fr.imie.service.SchoolServiceLocal;

/**
 * Servlet implementation class OneUser
 */
@WebServlet("/PersonForm")
public class PersonForm extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	SchoolServiceLocal personneService;

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
			personne = personneService.findPersonById(personne);
			request.setAttribute("personne", personne);
		}
		List<Promotion> promotions = personneService.findAllClass();
		request.setAttribute("classes", promotions);
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
		String classIdString = request.getParameter("class");
		Promotion promotion = null;
		if (!classIdString.isEmpty()) {
			Integer classId = Integer.valueOf(classIdString);
			promotion = new Promotion();
			promotion.setId(classId);
		}
		
		
		Personne personne = new Personne();
		if (id != null) {
			personne.setId(id);
		}
		personne.setNom(lastName);
		personne.setPrenom(firstName);
		personne.setPromotion(promotion);
		
		if (id != null) {
			personneService.updatePerson(personne);
		} else {
			personneService.createPerson(personne);
		}

		response.sendRedirect("PersonList");

	}

}
