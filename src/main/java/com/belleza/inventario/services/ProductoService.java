package com.belleza.inventario.services;

import com.belleza.inventario.dao.ProductoDAO;
import com.belleza.inventario.dao.jpa.ProductoRepository;
import com.belleza.inventario.entities.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementación de IProductoService.
 * - CRUD: usa ProductoDAO (acceso SQL puro con JDBC).
 * - Escenarios de negocio: usa ProductoRepository (JPA/Spring Data).
 */
@Service
public class ProductoService implements IProductoService {

    @Autowired
    private ProductoDAO productoDAO;            // SQL puro

    @Autowired
    private ProductoRepository productoRepository; // JPA

    // ── CRUD vía SQL ─────────────────────────────────────────────────────────

    @Override
    public List<Producto> obtenerTodos() {
        return productoDAO.obtenerTodos();
    }

    @Override
    public Producto obtenerPorId(int id) {
        return productoDAO.obtenerPorId(id);
    }

    @Override
    public void crear(Producto producto) {
        productoDAO.crear(producto);
    }

    @Override
    public void actualizar(Producto producto) {
        productoDAO.actualizar(producto);
    }

    @Override
    public void eliminar(int id) {
        productoDAO.eliminar(id);
    }

    // ── Escenarios de negocio vía JPA ────────────────────────────────────────

    /**
     * Escenario 1: Alerta de reposición — productos cuyo stock actual
     * está por debajo del stock mínimo configurado.
     */
    @Override
    public List<Producto> obtenerProductosBajoStock() {
        return productoRepository.findProductosBajoStockMinimo();
    }

    /**
     * Escenario 2: Filtrar productos por categoría usando JPA.
     */
    @Override
    public List<Producto> obtenerPorCategoria(int idCategoria) {
        return productoRepository.findByIdCategoria(idCategoria);
    }

    /**
     * Escenario 3: Filtrar productos por proveedor usando JPA.
     */
    @Override
    public List<Producto> obtenerPorProveedor(int idProveedor) {
        return productoRepository.findByIdProveedor(idProveedor);
    }

    /**
     * Escenario 4: Listar productos con precio superior a un umbral dado.
     */
    @Override
    public List<Producto> obtenerPorPrecioMayorA(double precio) {
        return productoRepository.findByPrecioGreaterThan(precio);
    }
}