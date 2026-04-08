package com.belleza.inventario.dao;

import com.belleza.inventario.entities.Proveedor;
import java.util.List;

public interface ProveedorDAO {
    List<Proveedor> obtenerTodos();
    Proveedor obtenerPorId(int id);
    void crear(Proveedor proveedor);
    void actualizar(Proveedor proveedor);
    void eliminar(int id);
}