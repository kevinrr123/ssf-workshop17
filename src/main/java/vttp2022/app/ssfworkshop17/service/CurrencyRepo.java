package vttp2022.app.ssfworkshop17.service;


import vttp2022.app.ssfworkshop17.model.Currency;


public interface CurrencyRepo {

    public void save(final Currency currency);
    
    public Currency load(final String id);
    
}
