package com.pes.verduras_android;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class listaAl {

    @Expose
    @SerializedName("lista")
    public List<Alimento> lista;

    public listaAl(List<Alimento> lista) {
        this.lista = lista;
    }

    public List<Alimento> getLista() {
        return lista;
    }

    public void setLista(List<Alimento> lista) {
        this.lista = lista;
    }
}
