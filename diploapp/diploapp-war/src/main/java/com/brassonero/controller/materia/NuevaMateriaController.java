package com.brassonero.controller.materia;

import com.brassonero.ejb.model.MateriaEnt;
import com.brassonero.ejb.service.IMateriaService;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

@Named(value = "nuevaMateriaController")
@ViewScoped
public class NuevaMateriaController implements Serializable {

    private static final Logger logger = Logger.getLogger(NuevaMateriaController.class.getName());

    @Inject
    private IMateriaService materiaService;

    private MateriaEnt nuevo;

    @PostConstruct
    public void init() {
        nuevo = new MateriaEnt();
    }

    public String guardaMateria() {
        try {
            logger.log(Level.INFO, "---> Id antes de guardar : {0}", nuevo.getId());
            materiaService.crear(nuevo);
        } catch (Exception e) {

        }
        logger.log(Level.INFO, "---> Id materia nueva asignada : {0}", nuevo.getId());
        return "/prime/listaMaterias?faces-redirect=true";

    }

    public MateriaEnt getNuevo() {
        return nuevo;
    }

    public void setNuevo(MateriaEnt nuevo) {
        this.nuevo = nuevo;
    }
}