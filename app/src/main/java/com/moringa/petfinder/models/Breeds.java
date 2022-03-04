
package com.moringa.petfinder.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

import javax.annotation.Generated;

@Parcel
@Generated("jsonschema2pojo")
public class Breeds {

    @SerializedName("primary")
    @Expose
    private String primary;
    @SerializedName("secondary")
    @Expose
    private String secondary;
    @SerializedName("mixed")
    @Expose
    private Boolean mixed;
    @SerializedName("unknown")
    @Expose
    private Boolean unknown;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Breeds() {
    }

    /**
     * 
     * @param secondary
     * @param mixed
     * @param primary
     * @param unknown
     */
    public Breeds(String primary, String secondary, Boolean mixed, Boolean unknown) {
        super();
        this.primary = primary;
        this.secondary = secondary;
        this.mixed = mixed;
        this.unknown = unknown;
    }

    public String getPrimary() {
        return primary;
    }

    public void setPrimary(String primary) {
        this.primary = primary;
    }

    public String getSecondary() {
        return secondary;
    }

    public void setSecondary(String secondary) {
        this.secondary = secondary;
    }

    public Boolean getMixed() {
        return mixed;
    }

    public void setMixed(Boolean mixed) {
        this.mixed = mixed;
    }

    public Boolean getUnknown() {
        return unknown;
    }

    public void setUnknown(Boolean unknown) {
        this.unknown = unknown;
    }

}
