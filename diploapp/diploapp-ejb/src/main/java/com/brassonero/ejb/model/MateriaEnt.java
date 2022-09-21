package com.brassonero.ejb.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "Materia")
public class MateriaEnt implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;

    @Column(name = "nombre", length = 45)
    private String nombre;

    @ManyToMany
    @JoinTable(name = "MaestrasMateria",
            joinColumns = @JoinColumn(name = "idMateria"),
            inverseJoinColumns = @JoinColumn(name = "idMaestra")
    )
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MateriaEnt that = (MateriaEnt) o;
        return id.equals(that.id) && nombre.equals(that.nombre) && Objects.equals(maestras, that.maestras);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, maestras);
    }

    @Override
    public String toString() {
        return "MateriaEnt{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", maestras=" + maestras +
                '}';
    }
}