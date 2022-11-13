package edu.upc.dsa.models;
import edu.upc.dsa.util.RandomUtils;

import java.util.ArrayList;
import java.util.HashMap;

public class Partida {
    String idJuego;
    String idUser;
    String userName;
    int puntos = 50;
    int nivel = 1;
    ArrayList<Juego> partidas = new ArrayList<Juego>();
    HashMap<String,String> usuarioPuntos= new HashMap<>();

    //Constructor de la clase Partida
    public Partida(String idJuego, String idUser){
        this.idJuego = idJuego;
        this.idUser = idUser;
    }

    //Constructor vacío
    public Partida(){};

    // Setters & Getters
    public String getIdJuego(){return this.idJuego;}

    public void setIdJuego(String id){this.idJuego = id;}

    public String getIdUser(){return  this.idUser;}

    public void setIdUser(String id){this.idUser = id;}

    public String getUserName(){return this.userName;}

    public void setUserName(String name){this.userName=name;}

    public int getPuntos(){return this.puntos;}

    public void setPuntos(int puntos){this.puntos += puntos;}

    public int getNivel(){return this.nivel;}

    public void setNivel(int nivel){this.nivel += 1;}

    public ArrayList<Juego> getPartidas(){return partidas;}

    public HashMap<String,String> getUsuarioPuntos(){
        return usuarioPuntos;
    }

}
