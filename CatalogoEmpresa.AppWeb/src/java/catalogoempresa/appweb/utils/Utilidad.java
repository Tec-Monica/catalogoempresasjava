
package catalogoempresa.appweb.utils;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class Utilidad {
 public static String getParameter(HttpServletRequest request, String pKey, String pDefault) {
        String result = "";
        String value = request.getParameter(pKey);//pKey: sirve para saber con que accion se esta trabajando 
        if (value != null && value.trim().length() > 0) {//condición
            result = value; // entonces result sera igual al value
        } else {
            result = pDefault;
        }
        return result;
    }
    
    public static void enviarError(String pError, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("error", pError); // esta peticion manda a llamar a un atributo llamado error
        request.getRequestDispatcher("Views/Shared/error.jsp").forward(request, response);//para navegar en la JSP
    }
    
    public static String obtenerRuta(HttpServletRequest request, String pStrRuta) {//
        return request.getContextPath() + pStrRuta;//getContextPath: cual es la URL de mi servidor 
    }   
}
