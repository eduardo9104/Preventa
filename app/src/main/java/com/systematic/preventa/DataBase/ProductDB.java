package com.systematic.preventa.DataBase;

import com.systematic.preventa.Entity.Cuota;
import com.systematic.preventa.Entity.Product;
import com.systematic.preventa.Entity.ProductPhoto;
import com.systematic.preventa.Entity.Tag;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

/**
 * Created by redbaron on 10/12/17.
 */

public class ProductDB {

    public static Product getRandomProduct(){
        List<Cuota> cuotas = new ArrayList<>();
        cuotas.add(new Cuota("1", 10.0, 2.0, "11/11/11","2"));
        List<Tag> tags = new ArrayList<>();
        tags.add(new Tag("1","Product","product"));
        List<ProductPhoto> details = new ArrayList<>();
        details.add(new ProductPhoto("1","producto.png"));
        return new Product(
                UUID.randomUUID().toString()+String.valueOf(new Random().nextInt(100)),
                "Product",
                String.valueOf(new Random().nextInt(100000000)),
                "1","Este producto",
                details,
                tags,
                cuotas
        );
    }
}
