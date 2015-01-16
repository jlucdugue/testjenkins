package fr.imie;

import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.inject.Named;

@Named("EtoD")
public class EuroToDollardConverter implements ICurrencyConverter {

	@Override
	public BigDecimal convert(BigDecimal initialValue) {
		return initialValue.divide(new BigDecimal("1.15"), 20, RoundingMode.HALF_EVEN);
	}

}
