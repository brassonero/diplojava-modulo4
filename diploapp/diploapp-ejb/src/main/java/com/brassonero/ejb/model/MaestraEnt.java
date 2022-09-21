package com.brassonero.ejb.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "Maestra")
public class MaestraEnt implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;

    @Column(name = "nombre", nullable = false, length = 45)
    private String nombre;
    @Column(name = "salario", nullable = false)
    private Double salario;
    @Column(name = "correo", length = 45)
    private String correo;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "grado")
    private GradoEnt grado;

    @ManyToMany
    @JoinTable(name = "MaestrasMateria",
            joinColumns = @JoinColumn(name = "idMaestra"),
            inverseJoinColumns = @JoinColumn(name = "idMateria")
    )

    private Set<MateriaEnt> materias = new LinkedHashSet<>();

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

    public Double getSalario() {
        return salario;
    }

    public void setSalario(Double salario) {
        this.salario = salario;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public GradoEnt getGrado() {
        return grado;
    }

    public void setGrado(GradoEnt grado) {
        this.grado = grado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MaestraEnt that = (MaestraEnt) o;
        return id.equals(that.id) && nombre.equals(that.nombre) && salario.equals(that.salario) && Objects.equals(correo, that.correo) && Objects.equals(grado, that.grado);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, salario, correo, grado);
    }

    @Override
    public String toString() {
        return "MaestraEnt{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", salario=" + salario +
                ", correo='" + correo + '\'' +
                ", grado=" + grado +
                '}';
    }
}