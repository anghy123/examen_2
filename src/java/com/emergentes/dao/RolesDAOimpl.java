package com.emergentes.dao;
import com.emergentes.modelo.Roles;
import com.emergentes.utiles.ConexionDB;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
public class RolesDAOimpl extends ConexionDB implements RolesDAO {
    @Override
    public void insert(Roles roles) throws Exception {
        try {
            this.conectar();
            PreparedStatement ps = this.conn.prepareStatement("INSERT INTO roles (descripcion) values (?)");
            ps.setString(1, roles.getDescripcion());
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
    }
    @Override
    public void update(Roles roles) throws Exception {
        try {
            this.conectar();
            PreparedStatement ps = this.conn.prepareStatement("UPDATE roles SET descripcion =? where id = ?");
            ps.setString(1, roles.getDescripcion());
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
            PreparedStatement ps = this.conn.prepareStatement("DELETE FROM roles WHERE id = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
    }
    @Override
    public Roles getById(int id) throws Exception {
        Roles rol = new Roles();
        try {
            this.conectar();
            PreparedStatement ps = this.conn.prepareStatement("SELECT * FROM roles WHERE id = ?");
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                rol.setId(rs.getInt("id"));
                rol.setDescripcion(rs.getString("descripcion"));
            }
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
        return rol;
    }
    @Override
    public List<Roles> getAll() throws Exception {
        List<Roles> lista = null;
        try {
            this.conectar();

            PreparedStatement ps = this.conn.prepareStatement("SELECT * FROM roles");
            ResultSet rs = ps.executeQuery();
            lista = new ArrayList<Roles>();
            while (rs.next()) {
                Roles rol = new Roles();

                rol.setId(rs.getInt("id"));
                rol.setDescripcion(rs.getString("descripcion"));

                lista.add(rol);
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
