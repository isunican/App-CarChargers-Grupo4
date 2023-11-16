package es.unican.carchargers.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Utils {

    /**
     * Converts the given input stream to a String
     * @param is
     * @return
     */
    public static String toString(InputStream is) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            StringBuilder out = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                out.append(line);
            }

            String fileContent = out.toString();
            reader.close();
            return fileContent;

        } catch (IOException e) {}
        return null;
    }

    /**
     * Calcula la distancia entre dos puntos geográficos.
     *
     * @param lat1 Latitud del primer punto
     * @param lon1 Longitud del primer punto
     * @param lat2 Latitud del segundo punto
     * @param lon2 Longitud del segundo punto
     * @return Distancia en kilómetros
     */

}
