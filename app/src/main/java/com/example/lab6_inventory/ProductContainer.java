package com.example.lab6_inventory;

public class ProductContainer {

    private String name;
//    private Bitmap image;
    private String descripcion;
    private double precio;
    private int cantidad;

    public ProductContainer (String name, String descripcion, double precio, int cantidad){
        this.name = name;
        this.descripcion = descripcion;
        this.precio = precio;
        this.cantidad = cantidad;
    }

    public ProductContainer(){

    }

    public String getNombre() {
        return name;
    }

//    public Bitmap getImage() {
//        return image;
//    }

    public String getDescripcion() {
        return descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setNombre(String name) {
        this.name = name;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public String toString(){
        return "{ Producto: Nombre "+ name +" Descripcion "
                + descripcion + " Cantidad "+cantidad+" precioTV "+precio+" }";
    }
}
