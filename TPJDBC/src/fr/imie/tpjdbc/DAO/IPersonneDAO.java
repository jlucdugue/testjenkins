package fr.imie.tpjdbc.DAO;

import java.util.List;
import fr.imie.tpjdbc.DTO.PersonneDTO;

public interface IPersonneDAO {
	public abstract List<PersonneDTO> findAll();
	public abstract PersonneDTO findById(PersonneDTO dto);
}
