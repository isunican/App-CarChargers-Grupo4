package es.unican.carchargers;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import es.unican.carchargers.activities.main.IMainContract;
import es.unican.carchargers.activities.main.MainPresenter;
import es.unican.carchargers.constants.EOperator;
import es.unican.carchargers.constants.ESorting;
import es.unican.carchargers.model.Address;
import es.unican.carchargers.model.Charger;
import es.unican.carchargers.model.Connection;
import es.unican.carchargers.model.Operator;
import es.unican.carchargers.repository.ICallBack;
import es.unican.carchargers.repository.IRepository;
import es.unican.carchargers.repository.service.APIArguments;

public class SortTest {
    @Mock
    private IMainContract.View mockView;
    @Mock
    private IRepository mockRepository;
    private MainPresenter presenter;


    private List<Charger> createTestChargers() {

        List<Charger> chargers = new ArrayList<>();

        Operator operator1 = new Operator();
        operator1.id = 3324;
        operator1.title = "Zunder";
        operator1.website = "http://example.com";
        operator1.comments = "Comentarios del operador";
        Address address1 = new Address();
        address1.title = "Dirección de Prueba";
        address1.town = "Ciudad de Prueba";
        address1.province = "Provincia de Prueba";
        address1.latitude = 40.416775; // Madrid, la mas cercana a santander 40.416775, -3.703790
        address1.longitude = -3.703790;
        Connection connection1 = new Connection();
        connection1.powerKw = "22";
        Charger charger1 = new Charger();
        charger1.id = "1";
        charger1.numberOfPoints = 4;
        charger1.usageCost = "0,70€/kWh AC";
        charger1.operator = operator1;
        charger1.address = address1;
        charger1.connections = new ArrayList<>();
        charger1.connections.add(connection1);

        Operator operator2 = new Operator();
        operator2.id = 3534;
        operator2.title = "Tesla";
        operator2.website = "http://example.com";
        operator2.comments = "Comentarios del operador";
        Address address2 = new Address();
        address2.title = "Dirección de Prueba";
        address2.town = "Ciudad de Prueba";
        address2.province = "Provincia de Prueba";
        address2.latitude = 48.856613; //Paris, la segunda más cercana a Santander 48.856613, 2.352222
        address2.longitude = -2.352222;
        Connection connection2 = new Connection();
        connection2.powerKw = "30";
        Charger charger2 = new Charger();
        charger2.id = "1";
        charger2.numberOfPoints = 4;
        charger2.usageCost = "0,39€/kWh AC";
        charger2.operator = operator2;
        charger2.address = address2;
        charger2.connections = new ArrayList<>();
        charger2.connections.add(connection2);

        Operator operator3 = new Operator();
        operator3.id = 3299;
        operator3.title = "Ionity";
        operator3.website = "http://example.com";
        operator3.comments = "Comentarios del operador";
        Address address3 = new Address();
        address3.title = "Dirección de Prueba";
        address3.town = "Ciudad de Prueba";
        address3.province = "Provincia de Prueba";
        address3.latitude = 41.902782; // Roma, la tercera más cercana a Santander 41.902782, 12.496366
        address3.longitude = -12.496366;
        Connection connection3 = new Connection();
        connection3.powerKw = "40";
        Charger charger3 = new Charger();
        charger3.id = "1";
        charger3.numberOfPoints = 4;
        charger3.usageCost = "0,45€/kWh AC";
        charger3.operator = operator3;
        charger3.address = address3;
        charger3.connections = new ArrayList<>();
        charger3.connections.add(connection3);


        Operator operator4 = new Operator();
        operator4.id = 2247;
        operator4.title = "Iberdrola";
        operator4.website = "http://example.com";
        operator4.comments = "Comentarios del operador";
        Address address4 = new Address();
        address4.title = "Dirección de Prueba";
        address4.town = "Ciudad de Prueba";
        address4.province = "Provincia de Prueba";
        address4.latitude = 35.689487; // Tokio, la cuarta más cercana a Santander 35.689487, 139.691706
        address4.longitude = -139.691706;
        Connection connection4 = new Connection();
        connection4.powerKw = "50";
        Charger charger4 = new Charger();
        charger4.id = "1";
        charger4.numberOfPoints = 4;
        charger4.usageCost = "0,47€/kWh AC";
        charger4.operator = operator4;
        charger4.address = address4;
        charger4.connections = new ArrayList<>();
        charger4.connections.add(connection4);


        chargers.add(charger2);
        chargers.add(charger1);
        chargers.add(charger4);
        chargers.add(charger3);


        return chargers;
    }

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        presenter = new MainPresenter();

