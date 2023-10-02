package es.unican.carchargers.constants;

/**
 * Static list of countries and their 2 letter ISO code
 */
public enum ECountry {

    SPAIN("ES");

    public final String code;

    ECountry(String code) {
        this.code = code;
    }
}
