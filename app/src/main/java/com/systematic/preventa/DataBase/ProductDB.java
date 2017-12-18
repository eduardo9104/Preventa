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
        cuotas.add(new Cuota("1", 100.0, 2.0, "11/11/11","2"));
        cuotas.add(new Cuota("3", 50.0, 2.0, "11/11/11","2"));
        List<Tag> tags = new ArrayList<>();
        tags.add(new Tag("1","Product","product"));
        List<ProductPhoto> details = new ArrayList<>();
        details.add(new ProductPhoto("1","producto.png"));
        return new Product(
                UUID.randomUUID().toString()+String.valueOf(new Random().nextInt(100)),
                randomName(),
                String.valueOf(new Random().nextInt(100000000)),
                "1",randomDescription(),
                details,
                tags,
                cuotas
        );
    }

    private static String randomName(){
        String[] names = {"Samsung Galaxy", "HP 650", "Unitec BRF", "Philip 345"};
        return names[new Random().nextInt(4)];
    }
    private static String randomDescription(){
        String[] names = {
                "Tablets are a fast-growing part of the Android installed base and they offer new opportunities for user engagement and monetization. If you are targeting tablets, check out this list of tips and techniques on how to deliver a great app experience for tablet users. "
                , "To develop apps for Android devices, you use a set of tools that are included in the Android SDK. Once you've downloaded and installed the SDK, you can access these tools right from your Eclipse IDE, through the ADT plugin, or from the command line. Developing with Eclipse is the preferred method because it can directly invoke the tools that you need while developing applications.",
                "The basic steps for developing applications (with or without Eclipse) are shown in figure 1. The development steps encompass four development phases, which include:",
                "During this phase you set up and develop your Android project, which contains all of the source code and resource files for your application. For more information, see Create an Android project."
        };
        return names[new Random().nextInt(4)];
    }
}
