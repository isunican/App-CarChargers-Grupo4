package es.unican.carchargers.model;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

/**
 * An operator of a charging station according to the OpenChargeMap API
 * Documentation: https://openchargemap.org/site/develop/api#/operations/get-poi
 *
 * Currently it only includes a sub-set of the complete model returned by OpenChargeMap
 */
@Parcel
public class Operator {

    @SerializedName("ID")               public int id;
    @SerializedName("Title")            public String title;
    @SerializedName("WebsiteURL")       public String website;
    @SerializedName("Comments")         public String comments;
}
