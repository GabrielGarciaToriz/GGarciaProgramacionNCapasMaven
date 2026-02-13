package com.digis01.GGarciaProgramacionNCapasMaven.ML;

public class Colonia {

    private int IdColonia;
    private String Nombre, CodigoPostal;
    public Municipio Municipio;

    public void setIdColonia(int IdColonia) {
        this.IdColonia = IdColonia;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public void setCodigoPostal(String CodigoPostal) {
        this.CodigoPostal = CodigoPostal;
    }

    public int getIdColonia() {
        return IdColonia;
    }

    public String getNombre() {
        return Nombre;
    }

    public String getCodigoPostal() {
        return CodigoPostal;
    }

    public Municipio getMunicipio() {
        return Municipio;
    }

    public void setMunicipio(Municipio Municipio) {
        this.Municipio = Municipio;
    }
}
