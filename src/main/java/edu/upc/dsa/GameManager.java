package edu.upc.dsa;

import edu.upc.dsa.models.Partida;
import edu.upc.dsa.models.User;

import java.util.List;

public interface GameManager {

    //Creación de un juego
    void createJuego(String idJuego, String descripcion, int numeroNiveles);

    void addUser(String idUser, String name);

    //Iniciar partida en un juego por parte de un usuario
    //Retorna 0->OK, -1->usuario o juego no existen, -2->ya está en partida
    int iniciarPartida(String idJuego, String idUser);

    //Consultar el nivel actual
    //Retorna el nivel actual y la partida. Si el usuario no existe o no tiene una partida en curso se notifica
    String consultarNivelActual(String idUser);

    //Consultar puntuación actual
    //Retorna la puntuación actual de la partida o -1 si el usuario no existe o no está en partida
    int consultarPuntosActual(String idUser);

    //Pasar de nivel
    //Retorna un mensaje informando de lo que pasa y aumenta la puntuación
    void pasarNivel(String id, int puntos, String fecha);

    //Finalizar partida
    void finalizarPartida(String idUsuario);

    //Lista de usuarios que han participado en un juego ordenados por puntos descendente
    List<String> listaUsuariosPorPuntos(String idJuego);

    //Lista de las partidas en las que ha participado un usuario
    List<Partida> cosultarPartidasUsuario(String idUser);

    //Actividad de un usuario en un juego
    String actividadUsuario(String idUser, String idJuego);

    int numUsers();


    void clear();





}
