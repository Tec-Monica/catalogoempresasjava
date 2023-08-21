
package catalogoempresa.appweb.utils;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import catalogoempresa.entidadesdenegocio.*;

public class SessionUser {
   public static void autenticarUser(HttpServletRequest request, Usuario pUsuario) {
        HttpSession session = (HttpSession) request.getSession();
        session.setMaxInactiveInterval(3600); // La cantidad maxima que estara abierta una sesión.
        session.setAttribute("auth", true);
        session.setAttribute("user", pUsuario.getLogin());
        session.setAttribute("rol", pUsuario.getRol().getNombre()); //Extrae el nombre que esta en un objeto 
    }
    
    public static boolean isAuth(HttpServletRequest request) {
        HttpSession session = (HttpSession) request.getSession();
        boolean auth = session.getAttribute("auth") != null ? (boolean) session.getAttribute("auth") : false; 
        return auth;
    }
    
    public static String getUser(HttpServletRequest request) {
        HttpSession session = (HttpSession) request.getSession();
        String user = "";
        if (SessionUser.isAuth(request)) { 
            user = session.getAttribute("user") != null ? (String) session.getAttribute("user") : "";
        }
        return user;
    }
    
    public static String getRol(HttpServletRequest request) {
        HttpSession session = (HttpSession) request.getSession(); //Muestra sesión
        String user = "";
        if (SessionUser.isAuth(request)) {//Verifica si el usuario esta autenticado (iniciando sesion)
            user = session.getAttribute("rol") != null ? (String) session.getAttribute("rol") : "";//es un operador ternario es un IF resumido
        }
        return user;
    }
    
    public static void authorize(HttpServletRequest request, HttpServletResponse response, IAuthorize pIAuthorize) throws ServletException, IOException {
        if (SessionUser.isAuth(request)) {//verifica que el usuario exista 
            pIAuthorize.authorize(); //ejecutamos este metodo
        } else {
            response.sendRedirect("Usuario?accion=login");//si no a iniciado sesión 
        }
    }
    
    public static void cerrarSession(HttpServletRequest request) {//Metodo para crear sesion 
        HttpSession session = (HttpSession) request.getSession();
        session.invalidate();
    }  
}
