package es.unican.carchargers.model;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

/**
 * The address of a charging station according to the OpenChargeMap API
 * Documentation: https://openchargemap.org/site/develop/api#/operations/get-poi
 *
 * Currently it only includes a sub-set of the complete model returned by OpenChargeMap
 */
@Parcel
public class Address {

    @SerializedName("Title")            public String title;
    @SerializedName("Town")             public String town;
    @SerializedName("StateOrProvince")  public String province;
    @SerializedName("Latitude")         public String latitude;
    @SerializedName("Longitude")        public String longitude;

}
