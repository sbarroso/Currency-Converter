package net.challenge.currency_converter.currency;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RateRepository extends JpaRepository<Rate, Long> {
	List<Rate> findOneByOriginCurrency(Currency originCurrency);
}