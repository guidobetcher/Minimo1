package edu.upc.eetac.dsa;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public interface GameManager {

    List<Usuario> getAllUsuariosSortedAlfabetically();
    void addUser(Usuario u);
    void updateUsuario(Usuario user);
    String[] getUsuarioInfo(Usuario user);
    List<Objeto> getUsuarioObjetos(Usuario user);
    void addObject(Usuario user, Objeto objeto);
    int getNumUsuarios();
    int getNumObjetos(Usuario user);
    HashMap<String, Usuario> getUsuarios();
}
