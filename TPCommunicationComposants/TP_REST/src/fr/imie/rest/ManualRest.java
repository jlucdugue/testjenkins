package fr.imie.rest;

import java.io.IOException;
import java.io.Writer;
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
 * Servlet implementation class ManualRest
 */
@WebServlet("/persons")
public class ManualRest extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	SchoolServiceLocal schoolService;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ManualRest() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Writer writer = response.getWriter();
		List<Personne> personnes = schoolService.findPersonAll();
		writer.write("[");
		String listSerialised = "";
		for (Personne personne : personnes) {
			listSerialised = listSerialised.concat((String.format(
					"{\"nom\":\"%s\",\"prenom\":\"%s\"},", personne.getNom(),
					personne.getPrenom())));
		}
		listSerialised = listSerialised.substring(0,
				listSerialised.length() - 1);
		writer.write(listSerialised);
		writer.write("]");
		response.setContentType("application/json");

		// writer.write(schoolService.findPersonAll().toString());
	}
}
