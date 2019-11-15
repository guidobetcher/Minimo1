package edu.upc.eetac.dsa;

import org.apache.log4j.Logger;

import javax.swing.*;
import java.util.*;

public class GameManagerImpl implements GameManager {
    //We call the log4j properties file
    final static Logger log = Logger.getLogger(GameManagerImpl.class.getName());

    //We implement the façade using a Singleton pattern
    private static GameManager instance;

    //Initialize the hashmap(key: string; value: Usuario) of users
    private HashMap<String, Usuario> usuarios;

    private GameManagerImpl(){
        usuarios = new HashMap<>();
    }

    public static GameManager getInstance(){
        if(instance==null) instance = (GameManager) new GameManagerImpl();
        return instance;
    }


    public void addUser(Usuario u){
        usuarios.put(u.getId(), u);
        log.info("Usuario añadido: " + this.usuarios.get(u.getId()));
    }

    @Override
    public List<Usuario> getAllUsuariosSortedAlfabetically() {
        //We create a copy of the list

        List<Usuario> res = new LinkedList<>();;
        ArrayList<Usuario> jugadores_arr = new ArrayList<Usuario>(this.usuarios.values());

        for (int i = 0; i < jugadores_arr.size(); i++) {
            res.add(jugadores_arr.get(i));
        }
        log.info("List before changes: " + res);
        //We have to tell to the sort method, which criteria we want to apply
        Collections.sort(res, new Comparator<Usuario>() {
            @Override
            public int compare(Usuario o1, Usuario o2) {
                //Ascending order
                return (int)(o1.getSurname().compareTo(o2.getSurname()));
            }
        });
        log.info("List after changes: " + res);
        return res;
    }


    @Override
    public int updateUsuario(Usuario user) {
        log.info("Antes de actualizar usuario: " + this.usuarios.get(user.getId()));
        Usuario result = this.usuarios.replace(user.getId(), user);
        int res;
        if (result != null) {
            log.info("Usuario actualizado: ");
            res = 1;
        } else {
            log.info("Specified user not found");
            res = -1;
        }
        log.info("Despues de actualizar usuario: " + this.usuarios.get(user.getId()));
        return res;
    }

    @Override
    public String[] getUsuarioInfo(Usuario user) {
        String[] info = new String[3];
        info[0] = "Nombre: " + user.getName();
        info[1] = " Apellido: " + user.getSurname();
        info[2] = " id: " + user.getId();
        log.info("Información obtenida: " + info);
        return info;
    }

    @Override
    public List<Objeto> getUsuarioObjetos(Usuario user) {
        List<Objeto> res = null;
        res = user.getObjetos();
        log.info("Esta es la lista obtenida" + res);
        return res;
    }

    @Override
    public void addObject(Usuario user, Objeto objeto) {
        user.addObjeto(objeto);
        log.info("Se ha añadido el objeto: " + objeto);
    }

    @Override
    public int getNumUsuarios() {
        log.info("Numero de usuarios: " + this.usuarios.size());
        return this.usuarios.size();
    }

    @Override
    public int getNumObjetos(Usuario user) {
        log.info("Numero de Objetos en el usuario " + user.getName() + user.getSurname() + ": " + user.getObjetos().size());
        return user.getObjetos().size();
    }

    @Override
    public HashMap<String, Usuario> getUsuarios() {
        HashMap<String, Usuario> res = this.usuarios;
        return res;
    }

    public HashMap<String, Usuario> allUsers(){
        HashMap<String, Usuario> ret = new HashMap<>();
        ret.putAll(this.usuarios);

        return ret;
    }

}
