package es.unican.carchargers.repository.service;

import java.io.IOException;
import java.util.List;

import es.unican.carchargers.model.Charger;
import okhttp3.Request;
import okio.Timeout;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * This is a simple way to mock a retrofit API call
 * Inspired by:
 * - https://stackoverflow.com/a/49902072
 * - Retromock FakeCall: https://github.com/infinum/Retromock/blob/master/retromock/src/main/java/co/infinum/retromock/Calls.java
 *
 * This is not meant to be modified.
 */
public class FakeCall implements Call<List<Charger>> {

    private final List<Charger> data;
    private final Throwable t;

    public static FakeCall success(List<Charger> data) {
        return new FakeCall(data, null);
    }

    public static FakeCall failure(Throwable t) {
        return new FakeCall(null, t);
    }

    private FakeCall(List<Charger> data, Throwable t) {
        this.data = data;
        this.t = t;
    }

    @Override
    public Response<List<Charger>> execute() throws IOException {
        return Response.success(data);
    }

    @Override
    public void enqueue(Callback<List<Charger>> callback) {
        if (this.t != null) {
            callback.onFailure(this, this.t);

        } else {
            try {
                Response<List<Charger>> response = execute();
                callback.onResponse(this, response);
            } catch (IOException e) {
                callback.onFailure(this, e);
            }
        }
    }

    @Override
    public boolean isExecuted() {
        return false;
    }

    @Override
    public void cancel() {

    }

    @Override
    public boolean isCanceled() {
        return false;
    }

    @Override
    public Call<List<Charger>> clone() {
        return this;
    }

    @Override
    public Request request() {
        return new Request.Builder().build();
    }

    @Override
    public Timeout timeout() {
        return null;
    }

}
