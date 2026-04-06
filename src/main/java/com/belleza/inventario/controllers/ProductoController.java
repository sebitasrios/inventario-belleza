package com.belleza.inventario.controllers;

import com.belleza.inventario.entities.Producto;
import com.belleza.inventario.services.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping
    public List<Producto> obtenerTodos() {
        return productoService.obtenerTodos();
    }

    @GetMapping("/{id}")
    public Producto obtenerPorId(@PathVariable int id) {
        return productoService.obtenerPorId(id);
    }

    @PostMapping
    public String crear(@RequestBody Producto producto) {
        productoService.crear(producto);
        return "Producto creado exitosamente";
    }

    @PutMapping("/{id}")
    public String actualizar(@PathVariable int id, @RequestBody Producto producto) {
        producto.setId(id);
        productoService.actualizar(producto);
        return "Producto actualizado exitosamente";
    }

    @DeleteMapping("/{id}")
    public String eliminar(@PathVariable int id) {
        productoService.eliminar(id);
        return "Producto eliminado exitosamente";
    }
}