package es.unican.carchargers;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.argThat;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static es.unican.carchargers.activities.main.MainPresenter.activeFilters;

import org.junit.Test;

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
        TestUtils.createTestChargers();
        List<Charger> chargers = new ArrayList<>();
        Charger testCharger1 = TestUtils.chargers.get(0);
        chargers.add(testCharger1);
        Charger testCharger2 = TestUtils.chargers.get(1);
        chargers.add(testCharger2);
        Charger testCharger3 = TestUtils.chargers.get(2);
        chargers.add(testCharger3);
        Charger testCharger4 = TestUtils.chargers.get(3);
        chargers.add(testCharger4);
        Charger testCharger5 = TestUtils.chargers.get(4);
        chargers.add(testCharger5);
        Charger testCharger6 = TestUtils.chargers.get(5);
        chargers.add(testCharger6);
        Charger testCharger7 = TestUtils.chargers.get(6);
        chargers.add(testCharger7);
        Charger testCharger8 = TestUtils.chargers.get(7);
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

        TestUtils.createTestChargers();
        List<Charger> chargers = new ArrayList<>();
        Charger testCharger1 = TestUtils.chargers.get(1);
        chargers.add(testCharger1);
        Charger testCharger2 = TestUtils.chargers.get(2);
        chargers.add(testCharger2);
        Charger testCharger3 = TestUtils.chargers.get(3);
        chargers.add(testCharger3);
        Charger testCharger4 = TestUtils.chargers.get(4);
        chargers.add(testCharger4);
        Charger testCharger5 = TestUtils.chargers.get(5);
        chargers.add(testCharger5);
        Charger testCharger6 = TestUtils.chargers.get(6);
        chargers.add(testCharger6);
        Charger testCharger7 = TestUtils.chargers.get(7);
        chargers.add(testCharger7);
        Charger testCharger8 = TestUtils.chargers.get(8);
        chargers.add(testCharger8);

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