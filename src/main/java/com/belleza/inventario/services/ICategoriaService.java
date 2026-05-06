package com.belleza.inventario.services;

import com.belleza.inventario.entities.Categoria;

import java.util.List;
import java.util.Optional;

/**
 * Interfaz de servicio para Categoria.
 * Los controllers acceden a esta interfaz, no a la implementación concreta.
 */
public interface ICategoriaService {

    // Operaciones CRUD (vía SQL - CategoriaDAOImpl)
    List<Categoria> obtenerTodos();
    Categoria obtenerPorId(int id);
    void crear(Categoria categoria);
    void actualizar(Categoria categoria);
    void eliminar(int id);

    // Operaciones JPA - escenarios de negocio
    Optional<Categoria> buscarPorNombreExacto(String nombre);
    List<Categoria> buscarPorNombreConteniendo(String nombre);
}