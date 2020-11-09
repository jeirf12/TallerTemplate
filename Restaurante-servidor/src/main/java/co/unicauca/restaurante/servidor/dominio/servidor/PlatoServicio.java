/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.unicauca.restaurante.servidor.dominio.servidor;

import co.unicauca.restaurante.commons.domain.PlatoDia;
import co.unicauca.restaurante.commons.domain.PlatoEspecial;
import co.unicauca.restaurante.commons.domain.Restaurante;
import co.unicauca.restaurante.servidor.acceso.IPlatoRepositorio;

/**
 * Comunicación con la capa de bajo nivel
 * metodos contra la base de datos
 * @author jhonfer ruiz
 * @author Jhonny Rosero
 */
public class PlatoServicio {
    /**
     * repositorio de platos, via de comunicacion a bajo nivel
     */
    IPlatoRepositorio repositorio;
    /**
    * constructor parametrizado que hace inyeccion de dependencias
    * @param repositorio repositorio a la base de datos, tipo IPlatoRepositorio
    */
    public PlatoServicio(IPlatoRepositorio repositorio) {
        this.repositorio = repositorio;
    }
    
    /**
     * envia la solicitud a la capa de bajo nivel para guardar un plato del dia en la base de datos
     * @param plato instancia a guardar
     * @return 
     */
    public String savePlatoDia(PlatoDia plato){
        return repositorio.savePlatoDia(plato);
    }
    /**
     * envia la solicitud a la capa de bajo nivel para guardar un plato especial en la base de datos
     * @param plato instancia de plato especial a guardar
     * @return 
     */
    public String savePlatoEspecial(PlatoEspecial plato){
        return repositorio.savePlatoEspecial(plato);
    }
    /**
     * envia la solicitud a la capa de bajo nivel para guardar un restaurante en la base de datos
     * @param res instancia del restaurante a guardar
     * @return 
     */
    public String saveRestaurante(Restaurante res){
        return repositorio.saveRestaurante(res);
    }
    /**
     * envia la solicitud a la capa de bajo nivel para guardar un plato del dia en la base de datos
     * @param resId id del restaurante donde el plato del dia se va a guardar
     * @return 
     */
    public String listarMenuDia(int resId){
        return repositorio.listarMenuDia(resId);
    }
    /**
     * envia la solicitud a la capa de bajo nivel para guardar un plato especial en la base de datos
     * @param resId id del restaurante donde el plato especial se va a guardar
     * @return 
     */
    public String listarMenuEspecial(int resId){
        return repositorio.listarMenuEspecial(resId);
    }
}
