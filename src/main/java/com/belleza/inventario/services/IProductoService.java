package com.belleza.inventario.services;

import com.belleza.inventario.entities.Producto;

import java.util.List;

/**
 * Interfaz de servicio para Producto.
 * Los controllers acceden a esta interfaz, no a la implementación concreta.
 * Esto desacopla la capa de presentación de la lógica de negocio.
 */
public interface IProductoService {

    // Operaciones CRUD (vía SQL - ProductoDAOImpl)
    List<Producto> obtenerTodos();
    Producto obtenerPorId(int id);
    void crear(Producto producto);
    void actualizar(Producto producto);
    void eliminar(int id);

    // Operaciones JPA - escenarios de negocio
    List<Producto> obtenerProductosBajoStock();
    List<Producto> obtenerPorCategoria(int idCategoria);
    List<Producto> obtenerPorProveedor(int idProveedor);
    List<Producto> obtenerPorPrecioMayorA(double precio);
}