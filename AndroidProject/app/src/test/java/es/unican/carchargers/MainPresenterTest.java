package es.unican.carchargers;

import es.unican.carchargers.model.Address;
import es.unican.carchargers.model.Charger;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import es.unican.carchargers.activities.main.IMainContract;
import es.unican.carchargers.activities.main.MainPresenter;
import es.unican.carchargers.model.Connection;
import es.unican.carchargers.model.Operator;
import es.unican.carchargers.repository.ICallBack;
import es.unican.carchargers.repository.IRepository;
import es.unican.carchargers.repository.service.APIArguments;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class MainPresenterTest {

    @Mock
    private IMainContract.View mockView;

    private MainPresenter presenter;


    private List<Charger> createTestChargers() {
        List<Charger> chargers = new ArrayList<>();

        Operator operator = new Operator();
        operator.id = 1;
        operator.title = "Operador de Prueba";
        operator.website = "http://example.com";
        operator.comments = "Comentarios del operador";
        Address address = new Address();
        address.title = "Dirección de Prueba";
        address.town = "Ciudad de Prueba";
        address.province = "Provincia de Prueba";
        address.latitude = 40.416775;
        address.longitude = -3.703790;
        Connection connection = new Connection();
        connection.powerKw = "22";
        // Crear el primer cargador
        Charger charger1 = new Charger();
        charger1.id = "1";
        charger1.numberOfPoints = 4;
        charger1.usageCost = "Gratis";
        charger1.operator = operator;
        charger1.address = address;
        charger1.connections.add(connection);

        chargers.add(charger1);

        return chargers;
    }

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        presenter = new MainPresenter();
        presenter.init(mockView);
        List<Charger> testChargers = createTestChargers();

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
}