package com.belleza.inventario.dao;

import com.belleza.inventario.entities.Producto;
import java.util.List;

public interface ProductoDAO {
    List<Producto> obtenerTodos();
    Producto obtenerPorId(int id);
    void crear(Producto producto);
    void actualizar(Producto producto);
    void eliminar(int id);
}