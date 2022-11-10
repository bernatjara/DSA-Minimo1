package edu.upc.dsa;

import edu.upc.dsa.models.Partida;

import java.util.List;

public interface GameManager {


    public Partida cosultarNivelActual(String id);
    public Partida consultarPuntosActual(String id);
    public Partida pasarNivel(String id, Integer puntos, String fecha);
    public List<Partida> listaUsuariosPorPuntos();
    public Partida finalizarPartida(String idUsuario, String idPartida);
    public List<Partida> cosultarPartidasUsuario();
    public List<Partida> actividadUsuario();
    public void addUser(Partida user);


}
