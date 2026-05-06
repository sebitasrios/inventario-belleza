package com.belleza.inventario.services;

import com.belleza.inventario.dao.CategoriaDAO;
import com.belleza.inventario.dao.jpa.CategoriaRepository;
import com.belleza.inventario.entities.Categoria;
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
class CategoriaServiceTest {

    @Mock
    private CategoriaDAO categoriaDAO;

    @Mock
    private CategoriaRepository categoriaRepository;

    @InjectMocks
    private CategoriaService categoriaService;

    private Categoria categoriaEjemplo;

    @BeforeEach
    void setUp() {
        categoriaEjemplo = new Categoria();
        categoriaEjemplo.setIdCategoria(1);
        categoriaEjemplo.setNombre("Cabello");
    }

    // ── CRUD (SQL) ────────────────────────────────────────────────

    @Test
    @DisplayName("obtenerTodos() debe retornar todas las categorías")
    void testObtenerTodos() {
        Categoria otra = new Categoria();
        otra.setIdCategoria(2);
        otra.setNombre("Maquillaje");

        when(categoriaDAO.obtenerTodos()).thenReturn(Arrays.asList(categoriaEjemplo, otra));

        List<Categoria> resultado = categoriaService.obtenerTodos();

        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        verify(categoriaDAO, times(1)).obtenerTodos();
    }

    @Test
    @DisplayName("obtenerPorId() debe retornar la categoría correcta")
    void testObtenerPorId() {
        when(categoriaDAO.obtenerPorId(1)).thenReturn(categoriaEjemplo);

        Categoria resultado = categoriaService.obtenerPorId(1);

        assertNotNull(resultado);
        assertEquals("Cabello", resultado.getNombre());
        verify(categoriaDAO, times(1)).obtenerPorId(1);
    }

    @Test
    @DisplayName("obtenerPorId() debe retornar null si la categoría no existe")
    void testObtenerPorIdInexistente() {
        when(categoriaDAO.obtenerPorId(999)).thenReturn(null);

        Categoria resultado = categoriaService.obtenerPorId(999);

        assertNull(resultado);
        verify(categoriaDAO, times(1)).obtenerPorId(999);
    }

    @Test
    @DisplayName("crear() debe delegar al DAO")
    void testCrear() {
        doNothing().when(categoriaDAO).crear(categoriaEjemplo);

        categoriaService.crear(categoriaEjemplo);

        verify(categoriaDAO, times(1)).crear(categoriaEjemplo);
    }

    @Test
    @DisplayName("actualizar() debe delegar al DAO")
    void testActualizar() {
        doNothing().when(categoriaDAO).actualizar(categoriaEjemplo);

        categoriaService.actualizar(categoriaEjemplo);

        verify(categoriaDAO, times(1)).actualizar(categoriaEjemplo);
    }

    @Test
    @DisplayName("eliminar() debe delegar al DAO")
    void testEliminar() {
        doNothing().when(categoriaDAO).eliminar(1);

        categoriaService.eliminar(1);

        verify(categoriaDAO, times(1)).eliminar(1);
    }

    // ── Escenarios JPA ────────────────────────────────────────────

    @Test
    @DisplayName("buscarPorNombreExacto() debe retornar la categoría si existe")
    void testBuscarPorNombreExactoEncontrada() {
        when(categoriaRepository.findByNombre("Cabello")).thenReturn(Optional.of(categoriaEjemplo));

        Optional<Categoria> resultado = categoriaService.buscarPorNombreExacto("Cabello");

        assertTrue(resultado.isPresent());
        assertEquals("Cabello", resultado.get().getNombre());
        verify(categoriaRepository, times(1)).findByNombre("Cabello");
    }

    @Test
    @DisplayName("buscarPorNombreExacto() debe retornar vacío si no existe")
    void testBuscarPorNombreExactoNoEncontrada() {
        when(categoriaRepository.findByNombre("Inexistente")).thenReturn(Optional.empty());

        Optional<Categoria> resultado = categoriaService.buscarPorNombreExacto("Inexistente");

        assertFalse(resultado.isPresent());
        verify(categoriaRepository, times(1)).findByNombre("Inexistente");
    }

    @Test
    @DisplayName("buscarPorNombreConteniendo() debe retornar categorías que coincidan")
    void testBuscarPorNombreConteniendo() {
        when(categoriaRepository.findByNombreContaining("cab"))
                .thenReturn(Collections.singletonList(categoriaEjemplo));

        List<Categoria> resultado = categoriaService.buscarPorNombreConteniendo("cab");

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        verify(categoriaRepository, times(1)).findByNombreContaining("cab");
    }

    @Test
    @DisplayName("buscarPorNombreConteniendo() debe retornar lista vacía si no hay coincidencias")
    void testBuscarPorNombreConteniendoSinResultados() {
        when(categoriaRepository.findByNombreContaining("xyz")).thenReturn(Collections.emptyList());

        List<Categoria> resultado = categoriaService.buscarPorNombreConteniendo("xyz");

        assertNotNull(resultado);
        assertTrue(resultado.isEmpty());
        verify(categoriaRepository, times(1)).findByNombreContaining("xyz");
    }
}