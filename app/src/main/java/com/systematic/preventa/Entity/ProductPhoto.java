package com.systematic.preventa.Entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by cognitivo on 13/07/17.
 */

public class ProductPhoto implements Serializable {

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("thumbnail_path")
    @Expose
    private String thumbnail_path;

    public ProductPhoto(String id, String thumbnail_path) {
        this.id = id;
        this.thumbnail_path = thumbnail_path;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getThumbnail_path() {
        return thumbnail_path;
    }

    public void setThumbnail_path(String thumbnail_path) {
        this.thumbnail_path = thumbnail_path;
    }
}
