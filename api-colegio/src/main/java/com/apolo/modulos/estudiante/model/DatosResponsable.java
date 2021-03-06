package com.apolo.modulos.estudiante.model;

import com.apolo.modulos.usuarios.model.Persona;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;

import javax.persistence.*;
import java.util.List;

@Entity
@ApiModel(description = "responsable de la matricula del estudiante")
public class DatosResponsable extends Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String nombreCompleto;

    private String numeroFijo;

    private String direccion;

    private String ocupacion;

    private String correo;

    private Boolean esPadre;

    private Boolean  esAcudiente;


    @OneToOne(cascade = {CascadeType.ALL})
    @JsonIgnoreProperties()
    private Matricula matricula;

    @OneToMany(cascade = {CascadeType.ALL})
    @JsonIgnoreProperties()
    private List<Estudiante> estudiante;

    public DatosResponsable() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getNumeroFijo() {
        return numeroFijo;
    }

    public void setNumeroFijo(String numeroFijo) {
        this.numeroFijo = numeroFijo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getOcupacion() {
        return ocupacion;
    }

    public void setOcupacion(String ocupacion) {
        this.ocupacion = ocupacion;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public boolean isEsPadre() {
        return esPadre;
    }

    public void setEsPadre(boolean esPadre) {
        this.esPadre = esPadre;
    }

    public boolean isEsAcudiente() {
        return esAcudiente;
    }

    public void setEsAcudiente(boolean esAcudiente) {
        this.esAcudiente = esAcudiente;
    }

    public Matricula getMatricula() {
        return matricula;
    }

    public void setMatricula(Matricula matricula) {
        this.matricula = matricula;
    }

    public Boolean getEsPadre() {
        return esPadre;
    }

    public void setEsPadre(Boolean esPadre) {
        this.esPadre = esPadre;
    }

    public Boolean getEsAcudiente() {
        return esAcudiente;
    }

    public void setEsAcudiente(Boolean esAcudiente) {
        this.esAcudiente = esAcudiente;
    }

    public List<Estudiante> getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(List<Estudiante> estudiante) {
        this.estudiante = estudiante;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("DatosResponsable{");
        sb.append("id=").append(id);
        sb.append(", nombreCompleto='").append(nombreCompleto).append('\'');
        sb.append(", numeroFijo='").append(numeroFijo).append('\'');
        sb.append(", direccion='").append(direccion).append('\'');
        sb.append(", ocupacion='").append(ocupacion).append('\'');
        sb.append(", correo='").append(correo).append('\'');
        sb.append(", esPadre=").append(esPadre);
        sb.append(", esAcudiente=").append(esAcudiente);
        sb.append(", matricula=").append(matricula);
        sb.append(", estudiante=").append(estudiante);
        sb.append('}');
        return sb.toString();
    }
}
