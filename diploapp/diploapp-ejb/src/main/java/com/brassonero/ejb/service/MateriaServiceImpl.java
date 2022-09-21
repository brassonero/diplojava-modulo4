package com.brassonero.ejb.service;

import com.brassonero.ejb.model.MaestraEnt;
import com.brassonero.ejb.model.MateriaEnt;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

import java.util.List;

@Stateless
public class MateriaServiceImpl extends AbstractServiceFacade<MateriaEnt> implements IMateriaService {
    @PersistenceContext(name = "diploappDS")
    private EntityManager em;

    public MateriaServiceImpl() {
        super(MateriaEnt.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    public List<MateriaEnt> listar() {
        Query qry = em.createQuery("from MateriaEnt", MateriaEnt.class);
        //return em.createQuery("from MateriaEnt", MateriaEnt.class).getResultList();
        return qry.getResultList();
    }

    @Override
    public MateriaEnt getMateria(Integer id) {
        return em.find(MateriaEnt.class, id);
    }

    @Override
    public MateriaEnt getMateriaCompleta(Integer id) {
        MateriaEnt materia = getMateria(id);

        for (MaestraEnt e : materia.getMaestras()) {
            e.getId();
            e.getNombre();
        }
        return materia;
    }
}