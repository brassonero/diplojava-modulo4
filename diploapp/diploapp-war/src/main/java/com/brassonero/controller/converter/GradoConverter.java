package com.brassonero.controller.converter;

import com.brassonero.ejb.model.GradoEnt;
import com.brassonero.ejb.service.IGradoService;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.ConverterException;
import jakarta.faces.convert.FacesConverter;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.util.logging.Level;
import java.util.logging.Logger;

@Named
@FacesConverter(value = "gradoConverter", forClass = GradoEnt.class, managed = true)
public class GradoConverter implements Converter<GradoEnt> {
    private static final Logger logger = Logger.getLogger(GradoConverter.class.getName());

    @Inject
    private IGradoService gradoSB;

    @Override
    public GradoEnt getAsObject(FacesContext context, UIComponent component, String valor) {
        logger.log(Level.INFO, "Valor : {0}", valor);
        if (valor != null && valor.trim().length() > 0) {
            try {
                GradoEnt grado = gradoSB.getGrado(Integer.parseInt(valor));
                return grado;
            } catch (NumberFormatException e) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de conversion", "Error de conversion"));
            }
        } else {
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, GradoEnt grado) {
        if (grado != null) {
            return String.valueOf(grado.getId());
        } else {
            return "";
        }
    }
}
