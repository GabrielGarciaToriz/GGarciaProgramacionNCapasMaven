/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.digis01.GGarciaProgramacionNCapasMaven.ML;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 *
 * @author ALIEN59
 */
public class Direccion {

    private int IdDireccion;
    @NotBlank(message = "Este campo es obligatorio")
    private String NumeroInterior;
    @NotBlank(message = "Este campo es obligatorio")
    private String NumeroExterior;
    @NotBlank(message = "Este campo es obligatorio")
    private String Calle;
    @NotNull(message = "Este campo es obligatorio")
    @Valid
    public Colonia Colonia;

    public int getIdDireccion() {
        return IdDireccion;
    }

    public void setIdDireccion(int IdDireccion) {
        this.IdDireccion = IdDireccion;
    }
    

    public void setCalle(String Calle) {
        this.Calle = Calle;
    }

    public String getCalle() {
        return Calle;
    }

    public void setIdColonia(int IdColonia) {
        this.IdDireccion = IdColonia;
    }

    public void setNumeroInterior(String NumeroInterior) {
        this.NumeroInterior = NumeroInterior;
    }

    public void setNumeroExterior(String NumeroExterior) {
        this.NumeroExterior = NumeroExterior;
    }

    public int IdColonia() {
        return IdDireccion;
    }

    public String getNumeroInterior() {
        return NumeroInterior;
    }

    public String getNumeroExterior() {
        return NumeroExterior;
    }

    public Colonia getColonia() {
        return Colonia;
    }

    public void setColonia(Colonia Colonia) {
        this.Colonia = Colonia;
    }

}
