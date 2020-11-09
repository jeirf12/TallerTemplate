package co.unicauca.restaurante.servidor.infra;

import co.unicauca.restaurante.commons.domain.DiaEnum;
import co.unicauca.restaurante.commons.domain.PlatoDia;
import co.unicauca.restaurante.commons.domain.PlatoEspecial;
import co.unicauca.restaurante.commons.domain.Restaurante;
import co.unicauca.restaurante.commons.infra.Protocol;
import co.unicauca.restaurante.commons.infra.Utilities;
import co.unicauca.restaurante.servidor.acceso.FabricaRepositorio;
import co.unicauca.restaurante.servidor.dominio.servidor.PlatoServicio;
import com.google.gson.Gson;
import java.util.logging.Level;
import java.util.logging.Logger;
import co.unicauca.restaurante.servidor.acceso.IPlatoRepositorio;
import co.unicauca.serversocket.serversockettemplate.infra.ServerSocketTemplate;

/**
 * esta clase se encarga de establecer y gestionar la conexion entre el servidor y los clientes
 * @author jhonfer ruiz
 * @author Jhonny Rosero
 */
public class RestauranteServerSocket extends ServerSocketTemplate{
    /**
     * servicio del plato, continene el mecanismo para comunicar platoD la base de datos y sus operaciones
     */
    private PlatoServicio service; //se debe iniciializar obligatorioamente
    
    /**
     * constructor obtiene el servidor mediante la fabrica
 instancia platoD servicio pasando el repositorio obtenido
     */
    public RestauranteServerSocket(){
        //inyeccion de dependencias par hacer la inyeccion
        IPlatoRepositorio repository = FabricaRepositorio.getInstance().getRepository();
        service = new PlatoServicio(repository);
    }

    public PlatoServicio getService() {
        return service;
    }

    public void setService(PlatoServicio service) {
        this.service = service;
    }
 
    @Override
    protected ServerSocketTemplate init() {
        PORT = Integer.parseInt(Utilities.loadProperty("server.port"));
        IPlatoRepositorio repository = FabricaRepositorio.getInstance().getRepository();
        this.setService(new PlatoServicio(repository));
        return this;
    }
    /**
     * Procesar la solicitud que proviene de la aplicación cliente
     *
     * @param request petición que proviene del cliente socket en formato
     * json que viene de esta manera:
     * "{"resource":"customer","action":"get","parameters":[{"name":"id","value":"1"}]}" 
     *
     */
    @Override
    protected void processRequest(String request){
        Gson gson = new Gson();
        Protocol protocolRequest = gson.fromJson(request, Protocol.class);
        switch (protocolRequest.getResource()) {
            case "administrador":
                if (protocolRequest.getAction().equals("postPlatoDia")) {
                    administradorRegistrarPlatoDia(protocolRequest);
                }
                
                if(protocolRequest.getAction().equals("postPlatoEspecial")){
                    administradorRegistrarPlatoEspecial(protocolRequest);               
                }
                
                if(protocolRequest.getAction().equals("postRestaurante")){
                    this.clienteResgistrarRestaurante(protocolRequest);
                }
                
                if (protocolRequest.getAction().equals("listarMenuDia")) {
                    this.listarMenuDia(protocolRequest);
                }
                
                if (protocolRequest.getAction().equals("listarMenuEspecial")) {
                    this.listarMenuEspecial(protocolRequest);
                }
                break;
            case "comprador":
                Logger.getLogger(RestauranteServerSocket.class.getName()).log(Level.INFO.SEVERE, "solicitud comprador recibida");
                if (protocolRequest.getAction().equals("listarMenuDia")) {
                    this.listarMenuDia(protocolRequest);
                }
                if (protocolRequest.getAction().equals("listarMenuEspecial")) {
                    this.listarMenuEspecial(protocolRequest);
                }
                break;

            }
    }

