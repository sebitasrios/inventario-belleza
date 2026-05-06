package com.belleza.inventario.dao.jpa;

import com.belleza.inventario.entities.Proveedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repositorio JPA para Proveedor.
 * Complementa el acceso SQL puro de ProveedorDAOImpl.
 */
@Repository
public interface ProveedorRepository extends JpaRepository<Proveedor, Integer> {

    // Escenario: Buscar proveedor por email
    Optional<Proveedor> findByEmail(String email);

    // Escenario: Buscar proveedor por nombre (búsqueda parcial, case-insensitive)
    @Query("SELECT p FROM Proveedor p WHERE LOWER(p.nombre) LIKE LOWER(CONCAT('%', :nombre, '%'))")
    java.util.List<Proveedor> findByNombreContaining(@Param("nombre") String nombre);
}