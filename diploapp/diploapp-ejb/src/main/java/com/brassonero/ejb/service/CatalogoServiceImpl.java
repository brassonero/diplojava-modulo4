package com.brassonero.ejb.service;

import com.brassonero.ejb.model.GradoEnt;
import com.brassonero.ejb.model.MateriaEnt;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

import java.util.List;

@Stateless
public class CatalogoServiceImpl implements ICatalogoService {

    @Inject
    private IGradoService gradoService;

    @Inject
    private IMateriaService materiaService;

    @Override
    public List<GradoEnt> getCatalogoGrados() {
        return gradoService.listar();
    }

    @Override
    public List<MateriaEnt> getCatalogoMaterias() {
        return materiaService.listar();
    }
}
