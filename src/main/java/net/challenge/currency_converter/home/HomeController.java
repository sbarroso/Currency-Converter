package net.challenge.currency_converter.home;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import net.challenge.currency_converter.currency.Currency;
import net.challenge.currency_converter.currency.FormData;

@Controller
public class HomeController {
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(Principal principal, Model model) {
		model.addAttribute("currencies", Arrays.asList(Currency.values()));
		model.addAttribute("formData", new FormData());
		return principal != null ? "home/homeSignedIn" : "home/homeNotSignedIn";
	}
}
