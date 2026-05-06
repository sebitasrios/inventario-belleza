package com.belleza.inventario.services;

import com.belleza.inventario.dao.ProductoDAO;
import com.belleza.inventario.dao.jpa.ProductoRepository;
import com.belleza.inventario.entities.Producto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductoServiceTest {

    @Mock
    private ProductoDAO productoDAO;

    @Mock
    private ProductoRepository productoRepository;

    @InjectMocks
    private ProductoService productoService;

    private Producto productoEjemplo;

    @BeforeEach
    void setUp() {
        productoEjemplo = new Producto();
        productoEjemplo.setIdProducto(1);
        productoEjemplo.setNombre("Shampoo Hidratante");
        productoEjemplo.setDescripcion("Shampoo para cabello seco");
        productoEjemplo.setPrecio(25000.0);
        productoEjemplo.setStock(10);
        productoEjemplo.setStockMinimo(5);
        productoEjemplo.setIdCategoria(1);
        productoEjemplo.setIdProveedor(1);
    }

    // ── CRUD (SQL) ────────────────────────────────────────────────

    @Test
    @DisplayName("obtenerTodos() debe retornar la lista completa de productos")
    void testObtenerTodos() {
        when(productoDAO.obtenerTodos()).thenReturn(Arrays.asList(productoEjemplo, new Producto()));

        List<Producto> resultado = productoService.obtenerTodos();

        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        verify(productoDAO, times(1)).obtenerTodos();
    }

    @Test
    @DisplayName("obtenerPorId() debe retornar el producto correcto cuando existe")
    void testObtenerPorIdExistente() {
        when(productoDAO.obtenerPorId(1)).thenReturn(productoEjemplo);

        Producto resultado = productoService.obtenerPorId(1);

        assertNotNull(resultado);
        assertEquals("Shampoo Hidratante", resultado.getNombre());
        verify(productoDAO, times(1)).obtenerPorId(1);
    }

    @Test
    @DisplayName("obtenerPorId() debe retornar null cuando el producto no existe")
    void testObtenerPorIdInexistente() {
        when(productoDAO.obtenerPorId(999)).thenReturn(null);

        Producto resultado = productoService.obtenerPorId(999);

        assertNull(resultado);
        verify(productoDAO, times(1)).obtenerPorId(999);
    }

    @Test
    @DisplayName("crear() debe delegar correctamente al DAO")
    void testCrear() {
        doNothing().when(productoDAO).crear(productoEjemplo);

        productoService.crear(productoEjemplo);

        verify(productoDAO, times(1)).crear(productoEjemplo);
    }

    @Test
    @DisplayName("actualizar() debe delegar correctamente al DAO")
    void testActualizar() {
        doNothing().when(productoDAO).actualizar(productoEjemplo);

        productoService.actualizar(productoEjemplo);

        verify(productoDAO, times(1)).actualizar(productoEjemplo);
    }

    @Test
    @DisplayName("eliminar() debe delegar correctamente al DAO")
    void testEliminar() {
        doNothing().when(productoDAO).eliminar(1);

        productoService.eliminar(1);

        verify(productoDAO, times(1)).eliminar(1);
    }

    // ── Escenarios JPA ────────────────────────────────────────────

    @Test
    @DisplayName("obtenerProductosBajoStock() debe retornar productos con stock menor al mínimo")
    void testObtenerProductosBajoStock() {
        Producto bajo = new Producto();
        bajo.setNombre("Crema Facial");
        bajo.setStock(2);
        bajo.setStockMinimo(10);

        when(productoRepository.findProductosBajoStockMinimo())
                .thenReturn(Collections.singletonList(bajo));

        List<Producto> resultado = productoService.obtenerProductosBajoStock();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertTrue(resultado.get(0).getStock() < resultado.get(0).getStockMinimo());
        verify(productoRepository, times(1)).findProductosBajoStockMinimo();
    }

    @Test
    @DisplayName("obtenerProductosBajoStock() debe retornar lista vacía si no hay alertas")
    void testObtenerProductosBajoStockVacio() {
        when(productoRepository.findProductosBajoStockMinimo()).thenReturn(Collections.emptyList());

        List<Producto> resultado = productoService.obtenerProductosBajoStock();

        assertNotNull(resultado);
        assertTrue(resultado.isEmpty());
    }

    @Test
    @DisplayName("obtenerPorCategoria() debe retornar productos de la categoría indicada")
    void testObtenerPorCategoria() {
        when(productoRepository.findByIdCategoria(1))
                .thenReturn(Collections.singletonList(productoEjemplo));

        List<Producto> resultado = productoService.obtenerPorCategoria(1);

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals(1, resultado.get(0).getIdCategoria());
        verify(productoRepository, times(1)).findByIdCategoria(1);
    }

    @Test
    @DisplayName("obtenerPorCategoria() debe retornar lista vacía si no hay productos en esa categoría")
    void testObtenerPorCategoriaVacio() {
        when(productoRepository.findByIdCategoria(99)).thenReturn(Collections.emptyList());

        List<Producto> resultado = productoService.obtenerPorCategoria(99);

        assertNotNull(resultado);
        assertTrue(resultado.isEmpty());
    }

    @Test
    @DisplayName("obtenerPorProveedor() debe retornar productos del proveedor indicado")
    void testObtenerPorProveedor() {
        when(productoRepository.findByIdProveedor(1))
                .thenReturn(Collections.singletonList(productoEjemplo));

        List<Producto> resultado = productoService.obtenerPorProveedor(1);

        assertNotNull(resultado);
        assertEquals(1, resultado.get(0).getIdProveedor());
        verify(productoRepository, times(1)).findByIdProveedor(1);
    }

    @Test
    @DisplayName("obtenerPorPrecioMayorA() debe retornar productos más caros que el umbral")
    void testObtenerPorPrecioMayorA() {
        Producto caro = new Producto();
        caro.setNombre("Perfume Premium");
        caro.setPrecio(150000.0);

        when(productoRepository.findByPrecioGreaterThan(50000.0))
                .thenReturn(Collections.singletonList(caro));

        List<Producto> resultado = productoService.obtenerPorPrecioMayorA(50000.0);

        assertNotNull(resultado);
        assertTrue(resultado.get(0).getPrecio() > 50000.0);
        verify(productoRepository, times(1)).findByPrecioGreaterThan(50000.0);
    }
}