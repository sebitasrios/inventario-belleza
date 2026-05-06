package com.belleza.inventario.dao.jpa;

import com.belleza.inventario.entities.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repositorio JPA para Producto.
 * Complementa el acceso SQL puro de ProductoDAOImpl.
 */
@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {

    // Escenario 1: Buscar productos con stock por debajo del mínimo (alerta de reposición)
    @Query("SELECT p FROM Producto p WHERE p.stock < p.stockMinimo")
    List<Producto> findProductosBajoStockMinimo();

    // Escenario 2: Buscar productos por categoría
    @Query("SELECT p FROM Producto p WHERE p.idCategoria = :idCategoria")
    List<Producto> findByIdCategoria(@Param("idCategoria") int idCategoria);

    // Escenario 3: Buscar productos por proveedor
    @Query("SELECT p FROM Producto p WHERE p.idProveedor = :idProveedor")
    List<Producto> findByIdProveedor(@Param("idProveedor") int idProveedor);

    // Escenario 4: Buscar productos con precio mayor a un valor dado
    @Query("SELECT p FROM Producto p WHERE p.precio > :precio ORDER BY p.precio ASC")
    List<Producto> findByPrecioGreaterThan(@Param("precio") double precio);
}