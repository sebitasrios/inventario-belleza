package com.belleza.inventario.controllers;

import com.belleza.inventario.entities.Categoria;
import com.belleza.inventario.services.CategoriaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<Categoria>> obtenerTodos() {
        return ResponseEntity.ok(categoriaService.obtenerTodos());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar categoría por ID")
    @ApiResponse(responseCode = "200", description = "Categoría encontrada")
    @ApiResponse(responseCode = "404", description = "Categoría no encontrada")
    public ResponseEntity<Categoria> obtenerPorId(@PathVariable int id) {
        Categoria categoria = categoriaService.obtenerPorId(id);
        if (categoria == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(categoria);
    }

    @PostMapping
    @Operation(summary = "Crear una categoría")
    @ApiResponse(responseCode = "201", description = "Categoría creada exitosamente")
    public ResponseEntity<String> crear(@RequestBody Categoria categoria) {
        categoriaService.crear(categoria);
        return ResponseEntity.status(HttpStatus.CREATED).body("Categoría creada exitosamente");
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar una categoría")
    @ApiResponse(responseCode = "200", description = "Categoría actualizada exitosamente")
    @ApiResponse(responseCode = "404", description = "Categoría no encontrada")
    public ResponseEntity<String> actualizar(@PathVariable int id, @RequestBody Categoria categoria) {
        categoria.setIdCategoria(id);
        categoriaService.actualizar(categoria);
        return ResponseEntity.ok("Categoría actualizada exitosamente");
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar una categoría")
    @ApiResponse(responseCode = "204", description = "Categoría eliminada exitosamente")
    public ResponseEntity<Void> eliminar(@PathVariable int id) {
        categoriaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}