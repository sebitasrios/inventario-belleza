package com.belleza.inventario.dao;

import com.belleza.inventario.entities.Producto;
import com.belleza.inventario.util.ConexionDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductoDAOImpl implements ProductoDAO {

    @Autowired
    private ConexionDB conexionDB;

    @Override
    public List<Producto> obtenerTodos() {
        List<Producto> lista = new ArrayList<>();
        String sql = "SELECT id_producto, nombre, descripcion, precio, stock, stock_minimo, id_categoria, id_proveedor FROM producto";
        try (Connection con = conexionDB.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(mapearProducto(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    @Override
    public Producto obtenerPorId(int id) {
        Producto p = null;
        String sql = "SELECT id_producto, nombre, descripcion, precio, stock, stock_minimo, id_categoria, id_proveedor FROM producto WHERE id_producto = ?";
        try (Connection con = conexionDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            ps.setInt(1, id);
            if (rs.next()) {
                p = mapearProducto(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return p;
    }

    @Override
    public void crear(Producto producto) {
        String sql = "INSERT INTO producto (nombre, descripcion, precio, stock, stock_minimo, id_categoria, id_proveedor) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = conexionDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, producto.getNombre());
            ps.setString(2, producto.getDescripcion());
            ps.setDouble(3, producto.getPrecio());
            ps.setInt(4, producto.getStock());
            ps.setInt(5, producto.getStockMinimo());
            ps.setInt(6, producto.getIdCategoria());
            ps.setInt(7, producto.getIdProveedor());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actualizar(Producto producto) {
        String sql = "UPDATE producto SET nombre=?, descripcion=?, precio=?, stock=?, stock_minimo=?, id_categoria=?, id_proveedor=? WHERE id_producto=?";
        try (Connection con = conexionDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, producto.getNombre());
            ps.setString(2, producto.getDescripcion());
            ps.setDouble(3, producto.getPrecio());
            ps.setInt(4, producto.getStock());
            ps.setInt(5, producto.getStockMinimo());
            ps.setInt(6, producto.getIdCategoria());
            ps.setInt(7, producto.getIdProveedor());
            ps.setInt(8, producto.getIdProducto());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminar(int id) {
        String sql = "DELETE FROM producto WHERE id_producto=?";
        try (Connection con = conexionDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Producto mapearProducto(ResultSet rs) throws SQLException {
        Producto p = new Producto();
        p.setIdProducto(rs.getInt("id_producto"));
        p.setNombre(rs.getString("nombre"));
        p.setDescripcion(rs.getString("descripcion"));
        p.setPrecio(rs.getDouble("precio"));
        p.setStock(rs.getInt("stock"));
        p.setStockMinimo(rs.getInt("stock_minimo"));
        p.setIdCategoria(rs.getInt("id_categoria"));
        p.setIdProveedor(rs.getInt("id_proveedor"));
        return p;
    }
}