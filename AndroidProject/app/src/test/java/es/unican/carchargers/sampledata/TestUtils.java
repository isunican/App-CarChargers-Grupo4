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
        Connection connection1a = new Connection();
        connection1a.powerKw = "10";
        Connection connection1b = new Connection();
        connection1b.powerKw = "11";
        Connection connection1c = new Connection();
        connection1c.powerKw = "12";
        Charger charger1 = new Charger();
        charger1.id = "1";
        charger1.numberOfPoints = 4;
        charger1.usageCost = "Gratis";
        charger1.operator = operator1;
        charger1.address = address1;
        charger1.connections = new ArrayList<>();
        charger1.connections.add(connection1a);
        charger1.connections.add(connection1b);
        charger1.connections.add(connection1c);

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
        Connection connection2a = new Connection();
        connection2a.powerKw = "20";
        Connection connection2b = new Connection();
        connection2b.powerKw = "21";
        Connection connection2c = new Connection();
        connection2c.powerKw = "22";
        Charger charger2 = new Charger();
        charger2.id = "1";
        charger2.numberOfPoints = 4;
        charger2.usageCost = "Gratis";
        charger2.operator = operator2;
        charger2.address = address2;
        charger2.connections = new ArrayList<>();
        charger2.connections.add(connection2a);
        charger2.connections.add(connection2b);
        charger2.connections.add(connection2c);

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
        Connection connection3a = new Connection();
        connection3a.powerKw = "30";
        Connection connection3b = new Connection();
        connection3b.powerKw = "31";
        Connection connection3c = new Connection();
        connection3c.powerKw = "32";
        Charger charger3 = new Charger();
        charger3.id = "1";
        charger3.numberOfPoints = 4;
        charger3.usageCost = "Gratis";
        charger3.operator = operator3;
        charger3.address = address3;
        charger3.connections = new ArrayList<>();
        charger3.connections.add(connection3a);
        charger3.connections.add(connection3b);
        charger3.connections.add(connection3c);
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
        Connection connection4a = new Connection();
        connection4a.powerKw = "40";
        Charger charger4 = new Charger();
        Connection connection4b = new Connection();
        connection4b.powerKw = "41";
        Connection connection4c = new Connection();
        connection4c.powerKw = "42";
        charger4.id = "1";
        charger4.numberOfPoints = 4;
        charger4.usageCost = "Gratis";
        charger4.operator = operator4;
        charger4.address = address4;
        charger4.connections = new ArrayList<>();
        charger4.connections.add(connection4a);
        charger4.connections.add(connection4b);
        charger4.connections.add(connection4c);

        chargers.add(charger4);

        return chargers;
    }
}