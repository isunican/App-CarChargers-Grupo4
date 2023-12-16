package es.unican.carchargers.sampledata;

import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import es.unican.carchargers.constants.ESorting;
import es.unican.carchargers.model.Charger;
import es.unican.carchargers.repository.ICallBack;
import es.unican.carchargers.repository.IRepository;
import es.unican.carchargers.repository.service.APIArguments;

public class SortTest extends TestBase {

    @Test
    public void testOnSortingClicked() {
        List<Charger> chargers = new ArrayList<>();
        Charger testCharger1 = TestUtils.createTestChargers().get(0);
        chargers.add(testCharger1);
        Charger testCharger2 = TestUtils.createTestChargers().get(1);
        chargers.add(testCharger2);
        Charger testCharger3 = TestUtils.createTestChargers().get(2);
        chargers.add(testCharger3);
        Charger testCharger4 = TestUtils.createTestChargers().get(3);
        chargers.add(testCharger4);

        IRepository mockRepository = mock(IRepository.class);
        doAnswer(invocation -> {
            ICallBack callback = invocation.getArgument(1);
            callback.onSuccess(chargers);
            return null;
        }).when(mockRepository).requestChargers(any(APIArguments.class), any(ICallBack.class));

        when(mockView.getRepository()).thenReturn(mockRepository);

        presenter.init(mockView);

        Mockito.reset(mockView);

        presenter.onSortingClicked(ESorting.COST);
        System.out.println("El costo de uso del primer cargador es: " + chargers.get(0).usageCost);
        verify(mockView, times(1)).showChargers(argThat(list -> list.get(0).usageCost.equals("0,39€/kWh AC")));
//        verify(mockView, times(1)).showChargers(argThat(list -> list.get(1).usageCost.equals("0,45€/kWh AC")));
//        verify(mockView, times(1)).showChargers(argThat(list -> list.get(2).usageCost.equals("0,47€/kWh AC")));
//        verify(mockView, times(1)).showChargers(argThat(list -> list.get(3).usageCost.equals("0,70€/kWh AC")));

//        Mockito.reset(mockView);
//
//        presenter.onSortingClicked(ESorting.DISTANCE);
//        verify(mockView, times(1)).showChargers(argThat(list -> list.get(0).address.latitude.equals(40.416775)));
//        verify(mockView, times(1)).showChargers(argThat(list -> list.get(1).address.latitude.equals(48.856613)));
//        verify(mockView, times(1)).showChargers(argThat(list -> list.get(2).address.latitude.equals(41.902782)));
//        verify(mockView, times(1)).showChargers(argThat(list -> list.get(3).address.latitude.equals(35.689487)));

//        Mockito.reset(mockView);
//
//        presenter.onSortingClicked(ESorting.POWER);
//        verify(mockView, times(1)).showChargers(argThat(list -> Math.abs(list.get(0).getMaxPower() - 22) < 0.001));
//        verify(mockView, times(1)).showChargers(argThat(list -> Math.abs(list.get(1).getMaxPower() - 30) < 0.001));
//        verify(mockView, times(1)).showChargers(argThat(list -> Math.abs(list.get(2).getMaxPower() - 40) < 0.001));
//        verify(mockView, times(1)).showChargers(argThat(list -> Math.abs(list.get(3).getMaxPower() - 50) < 0.001));
    }
}
