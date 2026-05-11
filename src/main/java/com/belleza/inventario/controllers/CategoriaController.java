package com.belleza.inventario.controllers;

import com.belleza.inventario.entities.Categoria;
import com.belleza.inventario.services.ICategoriaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/categorias")
@Tag(name = "Categorias", description = "Operaciones para gestionar las categorias de productos")
public class CategoriaController {

    @Autowired
    private ICategoriaService categoriaService;

    // ── CRUD (SQL) ────────────────────────────────────────────────────────────

    @GetMapping
    @Operation(summary = "Listar todas las categorias")
    @ApiResponse(responseCode = "200", description = "Lista obtenida exitosamente")
    public ResponseEntity<List<Categoria>> obtenerTodos() {
        return ResponseEntity.ok(categoriaService.obtenerTodos());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar categoria por ID")
    @ApiResponse(responseCode = "200", description = "Categoria encontrada")
    @ApiResponse(responseCode = "404", description = "Categoria no encontrada")
    public ResponseEntity<Categoria> obtenerPorId(@PathVariable int id) {
        Categoria categoria = categoriaService.obtenerPorId(id);
        if (categoria == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(categoria);
    }

    @PostMapping
    @Operation(summary = "Crear una categoria")
    @ApiResponse(responseCode = "201", description = "Categoria creada exitosamente")
    @ApiResponse(responseCode = "409", description = "Ya existe una categoria con ese nombre")
    public ResponseEntity<String> crear(@RequestBody Categoria categoria) {
        Optional<Categoria> existente = categoriaService.buscarPorNombreExacto(categoria.getNombre());
        if (existente.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Ya existe una categoria con el nombre: " + categoria.getNombre());
        }
        categoriaService.crear(categoria);
        return ResponseEntity.status(HttpStatus.CREATED).body("Categoria creada exitosamente");
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar una categoria")
    @ApiResponse(responseCode = "200", description = "Categoria actualizada exitosamente")
    @ApiResponse(responseCode = "404", description = "Categoria no encontrada")
    public ResponseEntity<String> actualizar(@PathVariable int id, @RequestBody Categoria categoria) {
        // ← verificar que existe antes de actualizar
        if (categoriaService.obtenerPorId(id) == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Categoria no encontrada con ID: " + id);
        }
        categoria.setIdCategoria(id);
        categoriaService.actualizar(categoria);
        return ResponseEntity.ok("Categoria actualizada exitosamente");
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar una categoria")
    @ApiResponse(responseCode = "204", description = "Categoria eliminada exitosamente")
    @ApiResponse(responseCode = "404", description = "Categoria no encontrada")
    public ResponseEntity<Void> eliminar(@PathVariable int id) {
        // ← verificar que existe antes de eliminar
        if (categoriaService.obtenerPorId(id) == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        categoriaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    // ── Escenarios JPA ────────────────────────────────────────────────────────

    @GetMapping("/buscar")
    @Operation(summary = "Buscar categorias por nombre parcial (JPA)")
    @ApiResponse(responseCode = "200", description = "Lista de categorias coincidentes")
    public ResponseEntity<List<Categoria>> buscarPorNombre(@RequestParam String nombre) {
        return ResponseEntity.ok(categoriaService.buscarPorNombreConteniendo(nombre));
    }
}