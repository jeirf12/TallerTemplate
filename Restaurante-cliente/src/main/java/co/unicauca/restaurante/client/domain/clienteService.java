package co.unicauca.restaurante.client.domain;

import co.unicauca.restaurante.client.access.IClienteAccess;
import co.unicauca.restaurante.commons.domain.PlatoDia;
import co.unicauca.restaurante.commons.domain.PlatoEspecial;
import co.unicauca.restaurante.commons.domain.Restaurante;
import java.util.List;

/**
 * servicios que el cliente puede usar del servidor (mascaras)
 * se comunica con la capa de bajo nivel que envia la solicitud
 * @author Jhonfer Ruiz
 * @author Jhonny Rosero
 */
public class clienteService {
    private final IClienteAccess service;
    /**
     * inyeccion de dependencias
     * @param service un clase concreta que implementa la interfaz de acceso, se instancia con una fabrica
     */
    public clienteService(IClienteAccess service) {
        this.service = service;
    }
    /**
     * mascara para guardar un restaurante, la solicitud se envia al acceso
     * @param res instancia concreta a enviar su informacion
     * @return
     * @throws Exception 
     */
    public String saveRestaurante(Restaurante res) throws Exception{
        return service.saveRestaurante(res);
    }
    /**
     * mascara para guardar un platodeldia, la solicitud se envia al acceso
     * @param plato instancia concreta a enviar su informacion
     * @return
     * @throws Exception 
     */
    public String savePlatoDia(PlatoDia plato) throws Exception{
        return service.savePlatoDia(plato); 
    }
    /**
     * mascara para guardar un plato especial, la solicitud se envia al acceso
     * @param instancia  es una instancia concreta para enviar su informacion
     * @return
     * @throws Exception 
     */
    public String savePlatoEspecial(PlatoEspecial instancia) throws Exception{
        return service.savePlatoEspecial(instancia);
    };
    /**
     * mascara para obtener una lista de los platos del dia, la solicitud se envia al acceso
     * @param resId  es una instancia concreta para enviar su informacion
     * @return
     * @throws Exception 
     */
    public List<PlatoDia> listarMenuDia(int resId)throws Exception{
        return service.listarMenuDia(resId);
    }
    /**
     * mascara para obtener una lista de los platos especiales, la solicitud se envia al acceso
     * @param resId  es una instancia concreta para enviar su informacion
     * @return
     * @throws Exception 
     */
    public List<PlatoEspecial> listarMenuEspecial(int resId) throws Exception{
        return service.listarMenuEspecial(resId);
    }
}
