package edu.upc.dsa.models;
import java.util.LinkedList;

public class Partida {
    String idJuego;
    String idUser;
    Integer puntos = 50;
    Integer nivel;
    //LinkedList<>  = null;


    public Partida(String idJuego, String idUser){

    }

    public String getIdJuego(){return this.idJuego;}

    public void setIdJuego(String id){this.idJuego = id;}

    public String getIdUser(){return  this.idUser;}

    public void setIdUser(String id){this.idUser = id;}

    public Integer getPuntos(){return this.puntos;}

    public void setPuntos(Integer puntos){this.puntos += puntos;}

    public Integer getNivel(){return this.nivel;}

    public void setNivel(){this.nivel += 1;}
}
