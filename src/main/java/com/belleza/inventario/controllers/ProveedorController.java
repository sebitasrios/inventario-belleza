package com.belleza.inventario.controllers;

import com.belleza.inventario.entities.Proveedor;
import com.belleza.inventario.services.IProveedorService;
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
@RequestMapping("/proveedores")
@Tag(name = "Proveedores", description = "Operaciones para gestionar los proveedores")
public class ProveedorController {

    // Inyectamos la INTERFAZ — desacoplamiento de capas
    @Autowired
    private IProveedorService proveedorService;

    // ── CRUD (SQL) ────────────────────────────────────────────────────────────

    @GetMapping
    @Operation(summary = "Listar todos los proveedores")
    @ApiResponse(responseCode = "200", description = "Lista obtenida exitosamente")
    public ResponseEntity<List<Proveedor>> obtenerTodos() {
        return ResponseEntity.ok(proveedorService.obtenerTodos());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar proveedor por ID")
    @ApiResponse(responseCode = "200", description = "Proveedor encontrado")
    @ApiResponse(responseCode = "404", description = "Proveedor no encontrado")
    public ResponseEntity<Proveedor> obtenerPorId(@PathVariable int id) {
        Proveedor proveedor = proveedorService.obtenerPorId(id);
        if (proveedor == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(proveedor);
    }

    @PostMapping
    @Operation(summary = "Crear un proveedor")
    @ApiResponse(responseCode = "201", description = "Proveedor creado exitosamente")
    @ApiResponse(responseCode = "409", description = "Ya existe un proveedor con ese email")
    public ResponseEntity<String> crear(@RequestBody Proveedor proveedor) {
        // Escenario JPA: verificar email duplicado antes de crear
        Optional<Proveedor> existente = proveedorService.buscarPorEmail(proveedor.getEmail());
        if (existente.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Ya existe un proveedor registrado con el email: " + proveedor.getEmail());
        }
        proveedorService.crear(proveedor);
        return ResponseEntity.status(HttpStatus.CREATED).body("Proveedor creado exitosamente");
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un proveedor")
    @ApiResponse(responseCode = "200", description = "Proveedor actualizado exitosamente")
    @ApiResponse(responseCode = "404", description = "Proveedor no encontrado")
    public ResponseEntity<String> actualizar(@PathVariable int id, @RequestBody Proveedor proveedor) {
        proveedor.setIdProveedor(id);
        proveedorService.actualizar(proveedor);
        return ResponseEntity.ok("Proveedor actualizado exitosamente");
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un proveedor")
    @ApiResponse(responseCode = "204", description = "Proveedor eliminado exitosamente")
    public ResponseEntity<Void> eliminar(@PathVariable int id) {
        proveedorService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    // ── Escenarios JPA ────────────────────────────────────────────────────────

    @GetMapping("/buscar")
    @Operation(summary = "Buscar proveedores por nombre parcial (JPA)")
    @ApiResponse(responseCode = "200", description = "Lista de proveedores coincidentes")
    public ResponseEntity<List<Proveedor>> buscarPorNombre(@RequestParam String nombre) {
        return ResponseEntity.ok(proveedorService.buscarPorNombreConteniendo(nombre));
    }

    @GetMapping("/por-email")
    @Operation(summary = "Buscar proveedor por email exacto (JPA)")
    @ApiResponse(responseCode = "200", description = "Proveedor encontrado")
    @ApiResponse(responseCode = "404", description = "Proveedor no encontrado")
    public ResponseEntity<Proveedor> buscarPorEmail(@RequestParam String email) {
        return proveedorService.buscarPorEmail(email)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}