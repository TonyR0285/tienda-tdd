package com.tienda.repositorio;

import com.tienda.modelo.Producto;

import java.util.List;
import java.util.Optional;

/**
 * Contrato del repositorio de productos.
 * Permite intercambiar implementaciones (en memoria, JPA, etc.)
 */
public interface ProductoRepository {
    Producto guardar(Producto producto);
    List<Producto> findAll();
    Optional<Producto> findById(Long id);
    List<Producto> findByCategoria(String categoria);
    boolean eliminar(Long id);
}
