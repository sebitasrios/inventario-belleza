package com.belleza.inventario.services;

import com.belleza.inventario.entities.Proveedor;

import java.util.List;
import java.util.Optional;

/**
 * Interfaz de servicio para Proveedor.
 * Los controllers acceden a esta interfaz, no a la implementación concreta.
 */
public interface IProveedorService {

    // Operaciones CRUD (vía SQL - ProveedorDAOImpl)
    List<Proveedor> obtenerTodos();
    Proveedor obtenerPorId(int id);
    void crear(Proveedor proveedor);
    void actualizar(Proveedor proveedor);
    void eliminar(int id);

    // Operaciones JPA - escenarios de negocio
    Optional<Proveedor> buscarPorEmail(String email);
    List<Proveedor> buscarPorNombreConteniendo(String nombre);
}