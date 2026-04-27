package com.tienda.service;

import com.tienda.modelo.Producto;
import com.tienda.repositorio.ProductoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Pruebas unitarias del servicio ProductoService usando Mockito.
 * El repositorio es mockeado para aislar la lógica de negocio.
 */
@ExtendWith(MockitoExtension.class)
@DisplayName("Pruebas unitarias: ProductoService (con Mockito)")
class ProductoServiceTest {

    @Mock
    private ProductoRepository repository;

    private ProductoService service;

    @BeforeEach
    void setUp() {
        service = new ProductoService(repository);
    }

    @Test
    @DisplayName("Guardar producto invoca el repositorio y retorna el producto")
    void guardarProductoInvocaRepository() {
        Producto producto = new Producto("Laptop", 1500.0, "Electrónica");
        when(repository.guardar(producto)).thenReturn(producto);

        Producto resultado = service.guardar(producto);

        assertNotNull(resultado);
        assertEquals("Laptop", resultado.getNombre());
        verify(repository, times(1)).guardar(producto);
    }

    @Test
    @DisplayName("Guardar producto nulo lanza excepción sin llamar al repositorio")
    void guardarProductoNuloLanzaExcepcion() {
        assertThrows(IllegalArgumentException.class, () -> service.guardar(null));
        verifyNoInteractions(repository);
    }

    @Test
    @DisplayName("ObtenerTodos retorna lista del repositorio")
    void obtenerTodosRetornaLista() {
        List<Producto> lista = Arrays.asList(
                new Producto("Laptop", 1500.0),
                new Producto("Mouse", 25.0)
        );
        when(repository.findAll()).thenReturn(lista);

        List<Producto> resultado = service.obtenerTodos();

        assertEquals(2, resultado.size());
        verify(repository).findAll();
    }

    @Test
    @DisplayName("BuscarPorId retorna el producto si existe")
    void buscarPorIdRetornaProducto() {
        Producto producto = new Producto("Teclado", 60.0);
        when(repository.findById(1L)).thenReturn(Optional.of(producto));

        Optional<Producto> resultado = service.buscarPorId(1L);

        assertTrue(resultado.isPresent());
        assertEquals("Teclado", resultado.get().getNombre());
    }

    @Test
    @DisplayName("BuscarPorId retorna vacío si no existe")
    void buscarPorIdRetornaVacio() {
        when(repository.findById(99L)).thenReturn(Optional.empty());

        Optional<Producto> resultado = service.buscarPorId(99L);

        assertFalse(resultado.isPresent());
    }

    @Test
    @DisplayName("Eliminar retorna true si el producto existía")
    void eliminarRetornaTrueSiExistia() {
        when(repository.eliminar(1L)).thenReturn(true);
        assertTrue(service.eliminar(1L));
    }
}
