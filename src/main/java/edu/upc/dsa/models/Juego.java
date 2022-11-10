package edu.upc.dsa.models;

import edu.upc.dsa.util.RandomUtils;

public class Juego {

    String id;
    String descripcion;
    String numeroNiveles;
    static int lastId;

    public Juego() {
        this.id = RandomUtils.getId();
    }

    public Juego(String id, String descripcion, String numeroNiveles) {
        this.id = id;
        this.descripcion = descripcion;
        this.numeroNiveles = numeroNiveles;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id=id;
    }


    public String getDescription() {
        return descripcion;
    }

    public void setDescription(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNumeroNiveles() {
        return numeroNiveles;
    }

    public void setNumeroNiveles(String numeroNiveles) {
        this.numeroNiveles = numeroNiveles;
    }

    @Override
    public String toString() {
        return "Juego [id="+id+", descripcion=" + descripcion + ", número de niveles=" + numeroNiveles +"]";
    }

}