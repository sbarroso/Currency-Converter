package net.challenge.currency_converter.exchange_rate;

import java.util.Arrays;
import java.util.List;

public enum Country {
	SPAIN, FRANCE, ITALY, GERMANY, USA, CANADA, POLAND;
	
	public static List<String> findCountryNames() {
		String[] countries = Arrays.stream(Country.class.getEnumConstants()).map(Enum::name).toArray(String[]::new);
		return Arrays.asList(countries);
	}
}
