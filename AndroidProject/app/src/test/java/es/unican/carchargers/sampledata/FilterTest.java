package es.unican.carchargers.sampledata;

import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import es.unican.carchargers.activities.main.MainPresenter;
import es.unican.carchargers.constants.EOperator;
import es.unican.carchargers.model.Charger;
import es.unican.carchargers.repository.ICallBack;
import es.unican.carchargers.repository.IRepository;
import es.unican.carchargers.repository.service.APIArguments;

public class FilterTest extends TestBase {

    @Test
    public void testChargerLoading() {
        Charger testCharger = TestUtils.createTestChargers().get(0);
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

        int activeFilterCount = presenter.onOperatorFilterClicked(EOperator.ZUNDER, true);
        assertEquals("Verificar conteo de filtros activos tras activar ZUNDER", 1, activeFilterCount);

        activeFilterCount = presenter.onOperatorFilterClicked(EOperator.TESLA, true);
        assertEquals("Verificar conteo de filtros activos tras activar OTRO_OPERADOR", 2, activeFilterCount);

        activeFilterCount = presenter.onOperatorFilterClicked(EOperator.ZUNDER, false);
        assertEquals("Verificar conteo de filtros activos tras desactivar ZUNDER", 1, activeFilterCount);
    }

    @Test
    public void testApplyFilters() {
        // Preparar la lista de cargadores
        List<Charger> chargers = TestUtils.createTestChargers();

        // Configurar el mock del repositorio
        IRepository mockRepository = mock(IRepository.class);
        doAnswer(invocation -> {
            ICallBack callback = invocation.getArgument(1);
            callback.onSuccess(chargers);
            return null;
        }).when(mockRepository).requestChargers(any(APIArguments.class), any(ICallBack.class));

        when(mockView.getRepository()).thenReturn(mockRepository);

        // Inicializar el presentador
        presenter.init(mockView);

        // Configuración de los filtros y verificación de los resultados usando applyFilters()
        // Suponiendo que 'activeFilters' es accesible y modificable desde aquí
        // Si no es así, necesitarías exponer alguna funcionalidad para manipular los filtros activos

        // Activar filtro para ZUNDER
        presenter.activeFilters.add(EOperator.ZUNDER);
        presenter.applyFilters();
        verify(mockView, times(1)).showChargers(argThat(list -> list.size() == 1));
        reset(mockView);

        // Activar filtro para TESLA
        presenter.activeFilters.add(EOperator.TESLA);
        presenter.applyFilters();
        verify(mockView, times(1)).showChargers(argThat(list -> list.size() == 2));
        reset(mockView);

        // Activar filtro para IBERDROLA
        presenter.activeFilters.add(EOperator.IBERDROLA);
        presenter.applyFilters();
        verify(mockView, times(1)).showChargers(argThat(list -> list.size() == 3));
        reset(mockView);
        // Activar filtro para IONITY
        presenter.activeFilters.add(EOperator.IONITY);
        presenter.applyFilters();
        verify(mockView, times(1)).showChargers(argThat(list -> list.size() == 4));
        reset(mockView);

        // Desactivar filtro para IONITY
        presenter.activeFilters.remove(EOperator.IONITY);
        presenter.applyFilters();
        verify(mockView, times(1)).showChargers(argThat(list -> list.size() == 3));
        reset(mockView);
    }
}