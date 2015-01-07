package fr.imie;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UserForm
 */
@WebServlet("/UserForm")
public class UserForm extends HttpServlet {
	private static final long serialVersionUID = 1L;

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
			List<UserDTO> userDTOs = (List<UserDTO>) request.getSession()
					.getAttribute("userDTOs");
			UserDTO userSelected = null;
			for (UserDTO userDTO : userDTOs) {
				if (userDTO.getId().equals(id)) {
					userSelected = userDTO;
					break;
				}
			}
			request.setAttribute("user", userSelected);
		}
		request.getRequestDispatcher("/WEB-INF/userForm.jsp").forward(request,
				response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//
		//
		// String idString = request.getParameter("id");
		// Integer id = null;
		// if ("create".compareTo(idString) != 0) {
		// try {
		// id = Integer.valueOf(idString);
		// } catch (NumberFormatException e) {
		// throw new IllegalArgumentException("requete non valide", e);
		// }
		// }

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
			UserDTO userDTOToInsert = new UserDTO();
			userDTOToInsert
					.setId((int) (Math.floor((double) (Math.random() * 1000))));
			userDTOToInsert.setLogin(request.getParameter("login"));
			userDTOToInsert.setPassword(request.getParameter("password"));
			List<UserDTO> userDTOs = (List<UserDTO>) request.getSession()
					.getAttribute("userDTOs");
			userDTOs.add(userDTOToInsert);

		}
		response.sendRedirect("UserList");
	}
}
