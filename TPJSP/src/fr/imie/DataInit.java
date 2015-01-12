package fr.imie;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import fr.imie.tpjdbc.AbstractFactory;
import fr.imie.tpjdbc.ConcreteFactory;
import fr.imie.tpjdbc.DTO.PersonneDTO;
import fr.imie.tpjdbc.service.IEcoleService;

/**
 * Application Lifecycle Listener implementation class DataInit
 *
 */
@WebListener
public class DataInit implements HttpSessionListener {

    /**
     * Default constructor. 
     */
    public DataInit() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see HttpSessionListener#sessionCreated(HttpSessionEvent)
     */
    public void sessionCreated(HttpSessionEvent sessionEvent)  { 
//         UserDTO userDTO1 = new UserDTO();
//         userDTO1.setId(1);
//         userDTO1.setLogin("peter");
//         userDTO1.setPassword("spiderman");
//         
//         UserDTO userDTO2 = new UserDTO();
//         userDTO2.setId(2);
//         userDTO2.setLogin("clark");
//         userDTO2.setPassword("superman");
//         
//         List<UserDTO> userDTOs = new ArrayList<UserDTO>();
//         userDTOs.add(userDTO1);
//         userDTOs.add(userDTO2);
         
         AbstractFactory factory = new ConcreteFactory();
         IEcoleService ecoleService = factory.createEcoleService();
         List<PersonneDTO> personnes = ecoleService.findAllPersonne();
         sessionEvent.getSession().setAttribute("userDTOs", personnes);
    }

	/**
     * @see HttpSessionListener#sessionDestroyed(HttpSessionEvent)
     */
    public void sessionDestroyed(HttpSessionEvent arg0)  { 
         // TODO Auto-generated method stub
    }
	
}
