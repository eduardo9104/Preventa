package com.systematic.preventa.Entity;



import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Locale;

/**
 * Created by cognitivo on 28/06/17.
 */

public class Cuota implements Serializable {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("cataloge_id")
    @Expose
    private String cataloge_id;
    @SerializedName("monto")
    @Expose
    private Double monto;
    @SerializedName("entry_value")
    @Expose
    private Double entry_value;
    @SerializedName("plazo_to_pay")
    @Expose
    private String plazo_to_pay;

    @SerializedName("plan_id")
    @Expose
    private String plan_id;


    public Cuota(){};

    public Cuota(String id, Double monto, Double entry_value, String plazo_to_pay,String plan_id) {
        this.id = id;
        this.monto = monto;
        this.entry_value = entry_value;
        this.plazo_to_pay = plazo_to_pay;
        this.plan_id = plan_id;
    }

    public Cuota(String id, String cataloge_id , Double monto, String plazo_to_pay, Double entry_value,String plan_id) {
        this.id = id;
        this.cataloge_id = cataloge_id;
        this.monto = monto;
        this.plazo_to_pay = plazo_to_pay;
        this.entry_value = entry_value;
        this.plan_id = plan_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCataloge_id() {
        return cataloge_id;
    }

    public void setCataloge_id(String cataloge_id) {
        this.cataloge_id = cataloge_id;
    }

    public Double getMonto() {
        return monto;
        //return NumberFormat.getInstance(Locale.PRC).format(Double.parseDouble(monto));
        //return monto;
    }

    public String getParseMonto(){
        return NumberFormat.getInstance(Locale.PRC).format(monto);
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public Double getEntry_value() {
        return entry_value;
        //return NumberFormat.getInstance(Locale.PRC).format(Double.parseDouble(entry_value));
    }

    public String getParseEntry_value(){
        return NumberFormat.getInstance(Locale.PRC).format(entry_value);
    }

    public void setEntry_value(Double entry_value) {
        this.entry_value = entry_value;
    }

    public String getPlazo_to_pay() {
        return plazo_to_pay;
    }

    public void setPlazo_to_pay(String plazo_to_pay) {
        this.plazo_to_pay = plazo_to_pay;
    }

    public String getPlan_id() {
        return plan_id;
    }

    public void setPlan_id(String plan_id) {
        this.plan_id = plan_id;
    }

    @Override
    public String toString() {
        return "Cuota{" +
                "id='" + id + '\'' +
                ", cataloge_id='" + cataloge_id + '\'' +
                ", monto=" + monto +
                ", entry_value=" + entry_value +
                ", plazo_to_pay='" + plazo_to_pay + '\'' +
                ", plan_id='" + plan_id + '\'' +
                '}';
    }

}
