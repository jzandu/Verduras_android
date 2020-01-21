package com.pes.verduras_android;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Receta{
    @SerializedName("nombre")
    @Expose
    private String nombre;
    @SerializedName("texto")
    @Expose
    private String texto;
    @SerializedName("ingrediente_por_receta")
    @Expose
    private List<Ingrediente> ingredienteporreceta;


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String n) {
        this.nombre = n;
    }
    public String getTexto(){
        return texto;
    }
    public  void setTexto(String rec) {
        this.texto=rec;
    }

    public String toString() {
        return this.nombre;
    }
}