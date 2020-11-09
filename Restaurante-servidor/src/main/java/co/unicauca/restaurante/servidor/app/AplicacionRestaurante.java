package co.unicauca.restaurante.servidor.app;

import co.unicauca.restaurante.servidor.infra.RestauranteServerSocket;
import co.unicauca.serversocket.serversockettemplate.infra.ServerSocketTemplate;

/**
 *
 * @author jhonfer ruiz
 * @author Jhonny Rosero
 */
public class AplicacionRestaurante {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //se crea el socket
        ServerSocketTemplate server = new RestauranteServerSocket();
        //se inicia
        server.startServer();
    }
    
}
