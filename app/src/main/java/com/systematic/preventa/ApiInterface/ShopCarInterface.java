package com.systematic.preventa.ApiInterface;

import com.systematic.preventa.Entity.ShopCar;
import com.systematic.preventa.Router.URL;

import retrofit2.Call;
import retrofit2.http.*;


public interface ShopCarInterface {

    @Multipart
    @PUT(URL.PEDIDOS_URL)
    Call<String> storeShopCar(@Part("shopcar")ShopCar shopCar);
}
