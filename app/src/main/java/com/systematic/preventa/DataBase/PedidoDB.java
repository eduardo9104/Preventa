package com.systematic.preventa.DataBase;

import com.systematic.preventa.Entity.Pedido;
import com.systematic.preventa.Entity.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by redbaron on 10/12/17.
 */

public class PedidoDB {
    public static Pedido getRandomPedido(){
        return new Pedido(
                UUID.randomUUID().toString(),
                ProductDB.getRandomProduct(),
                1
        );
    }

    public static Pedido createByCantidadAndProduct(String cantidad, Product product){
        return new Pedido(
                UUID.randomUUID().toString(),
                product,
                Integer.valueOf(cantidad)
        );
    }
}
