package com.brassonero.controller.comun;

import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

@Named(value = "sesionController")
@SessionScoped
public class SesionController implements Serializable {

    private static final Logger logger = Logger.getLogger(SesionController.class.getName());

    public SesionController() {
        logger.log(Level.INFO, ">>>> Creando el Session Controller <<<<");
    }

    public String cerrarSesion() {
        logger.log(Level.INFO, ">>>> Cerrando la session <<<<");
        ExternalContext contexto = FacesContext.getCurrentInstance().getExternalContext();
        HttpSession sesion = (HttpSession) contexto.getSession(false);
        sesion.invalidate();
        try {
            contexto.redirect("/diploapp");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
