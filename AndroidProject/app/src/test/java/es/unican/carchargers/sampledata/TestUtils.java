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

        Operator operator5 = new Operator();
        operator5.id = 3324;
        operator5.title = "Zunder";
        operator5.website = "http://example.com";
        operator5.comments = "Comentarios del operador";
        Address address5 = new Address();
        address5.title = "Dirección de Prueba";
        address5.town = "Ciudad de Prueba";
        address5.province = "Provincia de Prueba";
        address5.latitude = 40.416775; // Madrid, la mas cercana a santander 40.416775, -3.703790
        address5.longitude = -3.703790;
        Connection connection5 = new Connection();
        connection5.powerKw = "22";
        Charger charger5 = new Charger();
        charger5.id = "1";
        charger5.numberOfPoints = 4;
        charger5.usageCost = "0,70€/kWh AC";
        charger5.operator = operator5;
        charger5.address = address5;
        charger5.connections = new ArrayList<>();
        charger5.connections.add(connection5);

        Operator operator6 = new Operator();
        operator6.id = 3534;
        operator6.title = "Tesla";
        operator6.website = "http://example.com";
        operator6.comments = "Comentarios del operador";
        Address address6 = new Address();
        address6.title = "Dirección de Prueba";
        address6.town = "Ciudad de Prueba";
        address6.province = "Provincia de Prueba";
        address6.latitude = 48.856613; //Paris, la segunda más cercana a Santander 48.856613, 2.352222
        address6.longitude = -2.352222;
        Connection connection6 = new Connection();
        connection6.powerKw = "30";
        Charger charger6 = new Charger();
        charger6.id = "1";
        charger6.numberOfPoints = 4;
        charger6.usageCost = "0,39€/kWh AC";
        charger6.operator = operator6;
        charger6.address = address6;
        charger6.connections = new ArrayList<>();
        charger6.connections.add(connection6);

        Operator operator7 = new Operator();
        operator7.id = 3299;
        operator7.title = "Ionity";
        operator7.website = "http://example.com";
        operator7.comments = "Comentarios del operador";
        Address address7 = new Address();
        address7.title = "Dirección de Prueba";
        address7.town = "Ciudad de Prueba";
        address7.province = "Provincia de Prueba";
        address7.latitude = 41.902782; // Roma, la tercera más cercana a Santander 41.902782, 12.496366
        address7.longitude = -12.496366;
        Connection connection7 = new Connection();
        connection7.powerKw = "40";
        Charger charger7 = new Charger();
        charger7.id = "1";
        charger7.numberOfPoints = 4;
        charger7.usageCost = "0,45€/kWh AC";
        charger7.operator = operator7;
        charger7.address = address7;
        charger7.connections = new ArrayList<>();
        charger7.connections.add(connection7);

        Operator operator8 = new Operator();
        operator8.id = 2247;
        operator8.title = "Iberdrola";
        operator8.website = "http://example.com";
        operator8.comments = "Comentarios del operador";
        Address address8 = new Address();
        address8.title = "Dirección de Prueba";
        address8.town = "Ciudad de Prueba";
        address8.province = "Provincia de Prueba";
        address8.latitude = 35.689487; // Tokio, la cuarta más cercana a Santander 35.689487, 139.691706
        address8.longitude = -139.691706;
        Connection connection8 = new Connection();
        connection8.powerKw = "50";
        Charger charger8 = new Charger();
        charger8.id = "1";
        charger8.numberOfPoints = 4;
        charger8.usageCost = "0,47€/kWh AC";
        charger8.operator = operator8;
        charger8.address = address8;
        charger8.connections = new ArrayList<>();
        charger8.connections.add(connection8);

        chargers.add(charger2);
        chargers.add(charger1);
        chargers.add(charger4);
        chargers.add(charger3);
        chargers.add(charger5);
        chargers.add(charger6);
        chargers.add(charger7);
        chargers.add(charger8);

        return chargers;
    }
}