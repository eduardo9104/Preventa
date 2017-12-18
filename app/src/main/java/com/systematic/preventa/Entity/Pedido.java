package com.systematic.preventa.Entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.security.PublicKey;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class Pedido implements Serializable {

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("product")
    @Expose
    private Product product;

    @SerializedName("cantidad")
    @Expose
    private int cantidad;


    public Pedido(String id, Product product, int cantidad) {
        this.id = id;
        this.product = product;
        this.cantidad = cantidad;
    }

    public String getId() {
        return id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getCantidad() {
        return cantidad;
    }

    public String getParseCantidad(){
        return NumberFormat.getInstance(Locale.PRC).format(cantidad);
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "id='" + id + '\'' +
                ", product=" + product.getName() +
                ", cantidad=" + cantidad +
                '}';
    }
}
