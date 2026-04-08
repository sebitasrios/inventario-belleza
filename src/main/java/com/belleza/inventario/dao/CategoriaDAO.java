package com.belleza.inventario.dao;

import com.belleza.inventario.entities.Categoria;
import java.util.List;

public interface CategoriaDAO {
    List<Categoria> obtenerTodos();
    Categoria obtenerPorId(int id);
    void crear(Categoria categoria);
    void actualizar(Categoria categoria);
    void eliminar(int id);
}