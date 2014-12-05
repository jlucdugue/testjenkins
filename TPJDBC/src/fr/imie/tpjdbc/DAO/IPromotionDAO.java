package fr.imie.tpjdbc.DAO;

import fr.imie.tpjdbc.DTO.PromotionDTO;

public interface IPromotionDAO {
	public abstract PromotionDTO findById(PromotionDTO dto);

}
