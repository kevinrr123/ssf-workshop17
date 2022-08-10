package vttp2022.app.ssfworkshop17.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import vttp2022.app.ssfworkshop17.model.Currency;
import vttp2022.app.ssfworkshop17.model.Query;
import vttp2022.app.ssfworkshop17.service.CurrencyAPIService;

@Controller
public class IndexPageController {
    
    @Autowired  
    private CurrencyAPIService currySvc;

    @GetMapping("/")
    public String showIndexPage(Model model){
        Currency c = new Currency();
        Query q = new Query();
        c.setQuery(q);
        //lsCountryCode(model);
        model.addAttribute("currency", c);
        return "index";
    }

    // public void lsCountryCode(Model model){
    //     Map<String, String>lsOfGeoCode = currySvc.getLsOfGeoCode();
    //     List<String> lsOfCountryCode = Arrays.asList(lsOfGeoCode.keySet().toArray(new String[0]));
    //     List<String> lsOfCountryCodeVal = Arrays.asList(lsOfGeoCode.values().toArray(new String[0]));
    
    //     model.addAttribute("lsOfCountryCode", lsOfCountryCode);
    //     model.addAttribute("lsOfCountryCodeVal", lsOfCountryCodeVal);
    
    // }
}
