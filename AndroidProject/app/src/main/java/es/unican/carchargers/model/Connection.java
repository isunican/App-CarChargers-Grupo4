package es.unican.carchargers.model;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

@Parcel
public class Connection {
    @SerializedName("PowerKW")            public String powerKw;
    public double getPower() {
        if (this.powerKw != null && !this.powerKw.isEmpty()) {
            return Double.parseDouble(this.powerKw.trim());
        } else {

            return 0.0; // or whatever default value you find appropriate
        }
    }

}