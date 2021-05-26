package com.emergentes.controlador;
import com.emergentes.dao.PermisosDAO;
import com.emergentes.dao.PermisosDAOimpl;
import com.emergentes.dao.RolesDAO;
import com.emergentes.dao.RolesDAOimpl;
import com.emergentes.dao.UsuarioDAO;
import com.emergentes.dao.UsuarioDAOimpl;
import com.emergentes.modelo.Permisos;
import com.emergentes.modelo.Roles;
import com.emergentes.modelo.Usuario;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet(name = "PermisosControlador", urlPatterns = {"/PermisosControlador"})
public class PermisosControlador extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            PermisosDAO dao = new PermisosDAOimpl();
            UsuarioDAO daoUsuario = new UsuarioDAOimpl();
            RolesDAO daoRoles = new RolesDAOimpl();
            int id;
            List<Usuario> lista_usuario = null;
            List<Roles> lista_roles = null;
            Permisos permisos = new Permisos();
            String action = (request.getParameter("action") != null) ? request.getParameter("action") : "view";
            System.out.println("Opcion = " + action);
            switch (action) {
                case "add":
                    lista_usuario = daoUsuario.getAll();
                    lista_roles = daoRoles.getAll();
                    request.setAttribute("lista_usuario", lista_usuario);
                    request.setAttribute("lista_roles", lista_roles);
                    request.setAttribute("permisos", permisos);
                    request.getRequestDispatcher("frmpermisos.jsp").forward(request, response);
                    break;
                case "edit":
                    id = Integer.parseInt(request.getParameter("id"));
                    permisos = dao.getById(id);
                    lista_usuario = daoUsuario.getAll();
                    lista_roles = daoRoles.getAll();
                    request.setAttribute("lista_usuario", lista_usuario);
                    request.setAttribute("lista_roles", lista_roles);
                    request.setAttribute("permisos", permisos);
                    request.getRequestDispatcher("frmpermisos.jsp").forward(request, response);
                    break;
                case "delete":
                    id = Integer.parseInt(request.getParameter("id"));
                    dao.delete(id);
                    response.sendRedirect("PermisosControlador");
                    break;
                case "view":
                    List<Permisos> lista = dao.getAll();
                    request.setAttribute("permisoss", lista);
                    request.getRequestDispatcher("permisos.jsp").forward(request, response);
                    break;
            }
        } catch (Exception ex) {
            System.out.println("Error fatal " + ex.getMessage());
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        int id_usuario = Integer.parseInt(request.getParameter("id_usuario"));
        int id_rol = Integer.parseInt(request.getParameter("id_rol"));
        Permisos per = new Permisos();
        per.setId(id);
        per.setId_usuario(id_usuario);
        per.setId_rol(id_rol);
        if (id == 0) {
            // Nuevo
            PermisosDAO dao = new PermisosDAOimpl();
            try {
                dao.insert(per);
                response.sendRedirect("PermisosControlador");
            } catch (Exception ex) {
                Logger.getLogger(PermisosControlador.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            //Editar
            PermisosDAO dao = new PermisosDAOimpl();
            try {
                dao.update(per);
                response.sendRedirect("PermisosControlador");
            } catch (Exception ex) {
                Logger.getLogger(PermisosControlador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
