package co.unicauca.restaurante.client.access;

import co.unicauca.restaurante.commons.domain.PlatoDia;
import co.unicauca.restaurante.commons.domain.PlatoEspecial;
import co.unicauca.restaurante.commons.domain.Restaurante;
import java.util.List;

/**
 * entidad abstracta del los servicios que el cliente puede solicitar al servidor
 * @author Jhonfer Ruiz
 * @author Jhonny Rosero
 */
public interface IClienteAccess {
    /**
     * envia la solicitud al servidor para guardar un restaurante
     * @param res la instancia a guardar
     * @return (hacer validacion con el retorno)
     * @throws Exception 
     */
    public String saveRestaurante(Restaurante res) throws Exception;
    /**
     *envia la solicitud al servidor para guardar un plato del dia
     * @param instancia una instancia de plato
     * @return devualve el nombre
     * @throws Exception
     */
    public String savePlatoDia(PlatoDia instancia) throws Exception;
    /**
     * envia la solicitud al servidor para guardar un plato especial
     * 
     * @param instancia
     * @return 
     * @throws java.lang.Exception 
     */
    public String savePlatoEspecial(PlatoEspecial instancia) throws Exception;
    /**
     * envia la solicitud al servidor para listar los platos del dia
     * 
     * @param resId
     * @return 
     * @throws java.lang.Exception 
     */
    public List<PlatoDia> listarMenuDia(int resId)throws Exception;
    /**
     * envia la solicitud al servidor para listar los platos especiales
     * 
     * @param resId
     * @return 
     * @throws java.lang.Exception 
     */
    public List<PlatoEspecial> listarMenuEspecial(int resId)throws Exception;
}
