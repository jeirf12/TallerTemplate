package co.unicauca.restaurante.servidor.acceso;
import co.unicauca.restaurante.commons.domain.PlatoDia;
import co.unicauca.restaurante.commons.domain.PlatoEspecial;
import co.unicauca.restaurante.commons.domain.Restaurante;

/**
 *interface del repositorio de platos, usarla mediante inyeccion de dependencias
 * @author jhonfer ruiz
 * @author Jhonny Rosero
 */
public interface IPlatoRepositorio {
    /**
     * registrar una tupla en la base de datos de un plato del dia
     * @param instancia objeto plato dia que se desea almacenar
     * @return 
     */
    public String savePlatoDia(PlatoDia instancia);
    /**
     * registra una tupla en la base de datos de un plato especial
     * @param plato objeto plato especial que se desea almacenar
     * @return 
     */
    public String savePlatoEspecial(PlatoEspecial plato);
    /**
     * registrar una tupla en la base de datos de un restaurante
     * @param res objeto plato dia que se desea almacenar
     * @return 
     */
    public String saveRestaurante(Restaurante res);
    /**
     * lista todas las tuplas de los platos del dia
     * @param resId id del restaurante del que se va a mostrar el menu
     * @return 
     */
    public String listarMenuDia(int resId); 
    /**
     * lista todas las tuplas de los platos especiales
     * @param resId id del restaurante del que se va a mostrar el menu
     * @return 
     */
    public String listarMenuEspecial(int resId);
}