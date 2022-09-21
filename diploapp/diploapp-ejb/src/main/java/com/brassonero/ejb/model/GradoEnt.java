package com.brassonero.ejb.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "Grado")
public class GradoEnt implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;

    @Column(name = "nombre", length = 45)
    private String nombre;

    @OneToMany(mappedBy = "grado")
    private Set<MaestraEnt> maestras = new LinkedHashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Set<MaestraEnt> getMaestras() {
        return maestras;
    }

    public void setMaestras(Set<MaestraEnt> maestras) {
        this.maestras = maestras;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        GradoEnt otro = (GradoEnt) obj;
        return id.equals(otro.id) && nombre.equals(otro.nombre);
    }

    @Override
    public String toString() {
        return "GradoEnt{" + "id=" + id + ", nombre='" + nombre + '\'' + '}';
    }
}
