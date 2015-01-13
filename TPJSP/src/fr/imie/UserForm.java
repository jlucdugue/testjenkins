package fr.imie;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.imie.tpjdbc.AbstractFactory;
import fr.imie.tpjdbc.ConcreteFactory;
import fr.imie.tpjdbc.DTO.PersonneDTO;
import fr.imie.tpjdbc.service.IEcoleService;

/**
 * Servlet implementation class UserForm
 */
@WebServlet("/UserForm")
public class UserForm extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private IEcoleService ecoleService =  new ConcreteFactory().createEcoleService();

	
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserForm() {
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
		Integer id = null;
		if ("create".compareTo(idString) != 0) {
			try {
				id = Integer.valueOf(idString);
			} catch (NumberFormatException e) {
				throw new IllegalArgumentException("requete non valide", e);
			}
		}

		if (id != null) {
			PersonneDTO personneDTO = new PersonneDTO();
			personneDTO.setId(id);
			personneDTO = ecoleService.findPersonneById(personneDTO);
			request.setAttribute("user", personneDTO);
		}
		request.getRequestDispatcher("/WEB-INF/userForm.jsp").forward(request,
				response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		if (request.getParameter("update") != null) {
			String idString = request.getParameter("id");
			Integer id = null;

			try {
				id = Integer.valueOf(idString);
			} catch (NumberFormatException e) {
				throw new IllegalArgumentException("id non valide", e);
			}

			List<UserDTO> userDTOs = (List<UserDTO>) request.getSession()
					.getAttribute("userDTOs");
			UserDTO userSelected = null;
			for (UserDTO userDTO : userDTOs) {
				if (userDTO.getId().equals(id)) {
					userSelected = userDTO;
					break;
				}
			}

			userSelected.setLogin(request.getParameter("login"));
			userSelected.setPassword(request.getParameter("password"));

		} else if (request.getParameter("create") != null) {
			PersonneDTO personneDTOToInsert = new PersonneDTO();
			personneDTOToInsert.setNom(request.getParameter("login"));
			personneDTOToInsert.setPassword(request.getParameter("password"));
			ecoleService.insertPersonne(personneDTOToInsert);

		}
		response.sendRedirect("UserList");
	}
}
