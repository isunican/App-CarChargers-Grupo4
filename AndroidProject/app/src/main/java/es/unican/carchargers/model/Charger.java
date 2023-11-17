package es.unican.carchargers.model;

import com.google.gson.annotations.SerializedName;

import es.unican.carchargers.constants.ELocation;

import org.parceler.Parcel;

import java.util.List;

@Parcel
public class Charger {

    @SerializedName("ID")                   public String id;
    @SerializedName("NumberOfPoints")       public int numberOfPoints;
    @SerializedName("UsageCost")            public String usageCost;
    @SerializedName("OperatorInfo")         public Operator operator;
    @SerializedName("AddressInfo")          public Address address;

    @SerializedName("Connections")          public List<Connection> connections;

    private double distanceToUser; // Almacena la distancia al usuario

    public Charger() {
        this.operator = new Operator();
        this.address = new Address();
        // Inicializa otros campos si es necesario

    }

    public double calculateDistanceToSantander() {
        double lat1 = Math.toRadians(address.latitude);
        double lon1 = Math.toRadians(address.longitude);
        double lat2 = Math.toRadians(ELocation.SANTANDER.lat);
        double lon2 = Math.toRadians(ELocation.SANTANDER.lon);

        // Fórmula de Haversine
        double dlon = lon2 - lon1;
        double dlat = lat2 - lat1;
        double a = Math.pow(Math.sin(dlat / 2), 2)
                + Math.cos(lat1) * Math.cos(lat2)
                * Math.pow(Math.sin(dlon / 2),2);
        double c = 2 * Math.asin(Math.sqrt(a));

        // Radio de la Tierra en kilómetros. Usa 3956 para millas
        double r = 6371;

        return c * r;
    }

    // Método para obtener la potencia máxima disponible
    public double getMaxPower() {
        return connections.stream()
                .mapToDouble(Connection::getPower)
                .max()
                .orElse(0.0);
    }
}