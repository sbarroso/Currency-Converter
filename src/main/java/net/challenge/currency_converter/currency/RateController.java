package net.challenge.currency_converter.currency;

import java.security.Principal;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.RestTemplate;

@Controller
class RateController {
	
	private static final Logger log = LoggerFactory.getLogger(RateController.class);

    private RateRepository rateRepository;

    @Autowired
    public RateController(RateRepository rateRepository) {
        this.rateRepository = rateRepository;
    }

    @RequestMapping(value = "rate/current", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    public Rate currentRate(Principal principal) {
        Assert.notNull(principal);
        return rateRepository.findOne(1L);
    }
    
    @RequestMapping(value = "rate/history", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    public List<Rate> rateHistory(Principal principal) {
        Assert.notNull(principal);
        return rateRepository.findOneByOriginCurrency(Currency.EUR);
    }
    
    @RequestMapping(value = "rate/all", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    public List<Rate> rateAll(Principal principal, Model model) {
        Assert.notNull(principal);
        
        return rateRepository.findAll();
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
        	RestTemplate restTemplate = new RestTemplate();
            OpenExchangeRates oer = restTemplate.getForObject("https://openexchangerates.org/api/historical/2001-02-16.json?app_id="+appId, OpenExchangeRates.class);
            
            Double originToUsd = oer.getRates().get(origin);
            Double targetToUsd = oer.getRates().get(target);
            rate = (1/originToUsd) * targetToUsd;
        }
        
        log.info("source: {} - Target: {} - Rate: {} ", origin, target, rate);

        model.addAttribute("currencies", Arrays.asList(Currency.values()));
        model.addAttribute("result", rate);
        
        Rate rate2 = new Rate(Currency.valueOf(origin), Currency.valueOf(target), rate);
        rateRepository.save(rate2);
        
        model.addAttribute("lastQueries", rateRepository.findTop10ByOrderByCreatedDesc());
        
        return "home/homeSignedIn";
    }
    
    private String appId = "e538209d47724fa7a2bd7efd8da19883";
    
    @RequestMapping(value = "rate/oer2", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    public OpenExchangeRates queryOpenExchangeRate2(Principal principal) {
        Assert.notNull(principal);
        
        RestTemplate restTemplate = new RestTemplate();
        log.info(restTemplate.getForObject("https://openexchangerates.org/api/historical/2001-02-16.json?app_id="+appId, OpenExchangeRates.class).toString());
        return restTemplate.getForObject("https://openexchangerates.org/api/historical/2001-02-16.json?app_id="+appId, OpenExchangeRates.class);
    }
}
