package com.brassonero.ejb.service;

import com.brassonero.ejb.model.GradoEnt;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

import java.util.List;

@Stateless
public class GradoServiceImpl extends AbstractServiceFacade<GradoEnt> implements IGradoService {

    @PersistenceContext(name = "diploappDS")
    private EntityManager em;

    public GradoServiceImpl() {
        super(GradoEnt.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    public List<GradoEnt> listar() {
        Query qry = em.createQuery("from GradoEnt", GradoEnt.class);
        //return em.createQuery("from DepartamentoEnt", DepartamentoEnt.class).getResultList();
        return qry.getResultList();
    }

    @Override
    public GradoEnt getGrado(Integer id) {
        return em.find(GradoEnt.class, id);
    }
}
