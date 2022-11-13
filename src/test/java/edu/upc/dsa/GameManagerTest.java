package edu.upc.dsa;

import edu.upc.dsa.*;
import edu.upc.dsa.models.Partida;
import junit.framework.Assert;
import org.apache.log4j.Logger;
//Junit 4.13
import org.apache.log4j.PropertyConfigurator;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class GameManagerTest {

    private static Logger logger = Logger.getLogger(GameManagerTest.class);
    GameManager manager = null;

    @Before
    public void setUp(){

        //Configuring Log4j
        PropertyConfigurator.configure("src/main/resources/log4j.properties");
        logger.debug("Debug Test Message!");
        logger.info("Info Test Message!");
        logger.warn("Warning Test Message!");
        logger.error("Error Test Message!");

        //Instancing GameManager Implementation
        manager = GameManagerImpl.getInstance();

        manager.addUser("user1","Jofre");
        manager.addUser("user2","Maite");
        manager.createJuego("Busca Minas","Hay que encontrar las minas sin activarlas", 3);
        manager.iniciarPartida("Busca Minas","user1");

    }

    @After
    public void tearDown(){manager.clear();}

    @Test
    public void addUserTest(){
        manager.addUser("user3","Adam");
        manager.addUser("user4","Nil");
        Assert.assertEquals(4,manager.numUsers());
    }

    @Test
    public void pasarNivelTest(){
        manager.pasarNivel("user1",20,"10-12-2022");

    }

    @Test
    public void consultarNivelTest(){

        manager.consultarNivelActual("user1");
        Assert.assertEquals("1",manager.consultarNivelActual("user1"));
    }
}
