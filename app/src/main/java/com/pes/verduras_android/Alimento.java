package com.pes.verduras_android;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class Alimento{


    @SerializedName("nombre")
    @Expose
    public String nombre;
    @SerializedName("tipo")
    @Expose
    public String tipo;
    @SerializedName("vegetariano")
    @Expose
    public String  vegetariano;
    @SerializedName("ingrediente_por_receta")
    @Expose
    public List<Ingrediente> ingredienteporreceta;

    public Alimento(String nombre, String tipo, String vegeta) {
        this.nombre =nombre;
        this.tipo=tipo;
        this.vegetariano=vegeta;
    }
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String n) {
        this.nombre = n;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String toString(){
        return this.nombre;
    }


}