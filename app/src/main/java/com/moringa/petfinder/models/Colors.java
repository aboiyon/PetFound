
package com.moringa.petfinder.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

//@Parcel
@Generated("jsonschema2pojo")
public class Colors {

    @SerializedName("primary")
    @Expose
    private Object primary;
    @SerializedName("secondary")
    @Expose
    private Object secondary;
    @SerializedName("tertiary")
    @Expose
    private Object tertiary;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Colors() {
    }

    /**
     * 
     * @param secondary
     * @param tertiary
     * @param primary
     */
    public Colors(Object primary, Object secondary, Object tertiary) {
        super();
        this.primary = primary;
        this.secondary = secondary;
        this.tertiary = tertiary;
    }

    public Object getPrimary() {
        return primary;
    }

    public void setPrimary(Object primary) {
        this.primary = primary;
    }

    public Object getSecondary() {
        return secondary;
    }

    public void setSecondary(Object secondary) {
        this.secondary = secondary;
    }

    public Object getTertiary() {
        return tertiary;
    }

    public void setTertiary(Object tertiary) {
        this.tertiary = tertiary;
    }

}
