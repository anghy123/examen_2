package com.emergentes.dao;
import com.emergentes.modelo.Permisos;
import com.emergentes.utiles.ConexionDB;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
public class PermisosDAOimpl extends ConexionDB implements PermisosDAO {
    @Override
    public void insert(Permisos permisos) throws Exception {
        try {
            this.conectar();
            String sql = "INSERT INTO permisos (id_usuario, id_rol) values (?,?)";
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ps.setInt(1, permisos.getId_usuario());
            ps.setInt(2, permisos.getId_rol());
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
    }
    @Override
    public void update(Permisos permisos) throws Exception {
        try {
            this.conectar();
            String sql = "UPDATE permisos SET id_usuario= ?, id_rol = ? WHERE id = ?";
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ps.setInt(1, permisos.getId_usuario());
            ps.setInt(2, permisos.getId_rol());
            ps.setInt(4, permisos.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
    }
    @Override
    public void delete(int id) throws Exception {
        try {
            this.conectar();
            String sql = "DELETE FROM permisos WHERE id = ?";
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
    }
    @Override
    public Permisos getById(int id) throws Exception {
        Permisos pe = new Permisos();
        try {
            this.conectar();
            String sql = "SELECT * FROM permisos WHERE id = ?";
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                pe.setId(rs.getInt("id"));
                pe.setId_usuario(rs.getInt("id_usuario"));
                pe.setId_rol(rs.getInt("id_rol"));
            }
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
        return pe;
    }
    @Override
    public List<Permisos> getAll() throws Exception {
        List<Permisos> lista = null;
        try {
            this.conectar();
            String sql = "SELECT p.*, u.usuario as 'usuario', r.descripcion as 'rol' FROM permisos p ";
            sql += "LEFT JOIN usuarios u ON p.id_usuario = u.id ";
            sql += "LEFT JOIN roles r ON p.id_rol = r.id";
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            lista = new ArrayList<Permisos>();
            while (rs.next()) {
                Permisos p = new Permisos();
                p.setId(rs.getInt("id"));
                p.setId_usuario(rs.getInt("id_usuario"));
                p.setId_rol(rs.getInt("id_rol"));
                p.setUsuario(rs.getString("usuario"));
                p.setRol(rs.getString("rol"));
                lista.add(p);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
        return lista;
    }
}
