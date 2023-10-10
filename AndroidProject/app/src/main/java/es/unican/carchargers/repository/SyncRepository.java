package es.unican.carchargers.repository;

import java.util.List;

import es.unican.carchargers.model.Charger;
import es.unican.carchargers.repository.service.APIArguments;

/**
 * 
 */
public class SyncRepository implements IRepository {

    private final List<Charger> chargers;
    private final boolean fail;

    private SyncRepository(List<Charger> chargers, boolean fail) {
        this.chargers = chargers;
        this.fail = fail;
    }

    public static IRepository buildFake(List<Charger> chargers) {
        return new SyncRepository(chargers, false);
    }

    public static IRepository buildFail() {
        return new SyncRepository(null, true);
    }

    @Override
    public void requestChargers(APIArguments args, ICallBack cb) {
        if (!fail) {
            cb.onSuccess(chargers);
        } else {
            cb.onFailure(new Throwable());
        }
    }
}
