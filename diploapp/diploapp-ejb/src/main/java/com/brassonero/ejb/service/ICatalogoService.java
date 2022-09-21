package com.brassonero.ejb.service;

import com.brassonero.ejb.model.GradoEnt;
import com.brassonero.ejb.model.MateriaEnt;
import jakarta.ejb.Local;

import java.util.List;

@Local
public interface ICatalogoService {

    List<GradoEnt> getCatalogoGrados();

    List<MateriaEnt> getCatalogoMaterias();

}
