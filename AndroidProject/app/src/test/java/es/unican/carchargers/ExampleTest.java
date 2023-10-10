package es.unican.carchargers;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import es.unican.carchargers.activities.main.IMainContract;
import es.unican.carchargers.activities.main.MainPresenter;
import es.unican.carchargers.model.Charger;
import es.unican.carchargers.repository.IRepository;
import es.unican.carchargers.repository.Repositories;

public class ExampleTest {

    @Test
    public void test() {
        Charger charger = new Charger();
        charger.id = "0";
        assertEquals("0", charger.id);
    }
}