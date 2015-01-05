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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idString = request.getParameter("id");
		Integer id = Integer.valueOf(idString);
		List<UserDTO> userDTOs = (List<UserDTO>) request.getSession().getAttribute("userDTOs");
		UserDTO userSelected=null;
		for (UserDTO userDTO : userDTOs) {
			if(userDTO.getId()==id){
				userSelected=userDTO;
				break;
			}
		}
		request.setAttribute("user", userSelected);
		request.getRequestDispatcher("/WEB-INF/userForm.jsp").forward(request, response);
	}

}
