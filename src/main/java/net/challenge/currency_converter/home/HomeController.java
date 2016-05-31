package net.challenge.currency_converter.home;

import java.security.Principal;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import net.challenge.currency_converter.exchange_rate.Currency;
import net.challenge.currency_converter.exchange_rate.CurrencyExchangeRepository;
import net.challenge.currency_converter.exchange_rate.FormData;

@Controller
public class HomeController {
	
	@Autowired
	private CurrencyExchangeRepository currencyExchangeRepository;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(Principal principal, Model model) {
		
		//Initialize data that will be displayed on the screen
		model.addAttribute("currencies", Arrays.asList(Currency.values()));
		model.addAttribute("formData", new FormData());
		model.addAttribute("lastQueries", currencyExchangeRepository.findTop10ByOrderByCreatedDesc());
		return principal != null ? "home/homeSignedIn" : "home/homeNotSignedIn";
	}
}
