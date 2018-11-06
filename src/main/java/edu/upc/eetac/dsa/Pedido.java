package edu.upc.eetac.dsa;

import java.util.List;

public class Pedido {
    //Attributes
    private List<LProducto> products;
    private Usuario usuario;

    public Pedido(List<LProducto> products) {
        this.products = products;
    }

    public List<LProducto> getProducts() {
        return products;
    }

    public void setUser(Usuario theUser) {
        this.usuario= theUser;
    }

    public Usuario getUser() {
        return this.usuario;

    }

    protected class LProducto{
        protected int q;
        protected String producto;
    }
}
