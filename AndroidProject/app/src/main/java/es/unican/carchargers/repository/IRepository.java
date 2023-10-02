package es.unican.carchargers.repository;

import es.unican.carchargers.repository.service.APIArguments;

/**
 * A repository to access Charging Stations
 */
public interface IRepository {

        /**
         * Asynchronously requests a list of charging stations.
         * @param args the arguments to the charging stations API
         * @param cb the callback that will asynchronously process the returned charging stations
         */
        public void requestChargers(APIArguments args, ICallBack cb);

}
