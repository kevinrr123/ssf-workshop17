package vttp2022.app.ssfworkshop17.service;


import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import vttp2022.app.ssfworkshop17.model.Currency;
import vttp2022.app.ssfworkshop17.model.Query;

@Service
public class CurrencyAPIService implements CurrencyRepo {
    private static Logger logger = LoggerFactory.getLogger(CurrencyAPIService.class);

    @Autowired
    RedisTemplate<String, Currency> redisTemplate;

    private static String URL = "https://api.apilayer.com/fixer/convert";
    
    public Currency convertExchangeRates(Query q){
        
        
        String apiKey = System.getenv("FIXER_CURRENCY_API_KEY");

        
        String currencyURL = URL+"?to="+q.getTo()
                             + "&from="+q.getFrom()
                             +"&amount="+q.getAmount();
        
        logger.info(currencyURL);

        RestTemplate template = new RestTemplate();
        RequestEntity<Void> req = RequestEntity.get(currencyURL)
                                  .header("apiKey", apiKey)
                                  .accept(MediaType.APPLICATION_JSON).build();
        ResponseEntity<Currency> resp = template.exchange(req, Currency.class);
        resp.getBody().setQuery(q);
        return resp.getBody();  
    }

    @Override
    public void save(Currency currency){
        redisTemplate.opsForValue().set(currency.getId(), currency);
    }

    @Override
    public Currency load(String id){
        Currency result = redisTemplate.opsForValue().get(id);
        return result;
    }
    
    // private static String URL = "https://api.apilayer.com/fixer/convert";
    // public static final String LS_CURRENCY = "https://openexchangerates.org/api/currencies.json";

    // public Optional<Currency> convertExchangeRates(Query q){
    //     String apiKey = System.getenv("FIXER_CURRENCY_API_KEY");

    //     String currencyURL = UriComponentsBuilder.fromUriString(URL)
    //             .queryParam("to", q.getTo())
    //             .queryParam("from", q.getFrom())
    //             .queryParam("amount", q.getAmount())
    //             .toUriString();
    //     logger.info(currencyURL);
    //     RestTemplate template = new RestTemplate();
    //     ResponseEntity<String> resp = null;

    //     try{
    //         HttpHeaders headers = new HttpHeaders();
    //         headers.set("apikey", apiKey);
    //         HttpEntity request = new HttpEntity(headers);
    //         resp = template.exchange(currencyURL, HttpMethod.GET,request,String.class,1);
    //         logger.info(resp.getBody());
    //         Currency c = Currency.createJson(resp.getBody());
    //         return Optional.of(c);
    //     }catch(Exception e){
    //         logger.error(e.getMessage());
    //         e.printStackTrace();
    //     }
    //     return Optional.empty();
    // }

    // // Start rest template
    // // String url = "https://api.apilayer.com/fixer/convert?to=" + to + "&from=" + from + "&amount=" + amount;
    // //     RestTemplate template = new RestTemplate();
    // //     RequestEntity<Void> request = RequestEntity.get(url)
    // //             .header("apikey", currApiKey) //We can send over a Header key and value
    // //             .accept(MediaType.APPLICATION_JSON).build();
    // //     ResponseEntity<currConverter> response = template.exchange(request, currConverter.class);
    // //     System.out.println(response.getBody().getResult());
    // //     return response.getBody();


    // // public MultiValueMap<String, String> lsGeoCodeMapBuilder() {
    // // MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
    // // headers.add("apikey", apiKey);
    // // return headers;
    // // }

    // public String currencyComponentDynamicBuilder(String url, MultiValueMap<String, String> multiFrmTo) {

    //     return UriComponentsBuilder.fromUriString(url)
    //             .queryParams(multiFrmTo)
    //             .toUriString();
    // }

    // public Map<String, String> getLsOfGeoCode() {
    //     RestTemplate template = new RestTemplate();
    //     // String lsCurrencyUrl = currencyComponentDynamicBuilder(LS_CURRENCY,
    //     // lsGeoCodeMapBuilder());

    //     ResponseEntity<String> resp = template.getForEntity(LS_CURRENCY, String.class);

    //     Map<String, String> lsOfGeoCode = CurrencyCode.lsOfCountryCode(resp.getBody());
    //     logger.info("Retrieve list of country code: {}", lsOfGeoCode);
    //     return lsOfGeoCode;
    // }
}
