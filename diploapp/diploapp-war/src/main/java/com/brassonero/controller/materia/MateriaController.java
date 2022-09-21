package com.brassonero.controller.materia;

import com.brassonero.ejb.model.MateriaEnt;
import com.brassonero.ejb.service.ICatalogoService;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpServletRequest;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.security.Principal;
import java.util.List;
import java.util.logging.Logger;

@Named(value = "materiaController")
@ViewScoped
public class MateriaController implements Serializable {

    private static final Logger logger = Logger.getLogger(MateriaController.class.getName());

    @Inject
    private ICatalogoService catalogosService;
    private List<MateriaEnt> listaMaterias;
    private String usuario;

    @PostConstruct
    public void init() {
        listaMaterias = catalogosService.getCatalogoMaterias();
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        Principal principal = request.getUserPrincipal();
        usuario = principal.getName();
    }

    public String nuevaMateria() {
        return "/prime/nuevaMateria?faces-redirect=true";
    }

    public List<MateriaEnt> getListaMaterias() {
        return listaMaterias;
    }

    public void setListaMaterias(List<MateriaEnt> listaMaterias) {
        this.listaMaterias = listaMaterias;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
}