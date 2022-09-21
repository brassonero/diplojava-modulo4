package com.brassonero.ejb.service;

import com.brassonero.ejb.model.MaestraEnt;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

@Stateless
public class MaestraServiceImpl extends AbstractServiceFacade<MaestraEnt> implements IMaestraService {

    @PersistenceContext(name = "diploappDS")
    private EntityManager em;

    public MaestraServiceImpl() {
        super(MaestraEnt.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    public List<MaestraEnt> listar() {
        //Query qry = em.createQuery("from EmpleadoEnt", EmpleadoEnt.class);
        //return em.createQuery("from EmpleadoEnt", EmpleadoEnt.class).getResultList();
        //return qry.getResultList();
        String consulta = "select e from MaestraEnt e left join fetch e.grado";
        return em.createQuery(consulta, MaestraEnt.class).getResultList();
    }
}
