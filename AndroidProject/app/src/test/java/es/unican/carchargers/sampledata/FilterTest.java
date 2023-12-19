package es.unican.carchargers.sampledata;

import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import static es.unican.carchargers.activities.main.MainPresenter.activeFilters;

import java.util.ArrayList;
import java.util.List;

import es.unican.carchargers.constants.EOperator;
import es.unican.carchargers.model.Charger;
import es.unican.carchargers.repository.ICallBack;
import es.unican.carchargers.repository.IRepository;
import es.unican.carchargers.repository.service.APIArguments;

public class FilterTest extends TestBase {

    @Test
    public void testOperatorFilter() {
        List<Charger> chargers = new ArrayList<>();
        Charger testCharger1 = TestUtils.createTestChargers().get(0);
        chargers.add(testCharger1);
        Charger testCharger2 = TestUtils.createTestChargers().get(1);
        chargers.add(testCharger2);
        Charger testCharger3 = TestUtils.createTestChargers().get(2);
        chargers.add(testCharger3);
        Charger testCharger4 = TestUtils.createTestChargers().get(3);
        chargers.add(testCharger4);
        Charger testCharger5 = TestUtils.createTestChargers().get(4);
        chargers.add(testCharger5);
        Charger testCharger6 = TestUtils.createTestChargers().get(5);
        chargers.add(testCharger6);
        Charger testCharger7 = TestUtils.createTestChargers().get(6);
        chargers.add(testCharger7);
        Charger testCharger8 = TestUtils.createTestChargers().get(7);
        chargers.add(testCharger8);

        IRepository mockRepository = mock(IRepository.class);
        doAnswer(invocation -> {
            ICallBack callback = invocation.getArgument(1);
            callback.onSuccess(chargers);
            return null;
        }).when(mockRepository).requestChargers(any(APIArguments.class), any(ICallBack.class));

        when(mockView.getRepository()).thenReturn(mockRepository);

        presenter.init(mockView);

        presenter.onOperatorFilterClicked(EOperator.ZUNDER, true);
        assertEquals(1, activeFilters.size());
        reset(mockView);

        presenter.onOperatorFilterClicked(EOperator.IBERDROLA, true);
        assertEquals(2, activeFilters.size());
        reset(mockView);

        presenter.onOperatorFilterClicked(EOperator.IONITY, true);
        assertEquals(3, activeFilters.size());
        reset(mockView);

        presenter.onOperatorFilterClicked(EOperator.TESLA, true);
        assertEquals(4, activeFilters.size());
        reset(mockView);

        presenter.onOperatorFilterClicked(EOperator.TESLA, false);
        presenter.onOperatorFilterClicked(EOperator.IONITY, false);
        presenter.onOperatorFilterClicked(EOperator.ZUNDER, false);
        presenter.onOperatorFilterClicked(EOperator.IBERDROLA, false);
        assertEquals(0, activeFilters.size());
    }

    @Test
    public void testApplyFilters() {
        List<Charger> chargers = TestUtils.createTestChargers();

        IRepository mockRepository = mock(IRepository.class);
        doAnswer(invocation -> {
            ICallBack callback = invocation.getArgument(1);
            callback.onSuccess(chargers);
            return null;
        }).when(mockRepository).requestChargers(any(APIArguments.class), any(ICallBack.class));

        when(mockView.getRepository()).thenReturn(mockRepository);

        presenter.init(mockView);


        activeFilters.add(EOperator.ZUNDER);
        presenter.applyFilters();
        verify(mockView, times(1)).showChargers(argThat(list -> list.size() == 2));
        reset(mockView);

        activeFilters.add(EOperator.TESLA);
        presenter.applyFilters();
        verify(mockView, times(1)).showChargers(argThat(list -> list.size() == 4));
        reset(mockView);

        activeFilters.add(EOperator.IBERDROLA);
        presenter.applyFilters();
        verify(mockView, times(1)).showChargers(argThat(list -> list.size() == 6));
        reset(mockView);

        activeFilters.add(EOperator.IONITY);
        presenter.applyFilters();
        verify(mockView, times(1)).showChargers(argThat(list -> list.size() == 8));
        reset(mockView);

        activeFilters.remove(EOperator.IONITY);
        presenter.applyFilters();
        verify(mockView, times(1)).showChargers(argThat(list -> list.size() == 6));
        reset(mockView);

        activeFilters.remove(EOperator.IBERDROLA);
        activeFilters.remove(EOperator.TESLA);
        activeFilters.remove(EOperator.ZUNDER);
        presenter.applyFilters();
        verify(mockView, times(1)).showChargers(argThat(list -> list.size() == 8));
    }
}