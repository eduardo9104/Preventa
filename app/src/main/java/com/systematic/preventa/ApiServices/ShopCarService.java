package com.systematic.preventa.ApiServices;


import android.util.Log;
import com.systematic.preventa.ApiInterface.ShopCarInterface;
import com.systematic.preventa.Entity.ShopCar;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;



public class ShopCarService extends ServiceAcces {

    private ShopCarInterface service;


    public ShopCarService(String BASE_URL) {
        super(BASE_URL, ShopCarInterface.class);
        service = (ShopCarInterface) super.createService();
    }

    public void store(ShopCar shopCar) {
        callStore(shopCar);
    }

    private void callStore(ShopCar shopCar){
        Call<String> call = service.storeShopCar(shopCar);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.e("Respuesta solicitud", response.toString());
            }

            @Override
            public void onFailure(Call<String> call, Throwable throwable) {
                Log.e("Falla retrofit solitud", throwable.getMessage().toString());

            }
        });



    }


}
