package fr.imie;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class UserList
 */
@WebServlet("/UserList")
public class UserList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserList() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Object userDTOs = session.getAttribute("userDTOs");
		request.setAttribute("users", userDTOs);
		request.getRequestDispatcher("/WEB-INF/userList.jsp").forward(request,
				response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		String idString = request.getParameter("userId");
		Integer id = Integer.valueOf(idString);
		//recherche du user à supprimer
		List<UserDTO> userDTOs = (List<UserDTO>) request.getSession().getAttribute("userDTOs");
		UserDTO userToDelete = null;
		for (UserDTO userDTO : userDTOs) {
			if(userDTO.getId().equals(id)){
				userToDelete= userDTO;
				break;
			}
		}
		//suppression du user = suppression dans la session (même référence)
		if (userToDelete!=null){
			userDTOs.remove(userToDelete);
		}
		//execution de la vue
		request.setAttribute("users", userDTOs);
		request.getRequestDispatcher("/WEB-INF/userList.jsp").forward(request, response);
	}

}
