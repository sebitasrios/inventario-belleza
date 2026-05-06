package com.belleza.inventario.services;

import com.belleza.inventario.dao.ProveedorDAO;
import com.belleza.inventario.dao.jpa.ProveedorRepository;
import com.belleza.inventario.entities.Proveedor;
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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProveedorServiceTest {

    @Mock
    private ProveedorDAO proveedorDAO;

    @Mock
    private ProveedorRepository proveedorRepository;

    @InjectMocks
    private ProveedorService proveedorService;

    private Proveedor proveedorEjemplo;

    @BeforeEach
    void setUp() {
        proveedorEjemplo = new Proveedor();
        proveedorEjemplo.setIdProveedor(1);
        proveedorEjemplo.setNombre("Distribuidora Belleza S.A.S");
        proveedorEjemplo.setTelefono("3001234567");
        proveedorEjemplo.setEmail("contacto@belleza.com");
    }

    // ── CRUD (SQL) ────────────────────────────────────────────────

    @Test
    @DisplayName("obtenerTodos() debe retornar todos los proveedores")
    void testObtenerTodos() {
        Proveedor otro = new Proveedor();
        otro.setIdProveedor(2);
        otro.setNombre("Cosméticos del Valle");
        otro.setEmail("info@valle.com");

        when(proveedorDAO.obtenerTodos()).thenReturn(Arrays.asList(proveedorEjemplo, otro));

        List<Proveedor> resultado = proveedorService.obtenerTodos();

        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        verify(proveedorDAO, times(1)).obtenerTodos();
    }

    @Test
    @DisplayName("obtenerPorId() debe retornar el proveedor correcto")
    void testObtenerPorId() {
        when(proveedorDAO.obtenerPorId(1)).thenReturn(proveedorEjemplo);

        Proveedor resultado = proveedorService.obtenerPorId(1);

        assertNotNull(resultado);
        assertEquals("Distribuidora Belleza S.A.S", resultado.getNombre());
        assertEquals("contacto@belleza.com", resultado.getEmail());
        verify(proveedorDAO, times(1)).obtenerPorId(1);
    }

    @Test
    @DisplayName("obtenerPorId() debe retornar null si el proveedor no existe")
    void testObtenerPorIdInexistente() {
        when(proveedorDAO.obtenerPorId(999)).thenReturn(null);

        Proveedor resultado = proveedorService.obtenerPorId(999);

        assertNull(resultado);
        verify(proveedorDAO, times(1)).obtenerPorId(999);
    }

    @Test
    @DisplayName("crear() debe delegar al DAO")
    void testCrear() {
        doNothing().when(proveedorDAO).crear(proveedorEjemplo);

        proveedorService.crear(proveedorEjemplo);

        verify(proveedorDAO, times(1)).crear(proveedorEjemplo);
    }

    @Test
    @DisplayName("actualizar() debe delegar al DAO")
    void testActualizar() {
        doNothing().when(proveedorDAO).actualizar(proveedorEjemplo);

        proveedorService.actualizar(proveedorEjemplo);

        verify(proveedorDAO, times(1)).actualizar(proveedorEjemplo);
    }

    @Test
    @DisplayName("eliminar() debe delegar al DAO")
    void testEliminar() {
        doNothing().when(proveedorDAO).eliminar(1);

        proveedorService.eliminar(1);

        verify(proveedorDAO, times(1)).eliminar(1);
    }

    // ── Escenarios JPA ────────────────────────────────────────────

    @Test
    @DisplayName("buscarPorEmail() debe retornar el proveedor si el email existe")
    void testBuscarPorEmailEncontrado() {
        when(proveedorRepository.findByEmail("contacto@belleza.com"))
                .thenReturn(Optional.of(proveedorEjemplo));

        Optional<Proveedor> resultado = proveedorService.buscarPorEmail("contacto@belleza.com");

        assertTrue(resultado.isPresent());
        assertEquals("Distribuidora Belleza S.A.S", resultado.get().getNombre());
        verify(proveedorRepository, times(1)).findByEmail("contacto@belleza.com");
    }

    @Test
    @DisplayName("buscarPorEmail() debe retornar vacío si el email no existe")
    void testBuscarPorEmailNoEncontrado() {
        when(proveedorRepository.findByEmail("noexiste@test.com")).thenReturn(Optional.empty());

        Optional<Proveedor> resultado = proveedorService.buscarPorEmail("noexiste@test.com");

        assertFalse(resultado.isPresent());
        verify(proveedorRepository, times(1)).findByEmail("noexiste@test.com");
    }

    @Test
    @DisplayName("buscarPorNombreConteniendo() debe retornar proveedores que coincidan")
    void testBuscarPorNombreConteniendo() {
        when(proveedorRepository.findByNombreContaining("Belleza"))
                .thenReturn(Collections.singletonList(proveedorEjemplo));

        List<Proveedor> resultado = proveedorService.buscarPorNombreConteniendo("Belleza");

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertTrue(resultado.get(0).getNombre().contains("Belleza"));
        verify(proveedorRepository, times(1)).findByNombreContaining("Belleza");
    }

    @Test
    @DisplayName("buscarPorNombreConteniendo() debe retornar lista vacía si no hay coincidencias")
    void testBuscarPorNombreConteniendoSinResultados() {
        when(proveedorRepository.findByNombreContaining("XYZ")).thenReturn(Collections.emptyList());

        List<Proveedor> resultado = proveedorService.buscarPorNombreConteniendo("XYZ");

        assertNotNull(resultado);
        assertTrue(resultado.isEmpty());
        verify(proveedorRepository, times(1)).findByNombreContaining("XYZ");
    }
}