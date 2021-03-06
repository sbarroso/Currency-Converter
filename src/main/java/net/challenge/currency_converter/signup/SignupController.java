package net.challenge.currency_converter.signup;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import net.challenge.currency_converter.account.Account;
import net.challenge.currency_converter.account.AccountService;
import net.challenge.currency_converter.exchange_rate.Country;
import net.challenge.currency_converter.web.MessageHelper;

@Controller
public class SignupController {

    private static final String SIGNUP_VIEW_NAME = "signup/signup";

	@Autowired
	private AccountService accountService;
	
	@RequestMapping(value = "signup")
	public String signup(Model model) {
		model.addAttribute(new SignupForm());
		model.addAttribute("countries", Country.findCountryNames());
        return SIGNUP_VIEW_NAME;
	}
	
	@RequestMapping(value = "signup", method = RequestMethod.POST)
	public String signup(@Valid @ModelAttribute SignupForm signupForm, Errors errors, RedirectAttributes ra, Model model) {
		if (errors.hasErrors()) {
			model.addAttribute("countries", Country.findCountryNames());
			return SIGNUP_VIEW_NAME;
		}
		
		try {
			Account account = accountService.save(signupForm.createAccount());
			accountService.signin(account);
			MessageHelper.addSuccessAttribute(ra, "signup.success");
		}catch (Exception exception) {
			MessageHelper.addErrorAttribute(ra, "signup.error");
		}
		
		return "redirect:/";
	}
}
