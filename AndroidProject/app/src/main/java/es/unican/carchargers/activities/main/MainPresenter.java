package es.unican.carchargers.activities.main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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

    private Set<EOperator> activeFilters = new HashSet<>();

    @Override
    public void onOperatorFilterClicked(EOperator operator, boolean isActive) {
        if (shownChargers == null) return;

        // Actualizar el conjunto de filtros activos
        if (isActive) {
            activeFilters.add(operator);
        } else {
            activeFilters.remove(operator);
        }

        // Aplicar todos los filtros activos
        applyFilters();
    }

    private void applyFilters() {
        List<Charger> filteredChargers;

        System.out.println("Cantidad inicial de cargadores: " + shownChargers.size());
        System.out.println("Filtros activos: " + activeFilters);

        if (activeFilters.isEmpty()) {
            // Si no hay filtros activos, mostrar todos los cargadores
            filteredChargers = new ArrayList<>(shownChargers);
        } else {
            // Si hay filtros activos, filtrar la lista de cargadores
            filteredChargers = shownChargers.stream()
                    .filter(charger -> {
                        System.out.println("Operador del cargador: " + charger.operator.title);
                        EOperator operatorEnum = EOperator.fromId(charger.operator.id);
                        boolean matchesFilter = operatorEnum != null && activeFilters.contains(operatorEnum);
                        System.out.println("Coincide con el filtro: " + matchesFilter);
                        return matchesFilter;
                    })
                    .collect(Collectors.toList());
        }

        System.out.println("Cantidad de cargadores después de filtrar: " + filteredChargers.size());
        // Mostrar los cargadores filtrados (o todos si no hay filtros activos)
        view.showChargers(filteredChargers);
    }


    @Override
    public void onSortingClicked(ESorting criteria) {
<<<<<<< HEAD
        // TODO
=======
        if (shownChargers == null || shownChargers.isEmpty()) {
            System.out.println("La lista de cargadores está vacía o es nula.");
            return;
        }

        // Aplicar filtros primero
        List<Charger> filteredChargers = applyFiltersForSorting();

        // Ordenar la lista basándose en el criterio proporcionado.
        switch (criteria) {
            case COST:
                filteredChargers.sort(Comparator.comparingDouble(c -> parseCost(c.usageCost)));
                break;
            case DISTANCE:
                filteredChargers.sort(Comparator.comparingDouble(c -> c.calculateDistanceToSantander()));
                break;
            case POWER:
                filteredChargers.sort(Comparator.comparingDouble(this::getMaxPower));
                break;
            default:
                // Si el criterio proporcionado no es reconocido, no hacemos nada.
                return;
        }

        // Finalmente, mostrar los cargadores ordenados en la vista.
        view.showChargers(filteredChargers);
>>>>>>> 67528b854b70a7550eacd71ed6461b978dee6099
    }

    @Override
    public void onChargerClicked(int index) {
        if (shownChargers != null && index < shownChargers.size()) {
            Charger charger = shownChargers.get(index);
            view.showChargerDetails(charger);
        }
    }

<<<<<<< HEAD
=======
    private List<Charger> applyFiltersForSorting() {
        if (activeFilters.isEmpty()) {
            return new ArrayList<>(shownChargers);
        } else {
            return shownChargers.stream()
                    .filter(charger -> {
                        EOperator operatorEnum = EOperator.fromId(charger.operator.id);
                        return operatorEnum != null && activeFilters.contains(operatorEnum);
                    })
                    .collect(Collectors.toList());
        }
    }

    private Double getMaxPower(Charger c) {
        return c.connections.stream()
                .mapToDouble(Connection::getPower)
                .max()
                .orElse(0.0);
    }
>>>>>>> 67528b854b70a7550eacd71ed6461b978dee6099
    @Override
    public void onMenuInfoClicked() {
        view.showInfoActivity();
    }

}