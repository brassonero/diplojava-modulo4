package com.brassonero.ejb.service;

import com.brassonero.ejb.excepcion.SaveEntityException;
import com.brassonero.ejb.model.GradoEnt;
import jakarta.ejb.Local;

import java.util.List;

@Local
public interface IGradoService {

    List<GradoEnt> listar();

    GradoEnt crear(GradoEnt entity) throws SaveEntityException;

    GradoEnt actualiza(GradoEnt entity);

    GradoEnt getGrado(Integer id);

    void borrar(GradoEnt entity);
}
