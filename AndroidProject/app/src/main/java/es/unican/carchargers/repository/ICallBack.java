package es.unican.carchargers.repository;

import java.util.List;

import es.unican.carchargers.model.Charger;

/**
 * The callback used by the repository to asynchronously retrieve charging stations.
 */
public interface ICallBack {

    /**
     * This method is automatically called when the charging stations were successfully retrieved
     * @param chargers the list of retrieved charging stations
     */
    public void onSuccess(List<Charger> chargers);

    /**
     * This method is automaticaly called when there was some failure when retrieving the
     * charging stations.
     * @param e the information about the failure
     */
    public void onFailure(Throwable e);

}
