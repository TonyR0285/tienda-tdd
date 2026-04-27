package com.tienda.modelo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Pruebas unitarias de la entidad Producto.
 * Ciclo TDD: Red → Green → Refactor
 */
@DisplayName("Pruebas unitarias: Producto")
class ProductoTest {

    // ── RED → GREEN ──────────────────────────────────────────────────────

    @Test
    @DisplayName("Crear producto establece nombre y precio correctamente")
    void crearProductoDebeEstablecerNombreYPrecio() {
        Producto producto = new Producto("Camiseta", 19.99);

        assertEquals("Camiseta", producto.getNombre());
        assertEquals(19.99, producto.getPrecio());
    }

    @Test
    @DisplayName("Crear producto con categoría la almacena correctamente")
    void crearProductoConCategoria() {
        Producto producto = new Producto("Laptop", 1500.0, "Electrónica");

        assertEquals("Laptop", producto.getNombre());
        assertEquals(1500.0, producto.getPrecio());
        assertEquals("Electrónica", producto.getCategoria());
    }

    // ── REFACTOR: validaciones de negocio ────────────────────────────────

    @Test
    @DisplayName("Crear producto con nombre vacío lanza excepción")
    void nombreVacioLanzaExcepcion() {
        assertThrows(IllegalArgumentException.class,
                () -> new Producto("", 10.0));
    }

    @Test
    @DisplayName("Crear producto con nombre nulo lanza excepción")
    void nombreNuloLanzaExcepcion() {
        assertThrows(IllegalArgumentException.class,
                () -> new Producto(null, 10.0));
    }

    @Test
    @DisplayName("Crear producto con precio negativo lanza excepción")
    void precioNegativoLanzaExcepcion() {
        assertThrows(IllegalArgumentException.class,
                () -> new Producto("Gorra", -5.0));
    }

    @Test
    @DisplayName("Precio cero es válido")
    void precioCeroEsValido() {
        Producto producto = new Producto("Muestra gratis", 0.0);
        assertEquals(0.0, producto.getPrecio());
    }
}
