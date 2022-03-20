
package com.moringa.petfinder.models;

import android.os.Environment;

import androidx.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

import java.util.List;

@Parcel
public class Animal {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("organization_id")
    @Expose
    private String organizationId;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("species")
    @Expose
    private String species;
    @SerializedName("breeds")
    @Expose
    private Breeds breeds;
//    @SerializedName("colors")
//    @Expose
//    private Colors colors;
    @SerializedName("age")
    @Expose
    private String age;
    @SerializedName("gender")
    @Expose
    private String gender;
//    @SerializedName("size")
//    @Expose
//    private String size;
//    @SerializedName("coat")
//    @Expose
//    private Object coat;
    @SerializedName("attributes")
    @Expose
    private Attributes attributes;
//    @SerializedName("environment")
//    @Expose
//    private Environment environment;
//    @SerializedName("tags")
//    @Expose
//    private List<Object> tags = null;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("description")
    @Expose
    private String description;
//    @SerializedName("organization_animal_id")
//    @Expose
//    private String organizationAnimalId;
//    @SerializedName("photos")
//    @Expose
//    private List<Object> photos = null;
//    @SerializedName("primary_photo_cropped")
//    @Expose
//    private Object primaryPhotoCropped;
//    @SerializedName("videos")
//    @Expose
//    private List<Object> videos = null;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("status_changed_at")
    @Expose
    private String statusChangedAt;
    @SerializedName("published_at")
    @Expose
    private String publishedAt;
//    @SerializedName("distance")
//    @Expose
//    private Object distance;
    @SerializedName("contact")
    @Expose
    private Contact contact;
    @SerializedName("_links")
    @Expose
    private Links links;
    //pinpoint exact object to add/delete
    private String pushId;


    /**
     * No args constructor for use in serialization
     * 
     */
    public Animal() {
    }

    /**
     * 
     * @param gender
     * @param distance
     * @param description
     * @param videos
     * @param type
     * @param photos
//     * @param colors
     * @param breeds
     * @param organizationId
     * @param statusChangedAt
     * @param organizationAnimalId
     * @param contact
     * @param links
     * @param id
     * @param publishedAt
     * @param url
     * @param primaryPhotoCropped
     * @param tags
     * @param coat
     * @param environment
     * @param size
     * @param species
     * @param name
     * @param attributes
     * @param age
     * @param status
     */
    public Animal(@NonNull Integer id, String organizationId, String url, String type, String species, Breeds breeds, String age, String gender, String size, Object coat, Attributes attributes, Environment environment, List<Object> tags, String name, String description, String organizationAnimalId, List<Object> photos, Object primaryPhotoCropped, List<Object> videos, String status, String statusChangedAt, String publishedAt, Object distance, Contact contact, Links links) {
        super();
        this.id = id;
        this.organizationId = organizationId;
        this.url = url;
        this.type = type;
        this.species = species;
        this.breeds = breeds;
//        this.colors = colors;
        this.age = age;
        this.gender = gender;
//        this.size = size;
//        this.coat = coat;
        this.attributes = attributes;
//        this.environment = environment;
//        this.tags = tags;
        this.name = name;
        this.description = description;
//        this.organizationAnimalId = organizationAnimalId;
//        this.photos = photos;
//        this.primaryPhotoCropped = primaryPhotoCropped;
//        this.videos = videos;
        this.status = status;
        this.statusChangedAt = statusChangedAt;
        this.publishedAt = publishedAt;
//        this.distance = distance;
        this.contact = contact;
        this.links = links;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public Breeds getBreeds() {
        return breeds;
    }

    public void setBreeds(Breeds breeds) {
        this.breeds = breeds;
    }

//    public Colors getColors() {
//        return colors;
//    }

//    public void setColors(Colors colors) {
//        this.colors = colors;
//    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

//    public String getSize() {
//        return size;
//    }
//
//    public void setSize(String size) {
//        this.size = size;
//    }

//    public Object getCoat() {
//        return coat;
//    }

//    public void setCoat(Object coat) {
//        this.coat = coat;
//    }

    public Attributes getAttributes() {
        return attributes;
    }

    public void setAttributes(Attributes attributes) {
        this.attributes = attributes;
    }

//    public Environment getEnvironment() {
//        return environment;
//    }
//
//    public void setEnvironment(Environment environment) {
//        this.environment = environment;
//    }

//    public List<Object> getTags() {
//        return tags;
//    }
//
//    public void setTags(List<Object> tags) {
//        this.tags = tags;
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

//    public String getOrganizationAnimalId() {
//        return organizationAnimalId;
//    }
//
//    public void setOrganizationAnimalId(String organizationAnimalId) {
//        this.organizationAnimalId = organizationAnimalId;
//    }

//    public List<Object> getPhotos() {
//        return photos;
//    }
//
//    public void setPhotos(List<Object> photos) {
//        this.photos = photos;
//    }
//
//    public Object getPrimaryPhotoCropped() {
//        return primaryPhotoCropped;
//    }
//
//    public void setPrimaryPhotoCropped(Object primaryPhotoCropped) {
//        this.primaryPhotoCropped = primaryPhotoCropped;
//    }
//
//    public List<Object> getVideos() {
//        return videos;
//    }
//
//    public void setVideos(List<Object> videos) {
//        this.videos = videos;
//    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusChangedAt() {
        return statusChangedAt;
    }

    public void setStatusChangedAt(String statusChangedAt) {
        this.statusChangedAt = statusChangedAt;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

//    public void setPublishedAt(String publishedAt) {
//        this.publishedAt = publishedAt;
//    }
//
//    public Object getDistance() {
//        return distance;
//    }

//    public void setDistance(Object distance) {
//        this.distance = distance;
//    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public Links getLinks() {
        return links;
    }

    public void setLinks(Links links) {
        this.links = links;
    }
//retrieve or assign relevant id to an object
    public String getPushId() {
        return pushId;
    }

    public void setPushId(String pushId) {
        this.pushId = pushId;
    }

}
