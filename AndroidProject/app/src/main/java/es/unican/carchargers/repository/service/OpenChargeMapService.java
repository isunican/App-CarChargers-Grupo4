package es.unican.carchargers.repository.service;

import java.io.IOException;

import es.unican.carchargers.BuildConfig;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Retrofit utilities to instantiate a real API object to access the OpenChargeMap online service
 */
public class OpenChargeMapService {

    /** My Open Charge Map API Key */
    final static String API_KEY = BuildConfig.OCM_API_KEY;

    /** Open Charge Map API base URL*/
    final static String BASE_URL = "https://api.openchargemap.io/v3/";

    public static final OkHttpClient client;
    public static final Retrofit retrofit;
    public static final IOpenChargeMapAPI api;

    static {
        client = getClient();
        retrofit = getRetrofit();
        api = getAPI();
    }

    private static OkHttpClient getClient() {
        return new OkHttpClient().newBuilder().addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request originalRequest = chain.request();
                Request.Builder builder = originalRequest.newBuilder()
                        .header("X-API-Key", API_KEY);
                Request newRequest = builder.build();
                return chain.proceed(newRequest);
            }
        }).build();
    }

    private static Retrofit getRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    private static IOpenChargeMapAPI getAPI() {
        return retrofit.create(IOpenChargeMapAPI.class);
    }
}
