package com.systematic.preventa.Entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ShopCar implements Serializable {

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("pedidos")
    @Expose
    private List<Pedido> pedidos;

    @SerializedName("user_id")
    @Expose
    private String user_id;

    public ShopCar(String id, List<Pedido> pedidos, String user_id) {
        this.id = id;
        this.pedidos = pedidos;
        this.user_id = user_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public ShopCar(String user_id, List<Pedido> pedidos) {
        this.id = UUID.randomUUID().toString();
        this.pedidos = pedidos;
        this.user_id = user_id;
    }

    public String getTotal(){
        //TODO sumar el total correctamente
        double total = 0;
        for(Pedido pedido:pedidos){
            total+=pedido.getProduct().getCuotas().get(0).getMonto();
        }
        return String.valueOf(total);
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


