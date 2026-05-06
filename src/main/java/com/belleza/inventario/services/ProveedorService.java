package com.belleza.inventario.services;

import com.belleza.inventario.dao.ProveedorDAO;
import com.belleza.inventario.dao.jpa.ProveedorRepository;
import com.belleza.inventario.entities.Proveedor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Implementación de IProveedorService.
 * - CRUD: usa ProveedorDAO (acceso SQL puro con JDBC).
 * - Escenarios de negocio: usa ProveedorRepository (JPA/Spring Data).
 */
@Service
public class ProveedorService implements IProveedorService {

    @Autowired
    private ProveedorDAO proveedorDAO;               // SQL puro

    @Autowired
    private ProveedorRepository proveedorRepository; // JPA

    // ── CRUD vía SQL ─────────────────────────────────────────────────────────

    @Override
    public List<Proveedor> obtenerTodos() {
        return proveedorDAO.obtenerTodos();
    }

    @Override
    public Proveedor obtenerPorId(int id) {
        return proveedorDAO.obtenerPorId(id);
    }

    @Override
    public void crear(Proveedor proveedor) {
        proveedorDAO.crear(proveedor);
    }

    @Override
    public void actualizar(Proveedor proveedor) {
        proveedorDAO.actualizar(proveedor);
    }

    @Override
    public void eliminar(int id) {
        proveedorDAO.eliminar(id);
    }

    // ── Escenarios de negocio vía JPA ────────────────────────────────────────

    /**
     * Escenario: Verificar si ya existe un proveedor registrado con ese email.
     */
    @Override
    public Optional<Proveedor> buscarPorEmail(String email) {
        return proveedorRepository.findByEmail(email);
    }

    /**
     * Escenario: Búsqueda flexible de proveedores por nombre parcial.
     */
    @Override
    public List<Proveedor> buscarPorNombreConteniendo(String nombre) {
        return proveedorRepository.findByNombreContaining(nombre);
    }
}