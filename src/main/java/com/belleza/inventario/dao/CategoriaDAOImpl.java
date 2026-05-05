package com.belleza.inventario.dao;

import com.belleza.inventario.entities.Categoria;
import com.belleza.inventario.util.ConexionDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CategoriaDAOImpl implements CategoriaDAO {

    @Autowired
    private ConexionDB conexionDB;

    @Override
    public List<Categoria> obtenerTodos() {
        List<Categoria> lista = new ArrayList<>();
        String sql = "SELECT id_categoria, nombre FROM categoria";
        try (Connection con = conexionDB.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(mapearCategoria(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    @Override
    public Categoria obtenerPorId(int id) {
        Categoria c = null;
        String sql = "SELECT id_categoria, nombre FROM categoria WHERE id_categoria = ?";
        try (Connection con = conexionDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            ps.setInt(1, id);
            if (rs.next()) {
                c = mapearCategoria(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return c;
    }

    @Override
    public void crear(Categoria categoria) {
        String sql = "INSERT INTO categoria (nombre) VALUES (?)";
        try (Connection con = conexionDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, categoria.getNombre());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actualizar(Categoria categoria) {
        String sql = "UPDATE categoria SET nombre=? WHERE id_categoria=?";
        try (Connection con = conexionDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, categoria.getNombre());
            ps.setInt(2, categoria.getIdCategoria());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminar(int id) {
        String sql = "DELETE FROM categoria WHERE id_categoria=?";
        try (Connection con = conexionDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Categoria mapearCategoria(ResultSet rs) throws SQLException {
        Categoria c = new Categoria();
        c.setIdCategoria(rs.getInt("id_categoria"));
        c.setNombre(rs.getString("nombre"));
        return c;
    }
}