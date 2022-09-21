package com.brassonero.controller.jpa;

import com.brassonero.ejb.excepcion.SaveEntityException;
import com.brassonero.ejb.model.GradoEnt;
import com.brassonero.ejb.model.MaestraEnt;
import com.brassonero.ejb.service.ICatalogoService;
import com.brassonero.ejb.service.IMaestraService;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.primefaces.PrimeFaces;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Named(value = "listaMaestrasController")
@ViewScoped
public class ListaMaestrasController implements Serializable {
    private static final Logger logger = Logger.getLogger(ListaMaestrasController.class.getName());

    private MaestraEnt maestraSelect;
    private List<MaestraEnt> maestraLista;
    private List<GradoEnt> catGrados;

    @Inject
    private IMaestraService maestraSB;
    @Inject
    private ICatalogoService catalogoSB;

    @PostConstruct
    public void init() {
        logger.log(Level.INFO, "Inicia carga de Maestras");
        maestraLista = maestraSB.listar();
        catGrados = catalogoSB.getCatalogoGrados();
        maestraSelect = null;
    }

    public void nuevaMaestra() {
        maestraSelect = new MaestraEnt();
    }

    public void salvaMaestra() {
        logger.log(Level.INFO, "Salvando maestra");

        try {
            if (maestraSelect.getId() == null) {
                maestraSB.crear(maestraSelect);
                FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Maestra agregada",
                        "Maestra agregada");
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, mensaje);
            } else {
                maestraSB.actualiza(maestraSelect);
                FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Maestra actualizada",
                        "Maestra actualizada");
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, mensaje);
            }
        } catch (SaveEntityException ex) {
            FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Imposible guardar maestra",
                    ex.getMessage());
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, mensaje);
            PrimeFaces.current().executeInitScript("PF('maestraDialogo').hide()");
            PrimeFaces.current().ajax().update("formListaMaestras:mensajes", "formListaMaestras:dtMaestras");
            maestraSelect = null;
            return;
        }

        init();

        PrimeFaces.current().executeInitScript("PF('maestraDialogo').hide()");
        PrimeFaces.current().ajax().update("formListaMaestras:mensajes", "formListaMaestras:dtMaestras");
    }

    public void borrarMaestra() {
        maestraSB.borrar(maestraSelect);
        init();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Registro maestra eliminada"));
        PrimeFaces.current().ajax().update("formListaMaestras:mensajes", "formListaMaestras:dtMaestras");
    }

    public List<MaestraEnt> getMaestraLista() {
        return maestraLista;
    }

    public void setMaestraLista(List<MaestraEnt> maestraLista) {
        this.maestraLista = maestraLista;
    }

    public MaestraEnt getMaestraSelect() {
        return maestraSelect;
    }

    public void setMaestraSelect(MaestraEnt maestraSelect) {
        this.maestraSelect = maestraSelect;
    }

    public IMaestraService getMaestraSB() {
        return maestraSB;
    }

    public void setMaestraSB(IMaestraService maestraSB) {
        this.maestraSB = maestraSB;
    }

    public List<GradoEnt> getCatGrados() {
        return catGrados;
    }

    public void setCatGrados(List<GradoEnt> catGrados) {
        this.catGrados = catGrados;
    }

    public ICatalogoService getCatalogoSB() {
        return catalogoSB;
    }

    public void setCatalogoSB(ICatalogoService catalogoSB) {
        this.catalogoSB = catalogoSB;
    }
}
