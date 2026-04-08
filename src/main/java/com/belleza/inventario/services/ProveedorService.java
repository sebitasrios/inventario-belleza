package com.belleza.inventario.services;

import com.belleza.inventario.dao.ProveedorDAO;
import com.belleza.inventario.entities.Proveedor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProveedorService {
    @Autowired
    private ProveedorDAO proveedorDAO;

    public List<Proveedor> obtenerTodos() { return proveedorDAO.obtenerTodos(); }
    public Proveedor obtenerPorId(int id) { return proveedorDAO.obtenerPorId(id); }
    public void crear(Proveedor proveedor) { proveedorDAO.crear(proveedor); }
    public void actualizar(Proveedor proveedor) { proveedorDAO.actualizar(proveedor); }
    public void eliminar(int id) { proveedorDAO.eliminar(id); }
}