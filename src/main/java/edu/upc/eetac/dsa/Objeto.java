package edu.upc.eetac.dsa;

public class Objeto {
    String nombre;
    double peso;

    public Objeto(String nombre, double peso){
        this.nombre = nombre;
        this.peso = peso;
    }
    public String toString() {return this.nombre;};
}
