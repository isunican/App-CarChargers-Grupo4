package es.unican.carchargers.repository.service;

import java.util.List;
import java.util.Map;

import es.unican.carchargers.model.Charger;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * The API to access OpenChargeMap online service using Retrofit.
 * Documentation: https://openchargemap.org/site/develop/api#/
 *
 * Currently it only implements the access to the POI (points of interest) endpoint:
 * https://openchargemap.org/site/develop/api#/operations/get-poi
 */
public interface IOpenChargeMapAPI {

    public static final String COUNTRY_CODE = "countryCode";
    public static final String OPERATOR_ID = "operatorid";
    public static final String LATITUDE = "latitude";
    public static final String LONGITUDE = "longitude";
    public static final String MAX_RESULTS = "maxResults";

    @GET("poi")
    Call<List<Charger>> chargers(@QueryMap Map<String, Object> args);

}
