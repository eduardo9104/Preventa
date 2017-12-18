package com.systematic.preventa.ApiServices;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.systematic.preventa.Router.URL;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;



public class ServiceAcces {

    private String BASE_URL;
    private Class clase;

    public ServiceAcces(String BASE_URL, Class clase) {
        this.BASE_URL = BASE_URL;
        this.clase = clase;
    }
    public ServiceAcces() {
        this.BASE_URL = URL.BASE_URL;
    }
    protected Object createService(){
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").create();
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create(gson)).build();
        return retrofit.create(clase);
    }


}
