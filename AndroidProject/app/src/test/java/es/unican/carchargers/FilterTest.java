package es.unican.carchargers;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
import es.unican.carchargers.model.Address;
import es.unican.carchargers.model.Charger;
import es.unican.carchargers.model.Connection;
import es.unican.carchargers.model.Operator;
import es.unican.carchargers.repository.ICallBack;
import es.unican.carchargers.repository.IRepository;
import es.unican.carchargers.repository.service.APIArguments;


public class FilterTest {

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
        address1.latitude = 40.416775;
        address1.longitude = -3.703790;
        Connection connection1 = new Connection();
        connection1.powerKw = "22";
        Charger charger1 = new Charger();
        charger1.id = "1";
        charger1.numberOfPoints = 4;
        charger1.usageCost = "Gratis";
        charger1.operator = operator1;
        charger1.address = address1;
        charger1.connections = new ArrayList<>();
        charger1.connections.add(connection1);

        chargers.add(charger1);

        Operator operator2 = new Operator();
        operator2.id = 3534;
        operator2.title = "Tesla";
        operator2.website = "http://example.com";
        operator2.comments = "Comentarios del operador";
        Address address2 = new Address();
        address2.title = "Dirección de Prueba";
        address2.town = "Ciudad de Prueba";
        address2.province = "Provincia de Prueba";
        address2.latitude = 40.416775;
        address2.longitude = -3.703790;
        Connection connection2 = new Connection();
        connection2.powerKw = "22";
        Charger charger2 = new Charger();
        charger2.id = "1";
        charger2.numberOfPoints = 4;
        charger2.usageCost = "Gratis";
        charger2.operator = operator2;
        charger2.address = address2;
        charger2.connections = new ArrayList<>();
        charger2.connections.add(connection2);

        chargers.add(charger2);

        Operator operator3 = new Operator();
        operator3.id = 3299;
        operator3.title = "Ionity";
        operator3.website = "http://example.com";
        operator3.comments = "Comentarios del operador";
        Address address3 = new Address();
        address3.title = "Dirección de Prueba";
        address3.town = "Ciudad de Prueba";
        address3.province = "Provincia de Prueba";
        address3.latitude = 40.416775;
        address3.longitude = -3.703790;
        Connection connection3 = new Connection();
        connection3.powerKw = "22";
        Charger charger3 = new Charger();
        charger3.id = "1";
        charger3.numberOfPoints = 4;
        charger3.usageCost = "Gratis";
        charger3.operator = operator3;
        charger3.address = address3;
        charger3.connections = new ArrayList<>();
        charger3.connections.add(connection3);

        chargers.add(charger3);

        Operator operator4 = new Operator();
        operator4.id = 2247;
        operator4.title = "Iberdrola";
        operator4.website = "http://example.com";
        operator4.comments = "Comentarios del operador";
        Address address4 = new Address();
        address4.title = "Dirección de Prueba";
        address4.town = "Ciudad de Prueba";
        address4.province = "Provincia de Prueba";
        address4.latitude = 40.416775;
        address4.longitude = -3.703790;
        Connection connection4 = new Connection();
        connection4.powerKw = "22";
        Charger charger4 = new Charger();
        charger4.id = "1";
        charger4.numberOfPoints = 4;
        charger4.usageCost = "Gratis";
        charger4.operator = operator4;
        charger4.address = address4;
        charger4.connections = new ArrayList<>();
        charger4.connections.add(connection4);

        chargers.add(charger4);

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
    public void testChargerLoading() {
        Charger testCharger = createTestChargers().get(0);
        List<Charger> chargers = new ArrayList<>();
        chargers.add(testCharger);

        IRepository mockRepository = mock(IRepository.class);
        doAnswer(invocation -> {
            ICallBack callback = invocation.getArgument(1);
            callback.onSuccess(chargers);
            return null;
        }).when(mockRepository).requestChargers(any(APIArguments.class), any(ICallBack.class));

        when(mockView.getRepository()).thenReturn(mockRepository);

        presenter.init(mockView);

        verify(mockView, times(1)).showChargers(chargers);
        assertEquals("Verificar ID del cargador", "1", chargers.get(0).id);
        assertEquals("Verificar número de puntos", 4, chargers.get(0).numberOfPoints);
    }

    @Test
    public void testOperatorFilter() {
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

        int activeFilterCount = presenter.onOperatorFilterClicked(EOperator.ZUNDER, true);
        assertEquals("Verificar conteo de filtros activos tras activar ZUNDER", 1, activeFilterCount);

        activeFilterCount = presenter.onOperatorFilterClicked(EOperator.TESLA, true);
        assertEquals("Verificar conteo de filtros activos tras activar OTRO_OPERADOR", 2, activeFilterCount);

        activeFilterCount = presenter.onOperatorFilterClicked(EOperator.ZUNDER, false);
        assertEquals("Verificar conteo de filtros activos tras desactivar ZUNDER", 1, activeFilterCount);
    }

    @Test
    public void testApplyFilters() {
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

        presenter.onOperatorFilterClicked(EOperator.ZUNDER, true);
        verify(mockView, times(1)).showChargers(argThat(list -> list.size() == 1));

        Mockito.reset(mockView);

        presenter.onOperatorFilterClicked(EOperator.TESLA, true);
        verify(mockView, times(1)).showChargers(argThat(list -> list.size() == 2));

        Mockito.reset(mockView);

        presenter.onOperatorFilterClicked(EOperator.IBERDROLA, true);
        verify(mockView, times(1)).showChargers(argThat(list -> list.size() == 3));

        Mockito.reset(mockView);

        presenter.onOperatorFilterClicked(EOperator.IONITY, true);
        verify(mockView, times(1)).showChargers(argThat(list -> list.size() == 4));

        Mockito.reset(mockView);

        presenter.onOperatorFilterClicked(EOperator.IONITY, false);
        verify(mockView, times(1)).showChargers(argThat(list -> list.size() == 3));
    }
}
