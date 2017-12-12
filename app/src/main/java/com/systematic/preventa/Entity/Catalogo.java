package com.systematic.preventa.Entity;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class Catalogo implements Serializable {

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("productos")
    @Expose
    private List<Product> productos;


    public Catalogo(String id, List<Product> productos) {
        this.id = id;
        this.productos = productos;
    }

    public Catalogo() {
        this.id = UUID.randomUUID().toString();
        this.productos = new ArrayList<>();
    }

    public String getId() {
        return id;
    }
    public List<Product> getProductos() {
        return productos;
    }

    public void setProductos(List<Product> productos) {
        this.productos = productos;
    }

    public boolean addProducto(Product product){
        return this.productos.add(product);
    }

    public boolean removeProducto(Product product){
        return this.productos.remove(product);
    }

    public List<Product> findProductByName(String name){
        List<Product> result = new ArrayList<>();
        for (Product producto:this.productos) {
            if (producto.getName().contains(name))
                result.add(producto);
        }
        return result;
    }


}
