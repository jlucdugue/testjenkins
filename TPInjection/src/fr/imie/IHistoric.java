package fr.imie;

import java.math.BigDecimal;
import java.util.List;

public interface IHistoric {

	List<DataGui> getHistos();

	void addHisto(DataGui dataGui);

	void addHisto(BigDecimal initialValue, BigDecimal finalValue);

}
