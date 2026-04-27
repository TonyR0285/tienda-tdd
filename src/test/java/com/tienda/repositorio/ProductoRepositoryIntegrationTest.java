package com.tienda.repositorio;

import com.tienda.modelo.Producto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Pruebas de integración: ORM con JPA + H2 en memoria.
 * Verifica que la capa de persistencia funciona correctamente.
 */
@DisplayName("Pruebas de integración: ProductoRepository (JPA + H2)")
class ProductoRepositoryIntegrationTest {

    private EntityManagerFactory emf;
    private EntityManager em;
    private ProductoRepositoryImpl repository;

    @BeforeEach
    void setUp() {
        emf = Persistence.createEntityManagerFactory("tiendaPU");
        em  = emf.createEntityManager();
        repository = new ProductoRepositoryImpl(em);
    }

    @AfterEach
    void tearDown() {
        if (em.isOpen()) em.close();
        if (emf.isOpen()) emf.close();
    }

    @Test
    @DisplayName("Guardar y recuperar un producto")
    void guardarYRecuperarProducto() {
        Producto producto = new Producto("Gorra", 12.5, "Accesorios");
        repository.guardar(producto);

        List<Producto> resultado = repository.findAll();

        assertEquals(1, resultado.size());
        assertEquals("Gorra", resultado.get(0).getNombre());
        assertEquals(12.5, resultado.get(0).getPrecio());
    }

    @Test
    @DisplayName("Buscar por ID retorna el producto guardado")
    void buscarPorIdRetornaProducto() {
        Producto producto = new Producto("Reloj", 200.0, "Accesorios");
        repository.guardar(producto);

        Optional<Producto> resultado = repository.findById(producto.getId());

        assertTrue(resultado.isPresent());
        assertEquals("Reloj", resultado.get().getNombre());
    }

    @Test
    @DisplayName("Buscar por categoría retorna solo los de esa categoría")
    void buscarPorCategoriaFiltrasCorrectamente() {
        repository.guardar(new Producto("Laptop", 1500.0, "Electrónica"));
        repository.guardar(new Producto("Mouse",  25.0,   "Electrónica"));
        repository.guardar(new Producto("Camisa", 30.0,   "Ropa"));

        List<Producto> electronicos = repository.findByCategoria("Electrónica");

        assertEquals(2, electronicos.size());
    }

    @Test
    @DisplayName("Eliminar producto lo remueve de la base de datos")
    void eliminarProductoLoRemueve() {
        Producto producto = new Producto("Funda", 8.0, "Accesorios");
        repository.guardar(producto);
        Long id = producto.getId();

        boolean eliminado = repository.eliminar(id);

        assertTrue(eliminado);
        assertFalse(repository.findById(id).isPresent());
    }

    @Test
    @DisplayName("Eliminar producto inexistente retorna false")
    void eliminarProductoInexistenteRetornaFalse() {
        boolean resultado = repository.eliminar(999L);
        assertFalse(resultado);
    }
}
