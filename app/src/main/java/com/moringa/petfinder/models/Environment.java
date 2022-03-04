
package com.moringa.petfinder.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

import javax.annotation.Generated;

@Parcel
@Generated("jsonschema2pojo")
public class Environment {

    @SerializedName("children")
    @Expose
    private Object children;
    @SerializedName("dogs")
    @Expose
    private Object dogs;
    @SerializedName("cats")
    @Expose
    private Object cats;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Environment() {
    }

    /**
     * 
     * @param cats
     * @param children
     * @param dogs
     */
    public Environment(Object children, Object dogs, Object cats) {
        super();
        this.children = children;
        this.dogs = dogs;
        this.cats = cats;
    }

    public Object getChildren() {
        return children;
    }

    public void setChildren(Object children) {
        this.children = children;
    }

    public Object getDogs() {
        return dogs;
    }

    public void setDogs(Object dogs) {
        this.dogs = dogs;
    }

    public Object getCats() {
        return cats;
    }

    public void setCats(Object cats) {
        this.cats = cats;
    }

}
