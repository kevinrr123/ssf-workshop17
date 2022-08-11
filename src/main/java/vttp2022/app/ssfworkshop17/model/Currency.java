package vttp2022.app.ssfworkshop17.model;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

public class Currency {
    private static Logger logger = LoggerFactory.getLogger(Currency.class);

    private String success;
    //private String date;
    private BigDecimal result;
    private Query query;
    //private BigDecimal amount;
    private String id;
    
    public Currency() {
        this.id = generateId(8);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    private synchronized String generateId(int numchars) {
        Random r = new Random();
        StringBuilder strBuilder = new StringBuilder();
        while (strBuilder.length() < numchars) {
            strBuilder.append(Integer.toHexString(r.nextInt()));
        }
        return strBuilder.toString().substring(0, numchars);
    }

    // public BigDecimal getAmount() {
    //     return amount;
    // }
    // public void setAmount(BigDecimal amount) {
    //     this.amount = amount;
    // }
    public String getSuccess() {
        return success;
    }
    public void setSuccess(String success) {
        this.success = success;
    }
    // public String getDate() {
    //     return date;
    // }
    // public void setDate(String date) {
    //     this.date = date;
    // }
    public BigDecimal getResult() {
        return result;
    }
    public void setResult(BigDecimal result) {
        this.result = result;
    }
    public Query getQuery() {
        return query;
    }
    public void setQuery(Query query) {
        this.query = query;
    }

    //     public static Currency createJson(String json)throws IOException{
    //         logger.info("currency createJson");
    //         Currency c = new Currency();
    //         try(InputStream is = new ByteArrayInputStream(json.getBytes())){
    //             JsonReader r = Json.createReader(is);
    //             JsonObject o = r.readObject();
    //             logger.info(">>>" + o.getJsonObject("query"));
    //         c.query = Query.createJson(o.getJsonObject("query"));
    //         c.date = o.getJsonString("date").getString();
    //         c.success = o.get("success").toString();
    //         c.result = o.getJsonNumber("result").bigDecimalValue();
    //     }
    //     return c;
    // }
}
