package com.tienda.repositorio;

import com.tienda.modelo.Producto;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

/**
 * Implementación JPA del repositorio de productos.
 * Usa EntityManager de Hibernate para persistencia real.
 */
public class ProductoRepositoryImpl implements ProductoRepository {

    private final EntityManager em;

    public ProductoRepositoryImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public Producto guardar(Producto producto) {
        em.getTransaction().begin();
        em.persist(producto);
        em.getTransaction().commit();
        return producto;
    }

    @Override
    public List<Producto> findAll() {
        return em.createQuery("FROM Producto", Producto.class).getResultList();
    }

    @Override
    public Optional<Producto> findById(Long id) {
        return Optional.ofNullable(em.find(Producto.class, id));
    }

    @Override
    public List<Producto> findByCategoria(String categoria) {
        return em.createQuery(
                "FROM Producto p WHERE p.categoria = :cat", Producto.class)
                .setParameter("cat", categoria)
                .getResultList();
    }

    @Override
    public boolean eliminar(Long id) {
        Producto p = em.find(Producto.class, id);
        if (p == null) return false;
        em.getTransaction().begin();
        em.remove(p);
        em.getTransaction().commit();
        return true;
    }
}
