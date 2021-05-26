<%
    String opcion = request.getParameter("opcion");
%>
<ul class="nav nav-tabs">
    <li class="nav-item">
        <a class="nav-link <%= (opcion.equals("usuarios") ? "active" : "")%>" href="UsuarioControlador">Usuarios</a>
    </li>
    <li class="nav-item">
        <a class="nav-link <%= (opcion.equals("roles") ? "active" : "")%>" href="RolesControlador">Roles</a>
    </li>
    <li class="nav-item">
        <a class="nav-link <%= (opcion.equals("permisos") ? "active" : "")%>" href="PermisosControlador">Permisos</a>
    </li>
</ul>