package net.challenge.currency_converter.exchange_rate;

/**
 * Class containing the data to be entered by the user on the screen
 * 
 * @author Santiago Barroso
 *
 */
public class FormData {
	
	private String currencyOrigin;
	
	private String currencyTarget;

	public String getCurrencyOrigin() {
		return currencyOrigin;
	}

	public void setCurrencyOrigin(String currencyOrigin) {
		this.currencyOrigin = currencyOrigin;
	}

	public String getCurrencyTarget() {
		return currencyTarget;
	}

	public void setCurrencyTarget(String currencyTarget) {
		this.currencyTarget = currencyTarget;
	}

}
