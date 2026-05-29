package com.burgerparadise.model;

/**
 * ESTÁNDAR DE CODIFICACIÓN: Clase JavaBean que representa la entidad Producto.
 * Aplica encapsulamiento estricto y convenciones CamelCase.
 *
 * @author Thairuma
 */
public class Producto {

    // Atributos privados para cumplir con el estándar de encapsulación
    private int idProducto;
    private String nombre;
    private double precio;
    private int stock;

    // Constructor vacío requerido por los estándares de Frameworks
    public Producto() {
    }

    // Constructor sobrecargado para inicialización rápida
    public Producto(int idProducto, String nombre, double precio, int stock) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
    }

    // Métodos de acceso Getter y Setter (Estándar de Codificación)
    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
