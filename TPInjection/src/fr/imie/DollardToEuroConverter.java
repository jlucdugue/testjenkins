package fr.imie;

import java.math.BigDecimal;

import javax.inject.Named;


@Named("DtoE")
public class DollardToEuroConverter implements ICurrencyConverter {

	@Override
	public BigDecimal convert(BigDecimal initialValue) {
		//return initialValue.multiply(new BigDecimal(1.15));
		return initialValue.multiply(new BigDecimal("1.15"));

	}

}
