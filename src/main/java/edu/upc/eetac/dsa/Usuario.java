package edu.upc.eetac.dsa;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

public class Usuario {
    //Attributes
    String id;
    String name;
    String surname;


    @JsonIgnore
    @ApiModelProperty(hidden = true)
    List<Objeto> objetos;



    //In the constructor we only pass the username value, not the LinkedList getPedidos
    public Usuario(String name, String surname) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.surname = surname;
        this.objetos = new LinkedList<>();;
    }
    public  String getId() {return this.id;}

    public String getName() {
        return this.name;
    }

    public  String getSurname(){ return this.surname;}

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public List<Objeto> getObjetos() {
        return this.objetos;
    }

    public void setObjetos(List<Objeto> o) {
        this.objetos = o;
    }

    //We add addOrder function in Usuario, because we need to know which order has the user made to serveAnOrder
    public void addObjeto(Objeto o) {
        this.objetos.add(o);
    }

    public String toString() {return this.name;};
}
