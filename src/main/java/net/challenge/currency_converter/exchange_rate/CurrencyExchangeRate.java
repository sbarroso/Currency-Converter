package net.challenge.currency_converter.exchange_rate;

import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Class that contains all the info needed to keep in the database relating the
 * exchange rate between 2 currencies
 *
 * @author Santiago Barroso
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "currency")
public class CurrencyExchangeRate implements java.io.Serializable {
	
	@Id
	@GeneratedValue
	private Long id;

	private Currency originCurrency;
	
	private Currency targetCurrency;

	private Double rate;

	private Instant created = Instant.now();

    protected CurrencyExchangeRate() {

	}

	public CurrencyExchangeRate(Currency originCurrency, Currency targetCurrency, Double rate) {
		super();
		this.originCurrency = originCurrency;
		this.targetCurrency = targetCurrency;
		this.rate = rate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Currency getOriginCurrency() {
		return originCurrency;
	}

	public void setOriginCurrency(Currency originCurrency) {
		this.originCurrency = originCurrency;
	}

	public Currency getTargetCurrency() {
		return targetCurrency;
	}

	public void setTargetCurrency(Currency targetCurrency) {
		this.targetCurrency = targetCurrency;
	}

	public Double getRate() {
		return rate;
	}

	public void setRate(Double rate) {
		this.rate = rate;
	}

	public Instant getCreated() {
		return created;
	}

	public void setCreated(Instant created) {
		this.created = created;
	}

}
