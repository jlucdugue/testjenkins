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
import fr.imie.service.SchoolServiceLocal;

/**
 * Servlet implementation class AllPersonne
 */
@WebServlet("/PersonList")
public class PersonList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	SchoolServiceLocal personneService;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PersonList() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		List<Personne> findAll = personneService.findPersonAll();
		request.setAttribute("persons", findAll);
		request.getRequestDispatcher("/WEB-INF/personList.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
