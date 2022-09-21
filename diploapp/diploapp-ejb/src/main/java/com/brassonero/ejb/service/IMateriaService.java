package com.brassonero.ejb.service;

import com.brassonero.ejb.excepcion.SaveEntityException;
import com.brassonero.ejb.model.MateriaEnt;
import jakarta.ejb.Local;

import java.util.List;

@Local
public interface IMateriaService {

    List<MateriaEnt> listar();

    MateriaEnt crear(MateriaEnt entity) throws SaveEntityException;

    MateriaEnt actualiza(MateriaEnt entity);

    void borrar(MateriaEnt entity);

    MateriaEnt getMateria(Integer id);

    MateriaEnt getMateriaCompleta(Integer id);
}
