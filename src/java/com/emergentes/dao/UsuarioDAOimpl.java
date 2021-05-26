package com.emergentes.dao;
import com.emergentes.modelo.Usuario;
import com.emergentes.utiles.ConexionDB;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
public class UsuarioDAOimpl extends ConexionDB implements UsuarioDAO {
    @Override
    public void insert(Usuario usuario) throws Exception {
        try {
            this.conectar();
            PreparedStatement ps = this.conn.prepareStatement("INSERT INTO usuarios (usuario,correo,clave) values (?,?,md5(?))");
            ps.setString(1, usuario.getUsuario());
            ps.setString(2, usuario.getCorreo());
            ps.setString(3, usuario.getClave());
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
    }
    @Override
    public void update(Usuario usuario) throws Exception {
        try {
            this.conectar();
            PreparedStatement ps = this.conn.prepareStatement("UPDATE usuarios SET usuario = ?, correo = ?, clave = md5(?) where id = ?");
            ps.setString(1, usuario.getUsuario());
            ps.setString(2, usuario.getCorreo());
            ps.setString(3, usuario.getClave());
            ps.setInt(4, usuario.getId());
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
            PreparedStatement ps = this.conn.prepareStatement("DELETE FROM usuarios WHERE id = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
    }
    @Override
    public Usuario getById(int id) throws Exception {
        Usuario u = new Usuario();
        try {
            this.conectar();

            PreparedStatement ps = this.conn.prepareStatement("SELECT * FROM usuarios WHERE id = ?");
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                u.setId(rs.getInt("id"));
                u.setUsuario(rs.getString("usuario"));
                u.setCorreo(rs.getString("correo"));
                u.setClave(rs.getString("clave"));
            }
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
        return u;
    }
    @Override
    public List<Usuario> getAll() throws Exception {
        List<Usuario> lista = null;
        try {
            this.conectar();

            PreparedStatement ps = this.conn.prepareStatement("SELECT * FROM usuarios");
            ResultSet rs = ps.executeQuery();
            lista = new ArrayList<Usuario>();
            while (rs.next()) {
                Usuario us = new Usuario();
                us.setId(rs.getInt("id"));
                us.setUsuario(rs.getString("usuario"));
                us.setCorreo(rs.getString("correo"));
                us.setClave(rs.getString("clave"));
                lista.add(us);
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
