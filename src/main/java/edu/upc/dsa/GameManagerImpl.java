package edu.upc.dsa;

import edu.upc.dsa.models.*;

import java.util.*;
import java.util.Map.Entry;

import org.apache.log4j.Logger;

public class GameManagerImpl implements GameManager {
    private static GameManager instance;
    LinkedList<String> listaJuegos = new LinkedList<String>();
    LinkedList<String> listaUsuarios = new LinkedList<String>();
    HashMap<String, String> hm =new HashMap<String,String>();
    HashMap<String, String> usuarioPunto = new HashMap<>();

    final static Logger logger = Logger.getLogger(GameManagerImpl.class);

    public static GameManager getInstance() {
        if (instance==null) instance = new GameManagerImpl();
        return instance;
    }

    @Override
    public void createJuego(String idJuego, String descripcion, int numeroNiveles) {
        logger.info("Número actual de juegos es: "+ listaJuegos.size());
        if (listaJuegos.contains(idJuego)){
            logger.error("Ya existe un juego con esta id: " + idJuego);
        }
        else {
            Juego juegoNuevo = new Juego(idJuego, descripcion, numeroNiveles);
            listaJuegos.add(idJuego);
            logger.info("Ahora existe el juego con id: " + idJuego+ "descripción: "+descripcion+" nº niveles: "+numeroNiveles);
        }

    }

    @Override
    public void addUser(String idUser, String name) {
        User user;
        logger.info("Miramos si el usuario con id: "+idUser+" existe");
        if(listaUsuarios.contains(idUser)) {
            logger.error("Este usuario ya existe");
        }
        else {
            logger.info("El usuario no existe, lo podemos añadir con la id: "+idUser);
            user = new User(idUser,name);
            listaUsuarios.addLast(idUser);
        }
    }

    @Override
    public int iniciarPartida(String idJuego, String idUser) {
        int o;
        int puntos = 50;
        String resultado = idJuego+"/"+ puntos;
        boolean encontrado = false;
        if(listaJuegos.contains(idJuego) && listaUsuarios.contains(idUser)){
            logger.info("Existe un juego con este identificador: " + idJuego +" jugado por el jugador con id: " + idUser);
            if(hm.containsValue(idJuego) && idJuego.equals(hm.get((idUser)))){
                logger.error("El usuario ya esta en partida");
                o=-2;
            }
            else {
                o=0;
                encontrado = true;
            }
        }
        else {
            logger.error("No exite el juego o el usuario");
            o = -1;
        }
        if (encontrado){
            logger.info("Se ha iniciado partida correctamente");
            hm.put(idUser,idJuego);
            usuarioPunto.put(idUser, resultado);
        }
        return o;
    }

    public String consultarNivelActual(String idUser){
        String idJuego = null;
        int nivel;
        Partida partida = new Partida();
        String resultado = null;
        boolean encontrado = false;
        if(listaUsuarios.contains(idUser) && hm.containsKey(idUser)){
            logger.info("Existe un usuario con id: " + idUser + " que está en partida");
            for(Entry<String,String> entry : hm.entrySet()){
                if(entry.getValue().equals(idUser)){
                    idJuego= entry.getValue();
                    encontrado = true;
                    break;
                }
            }
            if(encontrado){
                nivel = partida.getNivel();
                resultado = "Nivel: " + nivel + "id partida: "+ idJuego;
            }
        }
        else logger.error("No existe un usuario con id: " + idUser + " que esté en partida");
        logger.info(resultado);
        return resultado;
    }

    @Override
    public int consultarPuntosActual(String idUser) {
        int resultado;
        Partida puntos = new Partida();
        if(listaUsuarios.contains(idUser) && hm.containsKey(idUser)) {
            logger.info("El usuario con id: " + idUser + "está en partida");
            resultado = puntos.getPuntos();
        }
        else{
            logger.error("El usuario no existe o no está en partida");
            resultado = -1;
        }
        return resultado;
    }

