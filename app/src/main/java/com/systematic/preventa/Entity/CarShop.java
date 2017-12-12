package com.systematic.preventa.Entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CarShop implements Serializable {

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("pedidos")
    @Expose
    private List<Pedido> pedidos;

    public CarShop(String id, List<Pedido> pedidos) {
        this.id = id;
        this.pedidos = pedidos;
    }

    public CarShop() {
        this.id = UUID.randomUUID().toString();
        this.pedidos = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public boolean addPedido(Pedido pedido){
        return this.pedidos.add(pedido);
    }

    public boolean removePedido(Pedido pedido){
        return this.pedidos.remove(pedido);
    }


}