        doAnswer(invocation -> {
            ICallBack callback = invocation.getArgument(1);
            callback.onSuccess(createTestChargers());
            return null;
        }).when(mockRepository).requestChargers(any(APIArguments.class), any(ICallBack.class));

        when(mockView.getRepository()).thenReturn(mockRepository);

        presenter.init(mockView);
    }

    @Test
    public void testGetMaxPower() {
        List<Charger> chargers = new ArrayList<>();
        Charger testCharger1 = createTestChargers().get(0);
        chargers.add(testCharger1);
        Charger testCharger2 = createTestChargers().get(1);
        chargers.add(testCharger2);
        Charger testCharger3 = createTestChargers().get(2);
        chargers.add(testCharger3);
        Charger testCharger4 = createTestChargers().get(3);
        chargers.add(testCharger4);

        IRepository mockRepository = mock(IRepository.class);
        doAnswer(invocation -> {
            ICallBack callback = invocation.getArgument(1);
            callback.onSuccess(chargers);
            return null;
        }).when(mockRepository).requestChargers(any(APIArguments.class), any(ICallBack.class));

        when(mockView.getRepository()).thenReturn(mockRepository);

        presenter.init(mockView);

        assertEquals( 30, testCharger1.getMaxPower(),0.1);
        assertEquals( 22, testCharger2.getMaxPower(),0.1);
        assertEquals( 50, testCharger3.getMaxPower(),0.1);
        assertEquals( 40, testCharger4.getMaxPower(),0.1);

    }

    @Test
    public void testOnSortingClicked() {
        List<Charger> chargers = new ArrayList<>();
        Charger testCharger1 = createTestChargers().get(0);
        chargers.add(testCharger1);
        Charger testCharger2 = createTestChargers().get(1);
        chargers.add(testCharger2);
        Charger testCharger3 = createTestChargers().get(2);
        chargers.add(testCharger3);
        Charger testCharger4 = createTestChargers().get(3);
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
        verify(mockView, times(1)).showChargers(argThat(list -> list.get(0).usageCost.equals("0,39€/kWh AC")));
        verify(mockView, times(1)).showChargers(argThat(list -> list.get(1).usageCost.equals("0,45€/kWh AC")));
        verify(mockView, times(1)).showChargers(argThat(list -> list.get(2).usageCost.equals("0,47€/kWh AC")));
        verify(mockView, times(1)).showChargers(argThat(list -> list.get(3).usageCost.equals("0,70€/kWh AC")));

        Mockito.reset(mockView);

        presenter.onSortingClicked(ESorting.DISTANCE);
        verify(mockView, times(1)).showChargers(argThat(list -> list.get(0).address.latitude.equals(40.416775)));
        verify(mockView, times(1)).showChargers(argThat(list -> list.get(1).address.latitude.equals(48.856613)));
        verify(mockView, times(1)).showChargers(argThat(list -> list.get(2).address.latitude.equals(41.902782)));
        verify(mockView, times(1)).showChargers(argThat(list -> list.get(3).address.latitude.equals(35.689487)));

        Mockito.reset(mockView);

        presenter.onSortingClicked(ESorting.POWER);
        verify(mockView, times(1)).showChargers(argThat(list -> Math.abs(list.get(0).getMaxPower() - 22) < 0.001));
        verify(mockView, times(1)).showChargers(argThat(list -> Math.abs(list.get(1).getMaxPower() - 30) < 0.001));
        verify(mockView, times(1)).showChargers(argThat(list -> Math.abs(list.get(2).getMaxPower() - 40) < 0.001));
        verify(mockView, times(1)).showChargers(argThat(list -> Math.abs(list.get(3).getMaxPower() - 50) < 0.001));


    }

}
