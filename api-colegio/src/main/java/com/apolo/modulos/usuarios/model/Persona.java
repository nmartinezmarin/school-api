package com.apolo.modulos.usuarios.model;

import com.apolo.modulos.usuarios.enums.TipoDocumento;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class Persona {


    private String primerNombre;

    private String segundoNombre;

    private String primerApellido;

    private String segundoApellido;

    private TipoDocumento tipoDocumento;

    private String documento;

    private String numeroCelular;


    public Persona() {

    }
    

    public String getNombrecompleto() {

        return String.format("%s %s %s %s", primerNombre, segundoNombre, primerApellido, segundoApellido);
    }

    public String getPrimerNombre() {
        return primerNombre;
    }

    public void setPrimerNombre(String primerNombre) {
        this.primerNombre = primerNombre;
    }

    public String getSegundoNombre() {
        return segundoNombre;
    }

    public void setSegundoNombre(String segundoNombre) {
        this.segundoNombre = segundoNombre;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    public TipoDocumento getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(TipoDocumento tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getNumeroCelular() {
        return numeroCelular;
    }

    public void setNumeroCelular(String numeroCelular) {
        this.numeroCelular = numeroCelular;
    }
}
