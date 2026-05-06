package com.belleza.inventario.services;

import com.belleza.inventario.dao.CategoriaDAO;
import com.belleza.inventario.dao.jpa.CategoriaRepository;
import com.belleza.inventario.entities.Categoria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Implementación de ICategoriaService.
 * - CRUD: usa CategoriaDAO (acceso SQL puro con JDBC).
 * - Escenarios de negocio: usa CategoriaRepository (JPA/Spring Data).
 */
@Service
public class CategoriaService implements ICategoriaService {

    @Autowired
    private CategoriaDAO categoriaDAO;               // SQL puro

    @Autowired
    private CategoriaRepository categoriaRepository; // JPA

    // ── CRUD vía SQL ─────────────────────────────────────────────────────────

    @Override
    public List<Categoria> obtenerTodos() {
        return categoriaDAO.obtenerTodos();
    }

    @Override
    public Categoria obtenerPorId(int id) {
        return categoriaDAO.obtenerPorId(id);
    }

    @Override
    public void crear(Categoria categoria) {
        categoriaDAO.crear(categoria);
    }

    @Override
    public void actualizar(Categoria categoria) {
        categoriaDAO.actualizar(categoria);
    }

    @Override
    public void eliminar(int id) {
        categoriaDAO.eliminar(id);
    }

    // ── Escenarios de negocio vía JPA ────────────────────────────────────────

    /**
     * Escenario: Verificar si ya existe una categoría con ese nombre exacto
     * antes de crear una nueva (evitar duplicados).
     */
    @Override
    public Optional<Categoria> buscarPorNombreExacto(String nombre) {
        return categoriaRepository.findByNombre(nombre);
    }

    /**
     * Escenario: Búsqueda flexible de categorías por nombre parcial.
     */
    @Override
    public List<Categoria> buscarPorNombreConteniendo(String nombre) {
        return categoriaRepository.findByNombreContaining(nombre);
    }
}