package com.belleza.inventario.services;

import com.belleza.inventario.dao.ProductoDAO;
import com.belleza.inventario.entities.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductoService {
    @Autowired
    private ProductoDAO productoDAO;

    public List<Producto> obtenerTodos() { return productoDAO.obtenerTodos(); }
    public Producto obtenerPorId(int id) { return productoDAO.obtenerPorId(id); }
    public void crear(Producto producto) { productoDAO.crear(producto); }
    public void actualizar(Producto producto) { productoDAO.actualizar(producto); }
    public void eliminar(int id) { productoDAO.eliminar(id); }
}