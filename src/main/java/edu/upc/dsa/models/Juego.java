package edu.upc.dsa.models;

import java.util.ArrayList;

public class Juego {

    String id; //Nombre del juego
    String descripcion; //Descripción del juego
    int numeroNiveles; //Número de niveles totales en el juego

    ArrayList<Partida> usuariosPartida = new ArrayList<Partida>();


    //Constructor público de la clase Juego.
    public Juego(String id, String descripcion, int numeroNiveles) {
        this.id = id;
        this.descripcion = descripcion;
        this.numeroNiveles = numeroNiveles;
    }

    //Constructor vacío
    public Juego(){}

    // Setters & Getters
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

    public int getNumeroNiveles() {
        return numeroNiveles;
    }

    public void setNumeroNiveles(int numeroNiveles) {
        this.numeroNiveles = numeroNiveles;
    }

    @Override
    public String toString() {
        return "Juego [id="+id+", descripcion=" + descripcion + ", número de niveles=" + numeroNiveles +"]";
    }

}