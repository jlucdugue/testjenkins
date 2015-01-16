package fr.imie;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;

@SessionScoped
public class Historic implements IHistoric,Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5956496086269663692L;
	private List<DataGui> dataGuis = new ArrayList<DataGui>();

	@Override
	public void addHisto(DataGui dataGui) {
		dataGuis.add(dataGui);
	}
	
	@Override
	public void addHisto(BigDecimal initialValue, BigDecimal finalValue) {
		DataGui dataGui = new DataGui();
		dataGui.setInitialValue(initialValue);
		dataGui.setFinalValue(finalValue);
		addHisto(dataGui);
	}

	@Override
	public List<DataGui> getHistos() {
		return dataGuis;
	}

}
