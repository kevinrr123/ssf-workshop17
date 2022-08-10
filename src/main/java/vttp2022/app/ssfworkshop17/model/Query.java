package vttp2022.app.ssfworkshop17.model;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.json.JsonNumber;
import jakarta.json.JsonObject;

public class Query {
    private static Logger logger = LoggerFactory.getLogger(Query.class);

    private String from;
    private String to;
    private BigDecimal amount;
    
    public String getFrom() {
        return from;
    }
    public void setFrom(String from) {
        this.from = from;
    }
    public String getTo() {
        return to;
    }
    public void setTo(String to) {
        this.to = to;
    }
    public BigDecimal getAmount() {
        return amount;
    }
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    // public static Query createJson(JsonObject o){
    //     logger.info("createJson Query");
    //     Query q = new Query();
    //     //JsonObject queryObj = o.getJsonObject("query");
    //     String toStr = o.getString("to");
    //     q.to = toStr;
    //     String FromStr = o.getString("from");
    //     q.from = FromStr;
    //     JsonNumber jsNum = o.getJsonNumber("amount");
    //     q.amount = jsNum.bigDecimalValue();
    //     return q;
    // }
}
