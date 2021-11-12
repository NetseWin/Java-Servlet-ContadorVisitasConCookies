
package web;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ContadorVisitasServlet")
public class ContadorVisitasServlet extends HttpServlet {
     protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Declaramos la variable contador
        int contador = 0;
        //revisar en el arreglo de cookie contadorVisitas
        Cookie[] cookies = request.getCookies();
        //si a variable cookies es diferente a nulo, vamos a poder iterar el arreglo
        if(cookies != null){
            for(Cookie c : cookies){
                //una vez estando en el arreglo de cookies preguntamos por el nombre de la cookie
                if(c.getName().equals("contadorVisitas")){
                    contador = Integer.parseInt( c.getValue());
                    
                }
            }
        }
        //incrementamos  el contador en uno
        contador++;
        //Agregamos la respuesta al navegador, como ambos parametros reciben 
        //tipo de dato string, convertimos en string el contador antes de agregarlo
        Cookie c = new Cookie("contadorVisitas", Integer.toString(contador));
        //la cookie se almacenara en el cliente por 1 hora(3600seg), es decir agregamos un tiempo de expiracion
        c.setMaxAge(3600);
        response.addCookie(c);
        
        //Mandamos el mensaje al navegador
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.print("Contador de visits de cada cliente: " + contador);
    }
}
