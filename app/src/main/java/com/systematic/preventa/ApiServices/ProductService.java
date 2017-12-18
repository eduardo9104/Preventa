package com.systematic.preventa.ApiServices;

import android.util.Log;

import com.systematic.preventa.ApiInterface.ProductInterface;
import com.systematic.preventa.Entity.Product;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductService extends ServiceAcces {

    private ProductInterface service;
    private ArrayList<Product> productos;

    public ProductService(String BASE_URL) {
        super(BASE_URL, ProductInterface.class);
        service = (ProductInterface) super.createService();
    }

    public ArrayList<Product> getCatalogo() {

        try {
            callCatalogo();
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return productos;
    }

    private void callCatalogo() {
        Call<List<Product>> call = service.getList();
        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                Log.e("Respuesta catalogo", response.toString());
                productos = (ArrayList<Product>) response.body();
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable throwable) {
                Log.e("Falla catalogo", throwable.getMessage().toString());
            }
        });

    }


}
