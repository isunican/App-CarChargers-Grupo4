package es.unican.carchargers.constants;

/**
 * Static list of locations with their coordinates
 */
public enum ELocation {

    SANTANDER (43.462776, -3.805000);

    public final double lat;
    public final double lon;

    private ELocation(double lat, double lon) {
        this.lat = lat;
        this.lon = lon;
    }

}
