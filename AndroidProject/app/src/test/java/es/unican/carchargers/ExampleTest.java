package es.unican.carchargers;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import es.unican.carchargers.model.Charger;

public class ExampleTest {

    @Test
    public void test() {
        Charger charger = new Charger();
        charger.numberOfPoints = 5;
        assertEquals(charger.numberOfPoints, 5);
    }
}