package edu.upc.eetac.dsa;

import org.apache.log4j.Logger;

import java.util.*;

public class GameManagerImpl implements GameManager {
    //We call the log4j properties file
    final static Logger log = Logger.getLogger(GameManagerImpl.class.getName());

    //We implement the façade using a Singleton pattern
    private static GameManager instance;

    //Initialize the hashmap(key: string; value: Usuario) of users
    private HashMap<String, Usuario> usuarios;
    private List<Usuario> listaUsuarios;

    private GameManagerImpl(){
        usuarios = new HashMap<>();
    }

    public static GameManager getInstance(){
        if(instance==null) instance = (GameManager) new GameManagerImpl();
        return instance;
    }

    public void addUser(String name, String surname){
        usuarios.put(name, new Usuario(name, surname));
        listaUsuarios.add(new Usuario(name, surname));
    }


    public int size(){
        log.info("Tamaño: " + this.usuarios.size());
        return this.usuarios.size();
    }

    @Override
    public List<Usuario> getAllUsuariosSortedAlfabetically() {
        //We create a copy of the list
        log.info("List before changes: " + this.listaUsuarios);
        List<Usuario> ret = new ArrayList<>();
        ret.addAll(this.listaUsuarios);

        //We have to tell to the sort method, which criteria we want to apply
        Collections.sort(ret, new Comparator<Usuario>() {
            @Override
            public int compare(Usuario o1, Usuario o2) {
                //Ascending order
                return (int)(o1.getSurname().compareTo(o2.getSurname()));
            }
        });
        log.info("List after changes: " + ret);
        return ret;
    }


    @Override
    public void updateUsuario(Usuario user) {
        HashMap<String, Usuario> ret = new HashMap<>();
        for (Usuario u:
             this.listaUsuarios) {
            if(u.getId() == user.getId()){
                log.info("Antes de actualizar usuario: " + u);
                u = user;
                log.info("Despues de actualizar usuario: " + u);
            }
        }

    }

    @Override
    public String getUsuarioInfo(Usuario user) {
        String info = "Nombre: " + user.getName() + " Apellido: " + user.getSurname() + " id: " + user.getId();
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
        log.info("Se ha añadido el objeto" + objeto);
        user.addObjeto(objeto);
    }

    @Override
    public int getNumUsuarios(HashMap users) {
        log.info("Numero de usuarios: " + users.size());
        return users.size();
    }

    @Override
    public int getNumObjetos(Usuario user) {
        log.info("Numero de Objetos en el usuario: " + user + user.getObjetos().size());
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
