package fr.imie.tpjdbc.DAO;

import java.sql.Connection;
import java.util.List;

import fr.imie.tpjdbc.DTO.PromotionDTO;

public interface IPromotionDAO {
	public abstract PromotionDTO findById(PromotionDTO dto);
	public abstract void delete(PromotionDTO dto);
	List<PromotionDTO> findAll();
	void delete(PromotionDTO dto, Connection connection);

}
