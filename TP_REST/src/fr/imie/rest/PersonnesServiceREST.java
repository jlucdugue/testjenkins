package fr.imie.rest;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import fr.imie.model.Personne;
import fr.imie.service.SchoolServiceLocal;

@Path("personnes")
@Stateless
@Produces({ MediaType.APPLICATION_JSON })
@Consumes({ MediaType.APPLICATION_JSON })
public class PersonnesServiceREST {

	@EJB
	private SchoolServiceLocal schoolService;

	@GET
	public Response getAllPersonnes() {
		List<Personne> retour = schoolService.findPersonAll();
		return Response.ok(retour).build();

	}
	
	@GET
	@Path("/{id}")
	public Response getOnePersonnes(@PathParam("id") Integer id){
		Personne personne = new Personne();
		personne.setId(id);
		Personne retour = schoolService.findPersonById(personne);
		return Response.ok(retour).build();

	}

	@DELETE
	@Path("/{id}")
	public void deletePersonne(@PathParam("id") Integer id) {
		Personne personneToDelete = new Personne();
		personneToDelete.setId(id);
		schoolService.deletePerson(personneToDelete);
	}
	
	@POST
	public Response insertPersonne(Personne personne){
		personne = schoolService.createPerson(personne);
		return Response.ok(personne).build();
	}
	
	@PUT
	@Path("/{id}")
	public Response updatePersonne(Personne personne,@PathParam("id") Integer id){
		personne.setId(id);
		personne = schoolService.updatePerson(personne);
		return Response.ok(personne).build();
	}

}
