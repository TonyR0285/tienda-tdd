package com.tienda.modelo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Kata TDD: Carrito de Compras
 * Iteraciones: agregar → eliminar → total → validar cantidades
 */
@DisplayName("Kata TDD: Carrito de Compras")
class CarritoTest {

    private Carrito carrito;
    private Producto zapatos;
    private Producto pantalon;

    @BeforeEach
    void setUp() {
        carrito  = new Carrito();
        zapatos  = new Producto("Zapatos", 50.0, "Calzado");
        pantalon = new Producto("Pantalón", 40.0, "Ropa");
    }

    // ── Iteración 1: Agregar productos ───────────────────────────────────

    @Test
    @DisplayName("Carrito nuevo está vacío")
    void carritoNuevoEstaVacio() {
        assertTrue(carrito.estaVacio());
        assertEquals(0, carrito.cantidadProductos());
    }

    @Test
    @DisplayName("Agregar un producto incrementa la cantidad")
    void agregarProductoIncrementaCantidad() {
        carrito.agregar(zapatos);
        assertEquals(1, carrito.cantidadProductos());
    }

    @Test
    @DisplayName("Calcular total con un producto")
    void calcularTotalConUnProducto() {
        carrito.agregar(new Producto("Libro", 30.0));
        assertEquals(30.0, carrito.calcularTotal());
    }

    @Test
    @DisplayName("Calcular total con dos productos")
    void calcularTotalConDosProductos() {
        Carrito c = new Carrito(Arrays.asList(zapatos, pantalon));
        assertEquals(90.0, c.calcularTotal());
    }

    // ── Iteración 2: Eliminar productos ──────────────────────────────────

    @Test
    @DisplayName("Eliminar producto reduce el total")
    void eliminarProductoReduceTotal() {
        carrito.agregar(zapatos);
        carrito.agregar(pantalon);
        carrito.eliminar(zapatos);
        assertEquals(40.0, carrito.calcularTotal());
    }

    @Test
    @DisplayName("Eliminar único producto deja el carrito vacío")
    void eliminarUnicoProductoDejaCarritoVacio() {
        carrito.agregar(zapatos);
        carrito.eliminar(zapatos);
        assertTrue(carrito.estaVacio());
    }

    @Test
    @DisplayName("Eliminar producto inexistente retorna false")
    void eliminarProductoInexistenteRetornaFalse() {
        boolean resultado = carrito.eliminar(zapatos);
        assertFalse(resultado);
    }

    // ── Iteración 3: Validaciones ─────────────────────────────────────────

    @Test
    @DisplayName("Agregar producto nulo lanza excepción")
    void agregarProductoNuloLanzaExcepcion() {
        assertThrows(IllegalArgumentException.class,
                () -> carrito.agregar(null));
    }

    @Test
    @DisplayName("Total de carrito vacío es cero")
    void totalCarritoVacioEsCero() {
        assertEquals(0.0, carrito.calcularTotal());
    }
}
