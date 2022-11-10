package edu.upc.dsa;

import edu.upc.dsa.models.Juego;
import edu.upc.dsa.models.Partida;

import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;

public class GameManagerImpl implements GameManager {
    private static GameManager instance;
    LinkedList<Partida> partidaList = new LinkedList<>();
    LinkedList<Partida> listaUsuarios = new LinkedList<>();
    final static Logger logger = Logger.getLogger(GameManagerImpl.class);

    public static GameManager getInstance() {
        if (instance==null) instance = new GameManagerImpl();
        return instance;
    }

    public void addUser(Partida user) {
        boolean found = false;

        for ( Partida u: this.listaUsuarios) {
            if (u.getIdUser().equals(user.getIdUser())) {
                found = true;
                logger.info("El usuario existe");
            }
        }

        if (!found) {
            this.listaUsuarios.add(user);
            logger.info("Usuario añadido");
        }
    }
    public String consultarNivelActual(String id) {
        logger.info("Identificador usuario " +id );
        boolean found = false;
        String n;
        Partida nivel = null;
        for (Partida u: this.listaUsuarios) {
            if (u.getIdUser().equals(id)) {
                found = true;
                logger.info("Se ha encontrado al jugador");
            }
        }
        if (found){
            n = Integer.toString(nivel.getNivel());
            logger.info("Nivel: " + n);
            return n;
        }
        else {
            logger.info("No hay jugador");
        }

    }

    public Juego addTrack(String title, String singer) {
        return this.addTrack(new Juego(title, singer));
    }

    public Juego getTrack(String id) {
        logger.info("getTrack("+id+")");

        for (Juego t: this.tracks) {
            if (t.getId().equals(id)) {
                logger.info("getTrack("+id+"): "+t);

                return t;
            }
        }

        logger.warn("not found " + id);
        return null;
    }

    public List<Juego> findAll() {
        return this.tracks;
    }

    @Override
    public void deleteTrack(String id) {

        Juego t = this.getTrack(id);
        if (t==null) {
            logger.warn("not found " + t);
        }
        else logger.info(t+" deleted ");

        this.tracks.remove(t);

    }

    @Override
    public Juego updateTrack(Juego p) {
        Juego t = this.getTrack(p.getId());

        if (t!=null) {
            logger.info(p+" rebut!!!! ");

            t.setSinger(p.getSinger());
            t.setTitle(p.getTitle());

            logger.info(t+" updated ");
        }
        else {
            logger.warn("not found "+p);
        }

        return t;
    }
}