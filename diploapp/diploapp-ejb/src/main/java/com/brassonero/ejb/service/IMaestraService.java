package com.brassonero.ejb.service;

import com.brassonero.ejb.excepcion.SaveEntityException;
import com.brassonero.ejb.model.MaestraEnt;
import jakarta.ejb.Local;

import java.util.List;

@Local
public interface IMaestraService {

    List<MaestraEnt> listar();

    MaestraEnt crear(MaestraEnt entity) throws SaveEntityException;

    MaestraEnt actualiza(MaestraEnt entity);

    void borrar(MaestraEnt entity);
}
