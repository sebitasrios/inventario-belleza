package com.belleza.inventario.controllers;

import com.belleza.inventario.entities.Producto;
import com.belleza.inventario.services.IProductoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productos")
@Tag(name = "Productos", description = "Operaciones para gestionar el inventario de productos de belleza")
public class ProductoController {

    @Autowired
    private IProductoService productoService;

    // ── CRUD (SQL) ────────────────────────────────────────────────────────────

    @GetMapping
    @Operation(summary = "Listar todos los productos")
    @ApiResponse(responseCode = "200", description = "Lista obtenida exitosamente")
    public ResponseEntity<List<Producto>> obtenerTodos() {
        return ResponseEntity.ok(productoService.obtenerTodos());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar producto por ID")
    @ApiResponse(responseCode = "200", description = "Producto encontrado")
    @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    public ResponseEntity<Producto> obtenerPorId(@PathVariable int id) {
        Producto producto = productoService.obtenerPorId(id);
        if (producto == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(producto);
    }

    @PostMapping
    @Operation(summary = "Crear un producto")
    @ApiResponse(responseCode = "201", description = "Producto creado exitosamente")
    public ResponseEntity<String> crear(@RequestBody Producto producto) {
        productoService.crear(producto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Producto creado exitosamente");
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un producto")
    @ApiResponse(responseCode = "200", description = "Producto actualizado exitosamente")
    @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    public ResponseEntity<String> actualizar(@PathVariable int id, @RequestBody Producto producto) {
        // ← verificar que existe antes de actualizar
        if (productoService.obtenerPorId(id) == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Producto no encontrado con ID: " + id);
        }
        producto.setIdProducto(id);
        productoService.actualizar(producto);
        return ResponseEntity.ok("Producto actualizado exitosamente");
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un producto")
    @ApiResponse(responseCode = "204", description = "Producto eliminado exitosamente")
    @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    public ResponseEntity<Void> eliminar(@PathVariable int id) {
        // ← verificar que existe antes de eliminar
        if (productoService.obtenerPorId(id) == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        productoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    // ── Escenarios JPA ────────────────────────────────────────────────────────

    @GetMapping("/bajo-stock")
    @Operation(summary = "Productos con stock por debajo del minimo (JPA)",
            description = "Devuelve los productos cuyo stock actual es menor al stock minimo configurado")
    @ApiResponse(responseCode = "200", description = "Lista de productos con stock bajo")
    public ResponseEntity<List<Producto>> obtenerBajoStock() {
        return ResponseEntity.ok(productoService.obtenerProductosBajoStock());
    }

    @GetMapping("/por-categoria/{idCategoria}")
    @Operation(summary = "Productos por categoria (JPA)")
    @ApiResponse(responseCode = "200", description = "Lista de productos de la categoria")
    public ResponseEntity<List<Producto>> obtenerPorCategoria(@PathVariable int idCategoria) {
        return ResponseEntity.ok(productoService.obtenerPorCategoria(idCategoria));
    }

    @GetMapping("/por-proveedor/{idProveedor}")
    @Operation(summary = "Productos por proveedor (JPA)")
    @ApiResponse(responseCode = "200", description = "Lista de productos del proveedor")
    public ResponseEntity<List<Producto>> obtenerPorProveedor(@PathVariable int idProveedor) {
        return ResponseEntity.ok(productoService.obtenerPorProveedor(idProveedor));
    }

    @GetMapping("/precio-mayor/{precio}")
    @Operation(summary = "Productos con precio mayor al indicado (JPA)")
    @ApiResponse(responseCode = "200", description = "Lista de productos filtrados por precio")
    public ResponseEntity<List<Producto>> obtenerPorPrecioMayorA(@PathVariable double precio) {
        return ResponseEntity.ok(productoService.obtenerPorPrecioMayorA(precio));
    }
}