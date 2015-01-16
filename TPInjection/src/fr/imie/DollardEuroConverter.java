package fr.imie;

import java.math.BigDecimal;

public class DollardEuroConverter implements ICurrencyConverter {

	@Override
	public BigDecimal convert(BigDecimal initialValue) {
		return initialValue.multiply(new BigDecimal(1.15));

	}

}
