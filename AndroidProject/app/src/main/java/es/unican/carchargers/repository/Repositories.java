package es.unican.carchargers.repository;

import java.io.InputStream;
import java.util.List;

import es.unican.carchargers.model.Charger;
import es.unican.carchargers.repository.service.APIFactory;

/**
 * A helper class to retrieve different variants of the repository
 */
public class Repositories {

    /**
     * Returns a "real" repository that will access the real OpenChargeMap online service
     * @return
     */
    public static IRepository getReal() {
        return new Repository(APIFactory.real());
    }

    /**
     * Returns a "fake" repository that will always return the given list of charging stations
     * without accessing the online service.
     * @param data
     * @return
     */
    public static IRepository getFake(List<Charger> data) {
        return new Repository(APIFactory.fakeSuccess(data));
    }

    /**
     * Returns a "fake" repository that will always return the given list of charging stations
     * without accessing the online service. The charging stations are given as a json input stream.
     * @param is
     * @return
     */
    public static IRepository getFake(InputStream is) {
        return new Repository(APIFactory.fakeSuccess(is));
    }

    /**
     * Returns a "fake" repository that will always fail. It does not access the internet.
     * @return
     */
    public static IRepository getFail() {
        return new Repository(APIFactory.fakeFailure());
    }

}
