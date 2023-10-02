package es.unican.carchargers.repository;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import es.unican.carchargers.model.Charger;
import es.unican.carchargers.repository.service.APIArguments;
import es.unican.carchargers.repository.service.IOpenChargeMapAPI;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * An implementation of a repository that uses the OpenChargeMap API to retrieve charging stations.
 */
class Repository implements IRepository {

    private final IOpenChargeMapAPI api;

    Repository(IOpenChargeMapAPI api) {
        this.api = api;
    }

    @Override
    public void requestChargers(APIArguments args, ICallBack cb) {
        Map<String, Object> map = args != null ? args.toMap() : null;
        cleanArguments(map);
        api.chargers(map)
                .enqueue(new Callback<List<Charger>>() {
                    @Override
                    public void onResponse(Call<List<Charger>> call, Response<List<Charger>> response) {
                        cb.onSuccess(response.body());
                    }

                    @Override
                    public void onFailure(Call<List<Charger>> call, Throwable t) {
                        cb.onFailure(t);
                    }
                });
    }

    /**
     * Cleans the argument map. This mas is used by the QueryMap feature of Retrofit.
     * This method makes sure the map is compatible with Retrofit's QueryMap.
     *
     * Retrofit QueryMap does not support List values. Transform them into a comma separated String
     * ref: https://github.com/square/retrofit/issues/1324
     *
     * Retrofit QueryMap does not support null values. Remove them to avoid retrofit errors.
     * ref: https://github.com/square/retrofit/issues/2741
     *
     * @param map
     */
    private void cleanArguments(Map<String, Object> map) {
        // transform List values into a comma-separated string
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            Object value = entry.getValue();

            if (value instanceof List) {
                List<?> list = (List<?>) value;

                if (list.isEmpty()) {
                    entry.setValue(null);
                } else {
                    String str = list.stream()
                            .map(i -> String.valueOf(i))
                            .collect(Collectors.joining(","));
                    entry.setValue(str);
                }
            }
        }

        // remove null values
        map.values().removeAll(Collections.singleton(null));
    }
}
