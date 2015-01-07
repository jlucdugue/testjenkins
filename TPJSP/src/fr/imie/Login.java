package fr.imie;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Login() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request,
				response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String login = request.getParameter("loginInput");
		String passw = request.getParameter("passwordInput");
		List<UserDTO> userDTOs = (List<UserDTO>) request.getSession()
				.getAttribute("userDTOs");
		UserDTO securedUser = null;
		for (UserDTO userDTO : userDTOs) {
			if (userDTO.getLogin().compareTo(login) == 0
					&& userDTO.getPassword().compareTo(passw) == 0) {
				securedUser= userDTO;
			}
		}
		
		if (securedUser!=null){
			request.getSession().setAttribute("connectedUser", securedUser);
		}else{
			request.setAttribute("mainErrorMessage", "mauvaise connection");
			request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
		}

	}

}
