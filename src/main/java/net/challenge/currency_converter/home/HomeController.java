package net.challenge.currency_converter.home;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(Principal principal, Model model) {
//		model.addAttribute("currencies", Arrays.asList(Currency.values()));
//		model.addAttribute("formData", new FormData());
		return principal != null ? "home/homeSignedIn" : "home/homeNotSignedIn";
	}
}
