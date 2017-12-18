package com.systematic.preventa.ApiInterface;

import com.systematic.preventa.Entity.Product;
import com.systematic.preventa.Router.URL;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;



public interface ProductInterface {
    @FormUrlEncoded
    @POST(URL.PRODUCT_URL)
    Call<List<Product>> getList();
}
