package vttp2022.app.ssfworkshop17.controller;



import java.math.BigDecimal;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import vttp2022.app.ssfworkshop17.model.Currency;
import vttp2022.app.ssfworkshop17.model.Query;
import vttp2022.app.ssfworkshop17.service.CurrencyAPIService;

@Controller
@RequestMapping(path="/exchange")
public class CurrencyExchangeController {
    private static final Logger logger = LoggerFactory.getLogger(CurrencyExchangeController.class);


    @Autowired
    private CurrencyAPIService currySvc;

    @GetMapping
    public String exchange(@RequestParam(required = true) String to,
    @RequestParam(required = true) String from, @RequestParam(required = true) String amount, Model model){
        Query q = new Query();
        q.setTo(to);
        q.setFrom(from);
        q.setAmount(new BigDecimal(amount));
        Currency curry = currySvc.convertExchangeRates(q);
        currySvc.save(curry);
        return null;
    }

    @GetMapping("/{id}")
    public String getExchangeRecord (Model model
                                     ,@PathVariable(value = "id") String id){
        Currency c = currySvc.load(id);
        model.addAttribute("currency", c);
        return "exchange";
        }
        
        
        
        
        
        
        
        
        // Optional<Currency> optCurry = currySvc.convertExchangeRates(q);
        // if(optCurry.isEmpty()){
        //     model.addAttribute("currency", new Currency());
        //     return "exchange";
        // }
        // logger.info("<<<<"+q.getFrom()+"****"+q.getTo());
        // model.addAttribute("currency", optCurry.get());
        // model.addAttribute("fromCurry", q.getFrom());
        // model.addAttribute("toCurry",q.getTo());
        // System.out.println(optCurry.get().getSuccess());
        // return "exchange";
    
}
