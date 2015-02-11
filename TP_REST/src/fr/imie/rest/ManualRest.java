package fr.imie.rest;

import java.io.IOException;
import java.io.Writer;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
@WebServlet("/persons/*")
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
			listSerialised = listSerialised
					.concat((String
							.format("{\"id\":\"%s\",\"nom\":\"%s\",\"prenom\":\"%s\"},",
									personne.getId(), personne.getNom(),
									personne.getPrenom())));
		}
		listSerialised = listSerialised.substring(0,
				listSerialised.length() - 1);
		writer.write(listSerialised);
		writer.write("]");
		response.setContentType("application/json");

		// writer.write(schoolService.findPersonAll().toString());
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String uri = req.getRequestURI();
		Matcher m = Pattern.compile("\\/persons\\/(\\S*)").matcher(uri);
		Integer id = null;
		if (m.find()) {
			String idString = m.group(1);
			try {
				id = Integer.valueOf(idString);
			} catch (Exception e) {
				//id still null and 400 is sent
			}
		}
		if (id != null) {
			Personne personne = new Personne();
			personne.setId(id);
			try{
			schoolService.deletePerson(personne);
			} catch (Exception e){
				resp.sendError(400, "unknowned person");
			}
		} else {
			resp.sendError(400, "not a valid url format");
		}

	}
}
