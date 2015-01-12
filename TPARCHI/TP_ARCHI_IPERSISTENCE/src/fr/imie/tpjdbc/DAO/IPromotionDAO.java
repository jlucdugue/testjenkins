package fr.imie.tpjdbc.DAO;

import java.util.List;

import fr.imie.tpjdbc.Itransactional;
import fr.imie.tpjdbc.DTO.PromotionDTO;

public interface IPromotionDAO extends Itransactional {
	public abstract PromotionDTO findById(PromotionDTO dto);

	public abstract void delete(PromotionDTO dto);

	public abstract List<PromotionDTO> findAll();

}
