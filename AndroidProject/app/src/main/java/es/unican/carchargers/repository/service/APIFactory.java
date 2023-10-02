package es.unican.carchargers.repository.service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import es.unican.carchargers.model.Charger;
import retrofit2.Call;

/**
 * Helper factory to construct different variants of an OpenChargeMap API object.
 * An API object is used to access a REST API.
 * The different variants are either "real" (connects to the online REST API), or "fake" (does
 * not connect to the online REST API)
 */
public class APIFactory {

    /**
     * Returns a "real" API object that actually connects to the internet.
     * @return the real API object
     */
    public static IOpenChargeMapAPI real() {
        return OpenChargeMapService.api;
    }

    /**
     * Returns a "fake" API object that does not connect to the internet.
     * This API always returns the list of charging stations provided.
     * @param data the list of charging stations that this API object should always return.
     * @return the fake API object
     */
    public static IOpenChargeMapAPI fakeSuccess(List<Charger> data) {
        return call(FakeCall.success(data));
    }

    /**
     * Returns a "fake" API object that does not connect to the internet.
     * This API always returns the list of charging stations as a json input stream.
     * @param is the json input stream that contains the list of charging stations
     * @return the fake API object
     */
    public static IOpenChargeMapAPI fakeSuccess(InputStream is) {
        Type typeToken = new TypeToken<List<Charger>>() { }.getType();
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        List<Charger> chargers = new Gson().fromJson(reader, typeToken);
        return fakeSuccess(chargers);
    }

    /**
     * Returns a "fake" API object that always fails.
     * @return
     */
    public static IOpenChargeMapAPI fakeFailure() {
        return call(FakeCall.failure(new Exception()));
    }

    /**
     * Helper method to create real of fake API objects
     * @param call
     * @return
     */
    private static IOpenChargeMapAPI call(Call<List<Charger>> call) {
        return new IOpenChargeMapAPI() {
            @Override
            public Call<List<Charger>> chargers(Map<String, Object> args) {
                return call;
            }
        };
    }

}
