package com.belleza.inventario.services;

import com.belleza.inventario.dao.CategoriaDAO;
import com.belleza.inventario.entities.Categoria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CategoriaService {
    @Autowired
    private CategoriaDAO categoriaDAO;

    public List<Categoria> obtenerTodos() { return categoriaDAO.obtenerTodos(); }
    public Categoria obtenerPorId(int id) { return categoriaDAO.obtenerPorId(id); }
    public void crear(Categoria categoria) { categoriaDAO.crear(categoria); }
    public void actualizar(Categoria categoria) { categoriaDAO.actualizar(categoria); }
    public void eliminar(int id) { categoriaDAO.eliminar(id); }
}