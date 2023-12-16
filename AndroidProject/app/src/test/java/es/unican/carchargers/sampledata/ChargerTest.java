package es.unican.carchargers.sampledata;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.argThat;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import es.unican.carchargers.constants.ESorting;
import es.unican.carchargers.model.Charger;
import es.unican.carchargers.repository.ICallBack;
import es.unican.carchargers.repository.IRepository;
import es.unican.carchargers.repository.service.APIArguments;

public class ChargerTest extends TestBase {

    @Test
    public void testGetMaxPower() {
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

        assertEquals( 12, testCharger1.getMaxPower(),0.1);
        assertEquals( 22, testCharger2.getMaxPower(), 0.1);
        assertEquals( 32, testCharger3.getMaxPower(),0.1);
        assertEquals( 42, testCharger4.getMaxPower(),0.1);

    }
}
