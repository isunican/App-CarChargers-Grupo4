package es.unican.carchargers.sampledata;


import java.util.ArrayList;
import java.util.List;

import es.unican.carchargers.model.Address;
import es.unican.carchargers.model.Charger;
import es.unican.carchargers.model.Connection;
import es.unican.carchargers.model.Operator;

public class TestUtils {
    public static List<Charger> createTestChargers() {

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
}