package com.brassonero.controller.jpa;

import com.brassonero.ejb.excepcion.SaveEntityException;
import com.brassonero.ejb.model.GradoEnt;
import com.brassonero.ejb.model.MaestraEnt;
import com.brassonero.ejb.service.ICatalogoService;
import com.brassonero.ejb.service.IGradoService;
import com.brassonero.ejb.service.IMaestraService;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Named(value = "altaMaestraController")
@ViewScoped
public class AltaMaestraController implements Serializable {
    private static final Logger logger = Logger.getLogger(AltaMaestraController.class.getName());

    private MaestraEnt nuevo;
    private Integer idGrado;
    private List<GradoEnt> catGrados;

    @Inject
    private IMaestraService maestraSB;
    @Inject
    private IGradoService gradoSB;
    @Inject
    private ICatalogoService catalogoSB;

    @PostConstruct
    public void inicia() {
        nuevo = new MaestraEnt();
        catGrados = catalogoSB.getCatalogoGrados();
    }

    public void salvar() {
        logger.log(Level.INFO, "valor idGrado : {0}", idGrado);
        GradoEnt grado = gradoSB.getGrado(idGrado);
        nuevo.setGrado(grado);
        try {
            maestraSB.crear(nuevo);
        } catch (SaveEntityException ex) {
            FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Imposible guardar maestra",
                    ex.getMessage());
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, mensaje);
            return;
        }
        FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO,
                "Maestra guardada",
                "Maestra guardada");
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, mensaje);
    }

    public MaestraEnt getNuevo() {
        return nuevo;
    }

    public void setNuevo(MaestraEnt nuevo) {
        this.nuevo = nuevo;
    }

    public Integer getIdGrado() {
        return idGrado;
    }

    public void setIdGrado(Integer idGrado) {
        this.idGrado = idGrado;
    }

    public List<GradoEnt> getCatGrados() {
        return catGrados;
    }
}