    @Override
    public void pasarNivel(String id, int puntos, String fecha) {
        Partida nivelActual = new Partida();
        Juego totalNiveles = new Juego();
        int puntos2;
        int puntos3;
        int puntosActuales = nivelActual.getPuntos();
        int nivel = nivelActual.getNivel();
        int total = totalNiveles.getNumeroNiveles();
        String resultado;

        if (listaUsuarios.contains(id) && hm.containsKey(id)){
            logger.info("El usuario con id: " + id + "está en partida");
            if(nivel == total){
                logger.info("Este era el ultimo nivel");
                puntos2 = puntos + 100;
                nivelActual.setPuntos(puntos2);
                finalizarPartida(id);

                puntos3= puntosActuales + puntos2;
                resultado=nivelActual.getIdJuego()+"/"+puntos3;
                usuarioPunto.put(id,resultado);

            }
            else{
                logger.info("Aún quedan más niveles");
                puntos2 = puntosActuales + puntos;
                logger.info("Nivel: "+ (nivel+1) + " Puntos: " + puntos2 );
                nivelActual.setPuntos(puntos);
                nivelActual.setNivel(nivel);
                resultado=nivelActual.getIdJuego()+"/"+puntos2;
                usuarioPunto.put(id,resultado);
            }


        }
        else{
            logger.error("No existe el usuario con id: " + id + " o no se encuentra en partida");
        }

    }

    @Override
    public void finalizarPartida(String idUsuario) {
        if (listaUsuarios.contains(idUsuario) && hm.containsKey(idUsuario)){
            logger.info("Vamos a finalizar la partida del usuario con id: "+idUsuario);
            for(Entry<String,String> entry : hm.entrySet()) {
                if (entry.getKey().equals(idUsuario)) {
                    hm.remove(idUsuario);
                    logger.info("Se ha eliminado al usuario");
                    break;
                }
            }
        }
        else{
            logger.error("No existe el usuario o no se encuentra en partida");
        }
    }

    @Override
    public List<String> listaUsuariosPorPuntos(String idJuego) {
        int i=0;
        String[] parts;
        HashMap<String,Integer> mapa2 = new HashMap<>();
        logger.info("Queremos la lista de los usuarios que han participado en un juego en orden descendente de puntos");
        while( usuarioPunto!=null && i<=usuarioPunto.size()){
            for(Entry<String,String> entry: usuarioPunto.entrySet()){
                parts = entry.getValue().split("/");
                if(parts[0].equals(idJuego)){
                    mapa2.put(entry.getKey(), Integer.parseInt(parts[1]));
                }
            }
            i++;
        }
        LinkedList<String> llista = null;
        if(usuarioPunto!=null) {
            Map<String, Integer> hm1 = sortByValue(mapa2);
            llista=new LinkedList<String>(hm1.keySet());
        }


        return llista;
    }

    @Override
    public List<Partida> cosultarPartidasUsuario(String idUser) {
        return null;
    }

    @Override
    public String actividadUsuario(String idUser, String idJuego) {
        return null;
    }

    // function to sort hashmap by values
    public static HashMap<String, Integer> sortByValue(HashMap<String, Integer> shm)
    {
        // Create a list from elements of HashMap
        List<Map.Entry<String, Integer> > list =
                new LinkedList<Map.Entry<String, Integer> >(shm.entrySet());

        // Sort the list
        Collections.sort(list, new Comparator<Map.Entry<String, Integer> >() {
            public int compare(Map.Entry<String, Integer> o1,
                               Map.Entry<String, Integer> o2)
            {
                return (o1.getValue()).compareTo(o2.getValue());
            }
        });

        // put data from sorted list to hashmap
        HashMap<String, Integer> temp = new LinkedHashMap<String, Integer>();
        for (Map.Entry<String, Integer> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
        }
        return temp;
    }

    @Override
    public int numUsers() {
        return this.listaUsuarios.size();
    }

    @Override
    public void clear() {
        this.listaUsuarios.clear();
        this.listaJuegos.clear();
        this.hm.clear();
        this.usuarioPunto.clear();
    }
}