package com.tienda.service;

import com.tienda.modelo.Producto;
import com.tienda.repositorio.ProductoRepository;

import java.util.List;
import java.util.Optional;

/**
 * Servicio de negocio para la gestión de productos.
 * Integrado con ProductoRepository para persistencia.
 */
public class ProductoService {

    private final ProductoRepository repository;

    public ProductoService(ProductoRepository repository) {
        this.repository = repository;
    }

    /** Guarda un nuevo producto en el sistema. */
    public Producto guardar(Producto producto) {
        if (producto == null)
            throw new IllegalArgumentException("El producto no puede ser nulo.");
        return repository.guardar(producto);
    }

    /** Retorna todos los productos disponibles. */
    public List<Producto> obtenerTodos() {
        return repository.findAll();
    }

    /** Busca un producto por su ID. */
    public Optional<Producto> buscarPorId(Long id) {
        return repository.findById(id);
    }

    /** Busca productos por categoría. */
    public List<Producto> buscarPorCategoria(String categoria) {
        return repository.findByCategoria(categoria);
    }

    /** Elimina un producto por ID. Retorna true si existía. */
    public boolean eliminar(Long id) {
        return repository.eliminar(id);
    }
}
