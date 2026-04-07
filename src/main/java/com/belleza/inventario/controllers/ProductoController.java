package com.belleza.inventario.controllers;

import com.belleza.inventario.entities.Producto;
import com.belleza.inventario.services.ProductoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productos")
@Tag(name = "Productos", description = "Operaciones para gestionar el inventario de productos de belleza")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping
    @Operation(summary = "Listar todos los productos", description = "Devuelve la lista completa de productos en el inventario")
    @ApiResponse(responseCode = "200", description = "Lista obtenida exitosamente")
    public List<Producto> obtenerTodos() {
        return productoService.obtenerTodos();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar producto por ID", description = "Devuelve un producto específico según su ID")
    @ApiResponse(responseCode = "200", description = "Producto encontrado")
    public Producto obtenerPorId(@PathVariable int id) {
        return productoService.obtenerPorId(id);
    }

    @PostMapping
    @Operation(summary = "Crear un producto", description = "Agrega un nuevo producto al inventario")
    @ApiResponse(responseCode = "200", description = "Producto creado exitosamente")
    public String crear(@RequestBody Producto producto) {
        productoService.crear(producto);
        return "Producto creado exitosamente";
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un producto", description = "Modifica los datos de un producto existente según su ID")
    @ApiResponse(responseCode = "200", description = "Producto actualizado exitosamente")
    public String actualizar(@PathVariable int id, @RequestBody Producto producto) {
        producto.setId(id);
        productoService.actualizar(producto);
        return "Producto actualizado exitosamente";
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un producto", description = "Elimina un producto del inventario según su ID")
    @ApiResponse(responseCode = "200", description = "Producto eliminado exitosamente")
    public String eliminar(@PathVariable int id) {
        productoService.eliminar(id);
        return "Producto eliminado exitosamente";
    }
}