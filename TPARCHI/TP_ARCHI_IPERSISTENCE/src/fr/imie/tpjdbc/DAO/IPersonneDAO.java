package fr.imie.tpjdbc.DAO;

import java.sql.Connection;
import java.util.List;

import fr.imie.tpjdbc.Itransactional;
import fr.imie.tpjdbc.DTO.PersonneDTO;

public interface IPersonneDAO extends Itransactional {
	public abstract List<PersonneDTO> findAll();
	public abstract PersonneDTO findById(PersonneDTO dto);
	public abstract PersonneDTO insert(PersonneDTO dto);
	public abstract PersonneDTO update(PersonneDTO dto);
	public abstract void delete(PersonneDTO dto);
	public abstract PersonneDTO update(PersonneDTO dto, Connection connectionCaller);
	public abstract List<PersonneDTO> findByDTO(PersonneDTO findParameter);
}
