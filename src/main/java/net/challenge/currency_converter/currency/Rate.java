package net.challenge.currency_converter.currency;

import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "currency")
public class Rate implements java.io.Serializable {
	
	@Id
	@GeneratedValue
	private Long id;

	private Currency originCurrency;
	
	private Currency targetCurrency;

	private Double rate;

	private Instant created = Instant.now();

    protected Rate() {

	}

	public Rate(Currency originCurrency, Currency targetCurrency, Double rate) {
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
