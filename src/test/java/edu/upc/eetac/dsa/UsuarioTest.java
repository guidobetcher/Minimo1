package edu.upc.eetac.dsa;

import org.apache.log4j.Logger;
import org.junit.*;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
public class UsuarioTest {
    //Logger
    final static Logger log = Logger.getLogger(UsuarioTest.class.getName());

    //We have to create and object of type ProductManager, for then use its methods
    static GameManager pm;

    static Objeto objeto1, objeto2, objeto3, objeto4, objeto5;

    static HashMap<String , Usuario> usuarios;

    static List<Objeto> lo;

    //Before the tests we have to add Users and some orders
    @BeforeClass
    public static void setUp(){
        //Because ProductManagerImpl implements ProductManager, we can create on top of pm, new ProductManagerImpl
        pm = GameManagerImpl.getInstance();
        lo = new ArrayList<>();


    }

    //When the test ends, it's properly to erase the contents added in @Before
    @AfterClass
    public static void tearDown(){
        pm = null;
    }


    @Test
    public void addUser(){
        pm.addUser(new Usuario("Pepe", "Garcia"));
        pm.addUser(new Usuario("Juan", "Fernandez"));
        pm.addUser(new Usuario("Carla", "Bruni"));
        pm.addUser(new Usuario("Paula", "Hernan"));
        int num = this.pm.getNumUsuarios();

        assertEquals(num, 4, 4);

    }

    @Test
    public void addObject(){
        Usuario joseph = new Usuario("Joseph", "Pamlona");
        String id = joseph.getId();
        this.pm.addObject(joseph, new Objeto("portatil",2));

        pm.addUser(joseph);
        int num = this.pm.getNumObjetos(this.pm.getUsuarios().get(id));

        assertEquals(num, 1, 1);

    }

    @Test
    public void getAllUsuariosSortedAlfabetically(){
        List<Usuario> ret = this.pm.getAllUsuariosSortedAlfabetically();

        assertEquals(ret.get(3).name, "Calamares", "Calamares");
        assertEquals(ret.get(1).name, "Cerveza", "Cerveza");
        assertEquals(ret.get(2).name, "Chocolate", "Chocolate");
        assertEquals(ret.get(0).name, "Manzana", "Manzana");
        assertEquals(ret.get(4).name, "Pastel", "Pastel");

    }
}
