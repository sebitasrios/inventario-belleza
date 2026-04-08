package com.belleza.inventario.controllers;

import com.belleza.inventario.entities.Categoria;
import com.belleza.inventario.services.CategoriaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/categorias")
@Tag(name = "Categorias", description = "Operaciones para gestionar las categorías de productos")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    @Operation(summary = "Listar todas las categorías")
    @ApiResponse(responseCode = "200", description = "Lista obtenida exitosamente")
    public List<Categoria> obtenerTodos() { return categoriaService.obtenerTodos(); }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar categoría por ID")
    @ApiResponse(responseCode = "200", description = "Categoría encontrada")
    public Categoria obtenerPorId(@PathVariable int id) { return categoriaService.obtenerPorId(id); }

    @PostMapping
    @Operation(summary = "Crear una categoría")
    @ApiResponse(responseCode = "200", description = "Categoría creada exitosamente")
    public String crear(@RequestBody Categoria categoria) {
        categoriaService.crear(categoria);
        return "Categoría creada exitosamente";
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar una categoría")
    @ApiResponse(responseCode = "200", description = "Categoría actualizada exitosamente")
    public String actualizar(@PathVariable int id, @RequestBody Categoria categoria) {
        categoria.setIdCategoria(id);
        categoriaService.actualizar(categoria);
        return "Categoría actualizada exitosamente";
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar una categoría")
    @ApiResponse(responseCode = "200", description = "Categoría eliminada exitosamente")
    public String eliminar(@PathVariable int id) {
        categoriaService.eliminar(id);
        return "Categoría eliminada exitosamente";
    }
}