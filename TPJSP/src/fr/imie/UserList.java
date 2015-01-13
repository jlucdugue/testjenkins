package fr.imie;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.imie.tpjdbc.AbstractFactory;
import fr.imie.tpjdbc.ConcreteFactory;
import fr.imie.tpjdbc.DTO.PersonneDTO;
import fr.imie.tpjdbc.service.IEcoleService;

/**
 * Servlet implementation class UserList
 */
@WebServlet("/UserList")
public class UserList extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IEcoleService ecoleService =  new ConcreteFactory().createEcoleService();

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

		request.setAttribute("users", ecoleService.findAllPersonne());
		request.getRequestDispatcher("/WEB-INF/userList.jsp").forward(request,
				response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//Le Post ne sert que pour la suppression, voir le Form pour update et create
		String idString = request.getParameter("userId");
		Integer id = Integer.valueOf(idString);
		//obtention du service
		AbstractFactory factory = new ConcreteFactory();
		IEcoleService ecoleService = factory.createEcoleService();
		//creation du dto à supprimer
		PersonneDTO personneToDelete=new PersonneDTO();
		personneToDelete.setId(id);
		//appel service
		ecoleService.deletePersonne(personneToDelete);
		//repeuplement de la liste à afficher
		List<PersonneDTO> personneDTOs = ecoleService.findAllPersonne();
		//execution de la vue
		request.setAttribute("users", personneDTOs);
		request.getRequestDispatcher("/WEB-INF/userList.jsp").forward(request, response);
	}

}
