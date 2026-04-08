package com.belleza.inventario.controllers;

import com.belleza.inventario.entities.Proveedor;
import com.belleza.inventario.services.ProveedorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/proveedores")
@Tag(name = "Proveedores", description = "Operaciones para gestionar los proveedores")
public class ProveedorController {

    @Autowired
    private ProveedorService proveedorService;

    @GetMapping
    @Operation(summary = "Listar todos los proveedores")
    @ApiResponse(responseCode = "200", description = "Lista obtenida exitosamente")
    public List<Proveedor> obtenerTodos() { return proveedorService.obtenerTodos(); }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar proveedor por ID")
    @ApiResponse(responseCode = "200", description = "Proveedor encontrado")
    public Proveedor obtenerPorId(@PathVariable int id) { return proveedorService.obtenerPorId(id); }

    @PostMapping
    @Operation(summary = "Crear un proveedor")
    @ApiResponse(responseCode = "200", description = "Proveedor creado exitosamente")
    public String crear(@RequestBody Proveedor proveedor) {
        proveedorService.crear(proveedor);
        return "Proveedor creado exitosamente";
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un proveedor")
    @ApiResponse(responseCode = "200", description = "Proveedor actualizado exitosamente")
    public String actualizar(@PathVariable int id, @RequestBody Proveedor proveedor) {
        proveedor.setIdProveedor(id);
        proveedorService.actualizar(proveedor);
        return "Proveedor actualizado exitosamente";
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un proveedor")
    @ApiResponse(responseCode = "200", description = "Proveedor eliminado exitosamente")
    public String eliminar(@PathVariable int id) {
        proveedorService.eliminar(id);
        return "Proveedor eliminado exitosamente";
    }
}