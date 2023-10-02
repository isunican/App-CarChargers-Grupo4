package es.unican.carchargers.repository.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Arguments that can be requested directly to the OpenChargeMap REST API.
 * This is currently a sub-set of the total number of arguments that the API accepts.
 * Doc: https://openchargemap.org/site/develop/api#/operations/get-poi
 */
public class APIArguments {
    private String countryCode;
    private List<Integer> operatorIds = new ArrayList<>();
    private Double lat;
    private Double lon;
    private Integer maxResults;

    public static APIArguments builder() {
        return new APIArguments();
    }

    public Map<String, Object> toMap() {
        Map<String, Object> args = new HashMap<>();
        args.put(IOpenChargeMapAPI.COUNTRY_CODE, countryCode);
        args.put(IOpenChargeMapAPI.OPERATOR_ID, operatorIds);
        args.put(IOpenChargeMapAPI.LATITUDE, lat);
        args.put(IOpenChargeMapAPI.LONGITUDE, lon);
        args.put(IOpenChargeMapAPI.MAX_RESULTS, maxResults);
        return args;
    }

    public APIArguments setCountryCode(String countryCode) {
        this.countryCode = countryCode;
        return this;
    }

    public APIArguments setOperatorId(int ...operatorId) {
        for (int i : operatorId) {
            this.operatorIds.add(i);
        }
        return this;
    }

    public APIArguments setLocation(double lat, double lon) {
        this.lat = lat;
        this.lon = lon;
        return this;
    }

    public APIArguments setMaxResults(int maxResults) {
        this.maxResults = maxResults;
        return this;
    }

}
