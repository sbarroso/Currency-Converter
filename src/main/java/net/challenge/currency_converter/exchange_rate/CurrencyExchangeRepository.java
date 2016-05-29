package net.challenge.currency_converter.exchange_rate;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyExchangeRepository extends JpaRepository<CurrencyExchangeRate, Long> {
	List<CurrencyExchangeRate> findOneByOriginCurrency(Currency originCurrency);
	
	List<CurrencyExchangeRate> findTop10ByOrderByCreatedDesc();
}