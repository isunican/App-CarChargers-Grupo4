package es.unican.carchargers;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.argThat;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;

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

        presenter.onSortingClicked(ESorting.COST);
        //reset(mockView);
        verify(mockView, times(2)).showChargers(argThat(list -> list.get(0).usageCost.equals("0,19€/kWh AC")));
        verify(mockView, times(2)).showChargers(argThat(list -> list.get(1).usageCost.equals("0,28€/kWh AC")));
        verify(mockView, times(2)).showChargers(argThat(list -> list.get(2).usageCost.equals("0,35€/kWh AC")));
        verify(mockView, times(2)).showChargers(argThat(list -> list.get(3).usageCost.equals("0,47€/kWh AC")));

        System.out.println("El costo de uso del primer cargador : " + chargers.get(0).address.latitude);
        System.out.println("El costo de uso del primer cargador : " + chargers.get(1).address.latitude);
        System.out.println("El costo de uso del primer cargador : " + chargers.get(2).address.latitude);
        System.out.println("El costo de uso del primer cargador : " + chargers.get(3).address.latitude);

        reset(mockView);

        presenter.onSortingClicked(ESorting.DISTANCE);

        System.out.println("El costo de uso del primer cargador e: " + chargers.get(0).address.latitude);
        System.out.println("El costo de uso del primer cargador e: " + chargers.get(1).address.latitude);
        System.out.println("El costo de uso del primer cargador e: " + chargers.get(2).address.latitude);
        System.out.println("El costo de uso del primer cargador e: " + chargers.get(3).address.latitude);

        verify(mockView, times(1)).showChargers(argThat(list -> list.get(0).address.latitude.equals(40.416775)));
        verify(mockView, times(1)).showChargers(argThat(list -> list.get(1).address.latitude.equals(48.856613)));
        verify(mockView, times(1)).showChargers(argThat(list -> list.get(2).address.latitude.equals(41.902782)));
        verify(mockView, times(1)).showChargers(argThat(list -> list.get(3).address.latitude.equals(35.689487)));

        reset(mockView);

        presenter.onSortingClicked(ESorting.POWER);
        System.out.println("El costo de uso del primer cargador es: " + chargers.get(0).getMaxPower());
        System.out.println("El costo de uso del primer cargador es: " + chargers.get(1).getMaxPower());
        System.out.println("El costo de uso del primer cargador es: " + chargers.get(2).getMaxPower());
        System.out.println("El costo de uso del primer cargador es: " + chargers.get(3).getMaxPower());
        double cmxa = chargers.get(0).getMaxPower();
        double cmxb = chargers.get(1).getMaxPower();
        double cmxc = chargers.get(2).getMaxPower();
        double cmxd = chargers.get(3).getMaxPower();

        verify(mockView, times(1)).showChargers(argThat(list -> Math.abs(cmxa - 12.0) < 0.001));
        verify(mockView, times(1)).showChargers(argThat(list -> Math.abs(cmxb - 22.0) < 0.001));
        verify(mockView, times(1)).showChargers(argThat(list -> Math.abs(cmxc - 32.0) < 0.001));
        verify(mockView, times(1)).showChargers(argThat(list -> Math.abs(cmxd - 42.0) < 0.001));
    }
}