    /**
     * Recibe la peticion del cliente, manda el id del restaurante
     * y manda esta peticion procesada al repositorio del servidor
     * para el menu por dias
     * 
     * @param protocolRequest 
     */
    private void listarMenuDia(Protocol protocolRequest){
        int resId =Integer.parseInt(protocolRequest.getParameters().get(0).getValue());
        String response;
        response=service.listarMenuDia(resId);
        output.println(response);
        
    }
    /**
     * Recibe la peticion del cliente, manda el id del restaurante
     * y manda esta peticion procesada al repositorio del servidor
     * para el menu especial
     * 
     * @param protocolRequest 
     */
    private void listarMenuEspecial(Protocol protocolRequest){
        int resdId=Integer.parseInt(protocolRequest.getParameters().get(0).getValue());
        String response;
        response=service.listarMenuEspecial(resdId);
        output.println(response);
    }
    
    private void clienteResgistrarRestaurante(Protocol protocolRequest){
        //aqui se recibe con el mismo orden
        Restaurante res = new Restaurante();
        res.setId(Integer.parseInt(protocolRequest.getParameters().get(0).getValue()));
        res.setNombre(protocolRequest.getParameters().get(1).getValue());
        String response=null;
        //el servicio comunicara con la base de datos,
        //se pasa el plato creado, y servicio llamara al repositorio
        response = service.saveRestaurante(res);
        output.println(response);
    }

     /**
     * Procesa la solicitud de registrar un plato del dia que ha enviado el cliente
     *
     * @param protocolRequest Protocolo de la solicitud
     */
    private void administradorRegistrarPlatoDia(Protocol protocolRequest) {
        //crea la instancia
        PlatoDia platoD = new PlatoDia();
        //se asignan los atributos de la instancia, segun los valores de los parametros
        //el orden debe ser exacto
        platoD.setId(Integer.parseInt(protocolRequest.getParameters().get(0).getValue()));
        platoD.setMenuId(Integer.parseInt(protocolRequest.getParameters().get(1).getValue()));
        platoD.setNombre(protocolRequest.getParameters().get(2).getValue());
        platoD.setDescripcion(protocolRequest.getParameters().get(3).getValue());
        platoD.setDiaSemana(DiaEnum.valueOf(protocolRequest.getParameters().get(4).getValue()));
        platoD.setEntrada(protocolRequest.getParameters().get(5).getValue());
        platoD.setPrincipio(protocolRequest.getParameters().get(6).getValue());
        platoD.setBebida(protocolRequest.getParameters().get(7).getValue());
        platoD.setCarne(protocolRequest.getParameters().get(8).getValue());
        platoD.setPrecio(Double.parseDouble(protocolRequest.getParameters().get(9).getValue()));
        //hacer validacion para esta, es decir sobre el parseo del dato
        String response=null;
        //el servicio comunicara con la base de datos,
        //se pasa el plato creado, y servicio llamara al repositorio
        response = service.savePlatoDia(platoD);
        output.println(response);
    }
    /**
     * Procesa la solicitud de registrar un plato Especial que ha enviado el cliente
     *
     * @param protocolRequest Protocolo de la solicitud
     */
    private void administradorRegistrarPlatoEspecial(Protocol protocolRequest) {
        //crea la instancia
        PlatoEspecial platoE = new PlatoEspecial();
        //se asignan los atributos de la instancia, segun los valores de los parametros
        //el orden debe ser exacto
        platoE.setId(Integer.parseInt(protocolRequest.getParameters().get(0).getValue()));
        platoE.setMenuEsp(Integer.parseInt(protocolRequest.getParameters().get(1).getValue()));
        platoE.setNombre(protocolRequest.getParameters().get(2).getValue());
        platoE.setDescripcion(protocolRequest.getParameters().get(3).getValue());
        platoE.setPrecio(Double.parseDouble(protocolRequest.getParameters().get(4).getValue()));
        //hacer validacion para esta, es decir sobre el parseo del dato
        String response=null;
        //el servicio comunicara con la base de datos,
        //se pasa el plato creado, y servicio llamara al repositorio
        response = service.savePlatoEspecial(platoE);
        output.println(response);
    }    
}