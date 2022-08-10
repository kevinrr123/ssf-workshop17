// package vttp2022.app.ssfworkshop17.model;

// import java.io.ByteArrayInputStream;
// import java.io.InputStream;
// import java.util.Iterator;
// import java.util.LinkedHashMap;
// import java.util.Map;
// import java.util.SortedSet;
// import java.util.TreeSet;

// import jakarta.json.Json;
// import jakarta.json.JsonObject;
// import jakarta.json.JsonReader;

// public class CurrencyCode {
//     static Map<String,String> CurrencyCodeLst = new LinkedHashMap<>();

//     public static Map<String, String> getCurrencyCodeLst() {
//         return CurrencyCodeLst;
//     }

//     public static void setCurrencyCodeLst(Map<String, String> currencyCodeLst) {
//         CurrencyCodeLst = currencyCodeLst;
//     }
    
//     public static Map<String,String> lsOfCountryCode(String json){
//         InputStream is = new ByteArrayInputStream(json.getBytes());
        
//         try(JsonReader jr = Json.createReader(is)){
//             JsonObject currenciesJo = jr.readObject();
//             SortedSet<String> SortedKeys = new TreeSet<>(currenciesJo.keySet());

//             Iterator<String> key = SortedKeys.iterator();
            
//             while(key.hasNext()){
//                 String geo = key.next();
//                 CurrencyCodeLst.put(currenciesJo.get(geo).toString(), geo);
//             }
//         }
//         return CurrencyCodeLst;
//     }
// }
