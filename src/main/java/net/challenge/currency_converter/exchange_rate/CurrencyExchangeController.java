package net.challenge.currency_converter.exchange_rate;

import java.security.Principal;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.RestTemplate;

/**
 * Class implementing a controller that will be used to access the OpenExchange API
 * @author Port
 *
 */
@Controller
class CurrencyExchangeController {
	
    @Value("${open.exchange.api.id}")
    private String openExchangeId;
	
	private static final Logger log = LoggerFactory.getLogger(CurrencyExchangeController.class);

    private CurrencyExchangeRepository currencyExchangeRepository;

    @Autowired
    public CurrencyExchangeController(CurrencyExchangeRepository currencyExchangeRepository) {
        this.currencyExchangeRepository = currencyExchangeRepository;
    }

    @RequestMapping(value = "rate/oer", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    public String queryOpenExchangeRate(Principal principal, @ModelAttribute FormData formData, Model model) {
        Assert.notNull(principal);
        
        String origin = formData.getCurrencyOrigin();
        String target = formData.getCurrencyTarget();
        
        Double rate = 1d;
        if (!origin.equals(target)) {
            OpenExchangeData oer = new RestTemplate().getForObject("https://openexchangerates.org/api/historical/2001-02-16.json?app_id="+openExchangeId, OpenExchangeData.class);
            
            Double originToUsd = oer.getRates().get(origin);
            Double targetToUsd = oer.getRates().get(target);
            rate = (1/originToUsd) * targetToUsd;
        }
        
        log.info("source: {} - Target: {} - Rate: {} ", origin, target, rate);

        //Save the required exchange rate to the db
        CurrencyExchangeRate currencyExchangeRate = new CurrencyExchangeRate(Currency.valueOf(origin), Currency.valueOf(target), rate);
        currencyExchangeRepository.save(currencyExchangeRate);
        
        //Add data to the context
        model.addAttribute("currencies", Arrays.asList(Currency.values()));
        model.addAttribute("result", rate);
        model.addAttribute("lastQueries", currencyExchangeRepository.findTop10ByOrderByCreatedDesc());
        return "home/homeSignedIn";
    }

}
