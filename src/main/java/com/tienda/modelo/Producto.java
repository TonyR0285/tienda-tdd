package com.tienda.modelo;

import javax.persistence.*;

/**
 * Entidad Producto — mapeada con JPA/Hibernate.
 * Usada en pruebas unitarias (TDD) y de integración (ORM + H2).
 */
@Entity
@Table(name = "productos")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private double precio;

    @Column
    private String categoria;

    // Constructor vacío requerido por JPA
    public Producto() {}

    public Producto(String nombre, double precio) {
        validar(nombre, precio);
        this.nombre = nombre;
        this.precio = precio;
    }

    public Producto(String nombre, double precio, String categoria) {
        validar(nombre, precio);
        this.nombre = nombre;
        this.precio = precio;
        this.categoria = categoria;
    }

    private void validar(String nombre, double precio) {
        if (nombre == null || nombre.isBlank())
            throw new IllegalArgumentException("El nombre no puede estar vacío.");
        if (precio < 0)
            throw new IllegalArgumentException("El precio no puede ser negativo.");
    }

    // Getters
    public Long getId()           { return id; }
    public String getNombre()     { return nombre; }
    public double getPrecio()     { return precio; }
    public String getCategoria()  { return categoria; }

    // Setters
    public void setNombre(String nombre)       { this.nombre = nombre; }
    public void setPrecio(double precio)       { this.precio = precio; }
    public void setCategoria(String categoria) { this.categoria = categoria; }

    @Override
    public String toString() {
        return "Producto{nombre='" + nombre + "', precio=" + precio + ", categoria='" + categoria + "'}";
    }
}
