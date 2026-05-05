package com.belleza.inventario.dao;

import com.belleza.inventario.entities.Proveedor;
import com.belleza.inventario.util.ConexionDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ProveedorDAOImpl implements ProveedorDAO {

    @Autowired
    private ConexionDB conexionDB;

    @Override
    public List<Proveedor> obtenerTodos() {
        List<Proveedor> lista = new ArrayList<>();
        String sql = "SELECT id_proveedor, nombre, telefono, email FROM proveedor";
        try (Connection con = conexionDB.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(mapearProveedor(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    @Override
    public Proveedor obtenerPorId(int id) {
        Proveedor p = null;
        String sql = "SELECT id_proveedor, nombre, telefono, email FROM proveedor WHERE id_proveedor = ?";
        try (Connection con = conexionDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            ps.setInt(1, id);
            if (rs.next()) {
                p = mapearProveedor(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return p;
    }

    @Override
    public void crear(Proveedor proveedor) {
        String sql = "INSERT INTO proveedor (nombre, telefono, email) VALUES (?, ?, ?)";
        try (Connection con = conexionDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, proveedor.getNombre());
            ps.setString(2, proveedor.getTelefono());
            ps.setString(3, proveedor.getEmail());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actualizar(Proveedor proveedor) {
        String sql = "UPDATE proveedor SET nombre=?, telefono=?, email=? WHERE id_proveedor=?";
        try (Connection con = conexionDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, proveedor.getNombre());
            ps.setString(2, proveedor.getTelefono());
            ps.setString(3, proveedor.getEmail());
            ps.setInt(4, proveedor.getIdProveedor());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminar(int id) {
        String sql = "DELETE FROM proveedor WHERE id_proveedor=?";
        try (Connection con = conexionDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Proveedor mapearProveedor(ResultSet rs) throws SQLException {
        Proveedor p = new Proveedor();
        p.setIdProveedor(rs.getInt("id_proveedor"));
        p.setNombre(rs.getString("nombre"));
        p.setTelefono(rs.getString("telefono"));
        p.setEmail(rs.getString("email"));
        return p;
    }
}