package com.brassonero.controller.materia;

import com.brassonero.ejb.model.MaestraEnt;
import com.brassonero.ejb.model.MateriaEnt;
import com.brassonero.ejb.service.IMateriaService;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Named(value = "listaMaeMateriaController")
@ViewScoped
public class ListaMaeMateriaController implements Serializable {
    private static final Logger logger = Logger.getLogger(ListaMaeMateriaController.class.getName());

    @Inject
    private IMateriaService materiaService;

    private String idMateria;
    private MateriaEnt matActual;
    private List<MaestraEnt> listaMaestras;
    private MaestraEnt maestraSelect;

    public void cargaDatosMateria() {
        logger.log(Level.INFO, ">>>> Id que vamos a buscar : {0} <<<<", idMateria);
        Integer id = Integer.valueOf(idMateria);
        matActual = materiaService.getMateriaCompleta(id);
        logger.log(Level.INFO, "---> Id : {0}", matActual.getId());
        logger.log(Level.INFO, "---> Nombre : {0}", matActual.getNombre());
        logger.log(Level.INFO, "---> Maestras : {0}", matActual.getMaestras().size());

        for (MaestraEnt e : matActual.getMaestras()) {
            logger.log(Level.INFO, "---> Id : {0}", e.getId());
            logger.log(Level.INFO, "---> Nombre : {0}", e.getNombre());
            logger.log(Level.INFO, "---> Salario : {0}", e.getSalario());
        }
        /*
            Java 17
            proyActual.getEmpleados().stream().toList();
        */
        listaMaestras = matActual.getMaestras().stream().collect(Collectors.toList());
    }

    public void removerMaestra() {
        logger.log(Level.INFO, ">>>> Empleado a remover : {0} <<<<", maestraSelect.getNombre());
        matActual.getMaestras().remove(maestraSelect);
        materiaService.actualiza(matActual);
        cargaDatosMateria();
    }

    public IMateriaService getMateriaService() {
        return materiaService;
    }

    public void setMateriaService(IMateriaService materiaService) {
        this.materiaService = materiaService;
    }

    public String getIdMateria() {
        return idMateria;
    }

    public void setIdMateria(String idMateria) {
        this.idMateria = idMateria;
    }

    public MateriaEnt getMatActual() {
        return matActual;
    }

    public void setMatActual(MateriaEnt matActual) {
        this.matActual = matActual;
    }

    public List<MaestraEnt> getListaMaestras() {
        return listaMaestras;
    }

    public void setListaMaestras(List<MaestraEnt> listaMaestras) {
        this.listaMaestras = listaMaestras;
    }

    public MaestraEnt getMaestraSelect() {
        return maestraSelect;
    }

    public void setMaestraSelect(MaestraEnt maestraSelect) {
        this.maestraSelect = maestraSelect;
    }
}