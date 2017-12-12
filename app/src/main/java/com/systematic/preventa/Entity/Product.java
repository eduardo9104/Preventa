package com.systematic.preventa.Entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by cognitivo on 18/05/17.
 */

public class Product implements Serializable {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("product_name")
    @Expose
    private String name;
    @SerializedName("product_code")
    @Expose
    private String code;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("id_user")
    @Expose
    private String id_user;
    @SerializedName("details")
    @Expose
    private List<ProductPhoto> details;

    @SerializedName("cuotas")
    @Expose
    private List<Cuota> cuotas;

    @SerializedName("tags")
    @Expose
    private List<Tag> tags;


    public Product(String id, String name,String code,String description, List<ProductPhoto> details,List<Tag> tags){
        this.id = id;
        this.name = name;
        this.code = code;
        this.description = description;
        this.details = details;
        this.tags = tags;
    }

    public Product(String id, String name,String code, String id_user,String description, List<ProductPhoto> details,List<Tag> tags, List<Cuota> cuotas) {
        this.id = id;
        this.name = name;
        this.id_user = id_user;
        this.details = details;
        this.tags = tags;
        this.code = code;
        this.description = description;
        this.cuotas = cuotas;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getId_user() {
        return id_user;
    }

    public void setId_user(String id_user) {
        this.id_user = id_user;
    }

    public List<ProductPhoto> getDetails() {
        return details;
    }

    public void setDetails(List<ProductPhoto> details) {
        this.details = details;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Cuota> getCuotas() {
        return cuotas;
    }

    public void setCuotas(List<Cuota> cuotas) {
        this.cuotas = cuotas;
    }

    public String cuotasStringFormat(){
        String res = "";
        for (Cuota cuota:cuotas) {

            res+=cuota.getMonto()+"\n";

        }
        return res;
    }
}
