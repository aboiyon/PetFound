
package com.moringa.petfinder.models;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class Links__1 {

    @SerializedName("next")
    @Expose
    private Next next;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Links__1() {
    }

    /**
     * 
     * @param next
     */
    public Links__1(Next next) {
        super();
        this.next = next;
    }

    public Next getNext() {
        return next;
    }

    public void setNext(Next next) {
        this.next = next;
    }

}
