package com.pes.verduras_android;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Ingrediente{

    @SerializedName("ingrediente_Receta")
    @Expose
    public String ingredienteReceta;

    @SerializedName("cantidad")
    @Expose
    private String cantidad;

    @SerializedName("receta")
    @Expose
    public Receta recetario;


    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getCantidad() {
        return cantidad;
    }

}
