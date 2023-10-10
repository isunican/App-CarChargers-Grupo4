package es.unican.carchargers.activities.main;

import java.util.Collections;
import java.util.List;

import es.unican.carchargers.constants.ECountry;
import es.unican.carchargers.constants.EOperator;
import es.unican.carchargers.constants.ESorting;
import es.unican.carchargers.model.Charger;
import es.unican.carchargers.repository.ICallBack;
import es.unican.carchargers.repository.IRepository;
import es.unican.carchargers.repository.service.APIArguments;

public class MainPresenter implements IMainContract.Presenter {

    /** the view controlled by this presenter */
    private IMainContract.View view;

    /** a cached list of charging stations currently shown */
    private List<Charger> shownChargers;

    @Override
    public void init(IMainContract.View view) {
        this.view = view;
        view.init();
        load();
    }

    /**
     * This method requests a list of charging stations from the repository, and requests
     * the view to show them.
     */
    private void load() {
        IRepository repository = view.getRepository();

        // set API arguments to retrieve charging stations that match some criteria
        APIArguments args = APIArguments.builder()
                .setCountryCode(ECountry.SPAIN.code)
                .setLevelId(3)          // level 3 (over 40kw)
                .setStatusId(50)        // status 50 (Operational)
                .setMaxResults(1000);   // limit download to 1000 results

        ICallBack callback = new ICallBack() {
            @Override
            public void onSuccess(List<Charger> chargers) {
                updateChargers(chargers);
            }

            @Override
            public void onFailure(Throwable e) {
                MainPresenter.this.shownChargers = Collections.emptyList();
                view.showLoadError();
            }
        };

        // asynchronously request chargers
        repository.requestChargers(args, callback);
    }

    /**
     * Process the given list of chargers:
     * <ul>
     *     <li>Updates the cached list of chargers stored in this presenter</li>
     *     <li>Sends the processed list of chargers to the View</li>
     * </ul>
     * @param chargers
     */
    private void updateChargers(List<Charger> chargers) {
        this.shownChargers = chargers != null ? chargers : Collections.emptyList();
        view.showChargers(this.shownChargers);
        view.showLoadCorrect(this.shownChargers.size());
    }

    @Override
    public void onOperatorFilterClicked(EOperator operator, boolean active) {
        // TODO
    }

    @Override
    public void onSortingClicked(ESorting criteria) {
        // TODO
    }

    @Override
    public void onChargerClicked(int index) {
        if (shownChargers != null && index < shownChargers.size()) {
            Charger charger = shownChargers.get(index);
            view.showChargerDetails(charger);
        }
    }

    @Override
    public void onMenuInfoClicked() {
        view.showInfoActivity();
    }

}
