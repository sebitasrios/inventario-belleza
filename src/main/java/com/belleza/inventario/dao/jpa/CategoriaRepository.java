package com.belleza.inventario.dao.jpa;

import com.belleza.inventario.entities.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repositorio JPA para Categoria.
 * Complementa el acceso SQL puro de CategoriaDAOImpl.
 */
@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {

    // Escenario: Buscar categoría por nombre (exacto)
    Optional<Categoria> findByNombre(String nombre);

    // Escenario: Buscar categorías cuyo nombre contenga una cadena (búsqueda parcial)
    @Query("SELECT c FROM Categoria c WHERE LOWER(c.nombre) LIKE LOWER(CONCAT('%', :nombre, '%'))")
    List<Categoria> findByNombreContaining(@Param("nombre") String nombre);
}