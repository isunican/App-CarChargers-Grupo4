package es.unican.carchargers.utils;

import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.IdlingResource;

import com.jakewharton.espresso.OkHttp3IdlingResource;

import es.unican.carchargers.repository.service.OpenChargeMapService;

/**
 * Idling Resource needed in Espresso when accessing a real REST service via HTTP
 * If this Idling Resource is not used, Espresso will not wait until the HTTP requests
 * are finished.
 *
 * By default Espresso knows how to wait until the UI is refreshed, but it does not know
 * how to wait until HTTP requests are finished. So we need a specific Idling Resource
 * for these HTTP Requests.
 *
 * We use directly an Idling Resource created for this:
 * https://github.com/JakeWharton/okhttp-idling-resource
 *
 * Documentation on Idling Resources:
 * https://developer.android.com/training/testing/espresso/idling-resource
 *
 */
public class HTTPIdlingResource {

    private static final HTTPIdlingResource INSTANCE =  new HTTPIdlingResource();
    private final  IdlingResource resource;

    HTTPIdlingResource() {
        resource = OkHttp3IdlingResource.create("okhttp", OpenChargeMapService.client);
    }

    public static HTTPIdlingResource getInstance() {
        return INSTANCE;
    }

    public void init() {
        IdlingRegistry.getInstance().register(resource);
    }

    public void finish() {
        IdlingRegistry.getInstance().register((IdlingResource)null);
    }

}
