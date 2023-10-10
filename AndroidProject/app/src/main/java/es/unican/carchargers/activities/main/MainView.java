package es.unican.carchargers.activities.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.chip.Chip;

import org.parceler.Parcels;

import java.util.List;
import java.util.stream.Stream;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import es.unican.carchargers.R;
import es.unican.carchargers.activities.details.DetailsView;
import es.unican.carchargers.activities.info.InfoActivity;
import es.unican.carchargers.constants.EOperator;
import es.unican.carchargers.constants.ESorting;
import es.unican.carchargers.model.Charger;
import es.unican.carchargers.repository.IRepository;

@AndroidEntryPoint
public class MainView extends AppCompatActivity implements IMainContract.View {

    /** repository is injected with Hilt */
    @Inject IRepository repository;

    /** presenter that controls this view */
    IMainContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize presenter-view connection
        presenter = new MainPresenter();
        presenter.init(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuItemInfo:
                presenter.onMenuInfoClicked();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void init() {
        // initialize listener to react to touch selections in the list
        ListView lv = findViewById(R.id.lvChargers);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                presenter.onChargerClicked(position);
            }
        });

        findViewById(R.id.chipTesla).setTag(EOperator.TESLA);
        findViewById(R.id.chipIberdrola).setTag(EOperator.IBERDROLA);
        findViewById(R.id.chipIonity).setTag(EOperator.IONITY);
        findViewById(R.id.chipZunder).setTag(EOperator.ZUNDER);

        findViewById(R.id.chipDistancia).setTag(ESorting.DISTANCE);
        findViewById(R.id.chipPotencia).setTag(ESorting.POWER);
        findViewById(R.id.chipPrecio).setTag(ESorting.COST);

        Stream.of(R.id.chipTesla, R.id.chipIberdrola, R.id.chipIonity, R.id.chipZunder)
                .forEach(i -> {
                    findViewById(i).setOnClickListener(chip -> onFilterClicked((Chip) chip));
                });

        Stream.of(R.id.chipDistancia, R.id.chipPotencia, R.id.chipPrecio)
                .forEach(i -> {
                    findViewById(i).setOnClickListener(chip -> onSortingClicked((Chip) chip));
                });
    }

    private void onFilterClicked(Chip chip) {
        presenter.onOperatorFilterClicked((EOperator)chip.getTag(), chip.isChecked());
    }

    private void onSortingClicked(Chip chip) {
        presenter.onSortingClicked((ESorting)chip.getTag());
    }

    @Override
    public IRepository getRepository() {
        return repository;
    }

    @Override
    public void showChargers(List<Charger> chargers) {
        ChargersArrayAdapter adapter = new ChargersArrayAdapter(this, chargers);
        ListView listView = findViewById(R.id.lvChargers);
        listView.setAdapter(adapter);
    }

    @Override
    public void showLoadCorrect(int chargers) {
        Toast.makeText(this, String.format("Cargados %d cargadores", chargers),
                Toast.LENGTH_LONG).show();
    }

    @Override
    public void showLoadError() {
        Toast.makeText(this, "Error cargando cargadores", Toast.LENGTH_LONG).show();
    }

    @Override
    public void showChargerDetails(Charger charger) {
        Intent intent = new Intent(this, DetailsView.class);
        intent.putExtra(DetailsView.INTENT_CHARGER, Parcels.wrap(charger));
        startActivity(intent);
    }

    @Override
    public void showInfoActivity() {
        Intent intent = new Intent(this, InfoActivity.class);
        startActivity(intent);
    }

}