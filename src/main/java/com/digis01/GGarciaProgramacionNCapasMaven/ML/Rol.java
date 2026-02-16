package com.digis01.GGarciaProgramacionNCapasMaven.ML;

import jakarta.validation.constraints.NotBlank;

public class Rol {

    private int IdRol;
    @NotBlank(message = "Este campo es obligatorio")
    private String Nombre;

    public Rol() {

    }

    /*GETTERS*/
    public int getIdRol() {
        return IdRol;
    }

    public String getNombre() {
        return Nombre;
    }

    /*SETTERS*/
    public void setIdRol(int IdRol) {
        this.IdRol = IdRol;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

}
