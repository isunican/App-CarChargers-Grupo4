package es.unican.carchargers;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.HashSet;

import static org.junit.Assert.*;

import es.unican.carchargers.activities.main.IMainContract;
import es.unican.carchargers.activities.main.MainPresenter;
import es.unican.carchargers.constants.EOperator;
import es.unican.carchargers.model.Charger;

public class FilterTest {

    @Mock
    private IMainContract.View view;
    private MainPresenter subject;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
        subject = new MainPresenter(); // Inicializa tu clase aquí.
        subject.view = view;
    }

    @Test
    public void testOnOperatorFilterClicked_withNoActiveFilters() {
        subject.shownChargers = Arrays.asList(mock(Charger.class), mock(Charger.class)); // Ejemplo de dos cargadores.
        subject.onOperatorFilterClicked(EOperator.WENEA, true);
        verify(view).showChargers(anyList()); // Verifica que se llame al método showChargers.
    }

    @Test
    public void testOnOperatorFilterClicked_withActiveFilters() {
        subject.shownChargers = Arrays.asList(mock(Charger.class), mock(Charger.class));
        subject.activeFilters = new HashSet<>();
        subject.activeFilters.add(EOperator.WENEA);
        subject.onOperatorFilterClicked(EOperator.WENEA, false);
        verify(view).showChargers(anyList());
    }

}
