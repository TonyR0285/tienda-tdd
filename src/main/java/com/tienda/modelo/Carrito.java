package com.tienda.modelo;

import java.util.ArrayList;
import java.util.List;

/**
 * Carrito de compras.
 * Kata TDD: agregar, eliminar productos y calcular total.
 */
public class Carrito {

    private final List<Producto> productos = new ArrayList<>();

    public Carrito() {}

    public Carrito(List<Producto> productos) {
        if (productos != null) this.productos.addAll(productos);
    }

    /** Agrega un producto al carrito. */
    public void agregar(Producto producto) {
        if (producto == null)
            throw new IllegalArgumentException("El producto no puede ser nulo.");
        productos.add(producto);
    }

    /** Elimina la primera ocurrencia del producto del carrito. */
    public boolean eliminar(Producto producto) {
        return productos.remove(producto);
    }

    /** Calcula el total sumando los precios de todos los productos. */
    public double calcularTotal() {
        return productos.stream().mapToDouble(Producto::getPrecio).sum();
    }

    /** Retorna la cantidad de productos en el carrito. */
    public int cantidadProductos() {
        return productos.size();
    }

    /** Verifica si el carrito está vacío. */
    public boolean estaVacio() {
        return productos.isEmpty();
    }

    public List<Producto> getProductos() {
        return new ArrayList<>(productos);
    }
}
