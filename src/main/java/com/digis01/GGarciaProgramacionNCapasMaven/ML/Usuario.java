package com.digis01.GGarciaProgramacionNCapasMaven.ML;

import java.util.Date;
import java.util.List;

public class Usuario {

    private int IdUsuario;
    private String Nombre, ApellidoPaterno, ApellidoMaterno, Celular, Curp, UserName, Email, Password, Sexo, Telefono;
    private Date FechaNacimiento;
    public Rol Rol;
    public List<Direccion> Direcciones;

    public Usuario() {
    }

    public Usuario(int IdUsuario, String Nombre, String ApellidoPaterno, String ApellidoMaterno, String Celular, String Curp, String UserName, String Email, String Password, String Sexo, String Telefono, Date FechaNacimiento, Rol Rol, List<Direccion> Direcciones) {
        this.IdUsuario = IdUsuario;
        this.Nombre = Nombre;
        this.ApellidoPaterno = ApellidoPaterno;
        this.ApellidoMaterno = ApellidoMaterno;
        this.Celular = Celular;
        this.Curp = Curp;
        this.UserName = UserName;
        this.Email = Email;
        this.Password = Password;
        this.Sexo = Sexo;
        this.Telefono = Telefono;
        this.FechaNacimiento = FechaNacimiento;
        this.Rol = Rol;
        this.Direcciones = Direcciones;
    }

  


    /*SETTERS*/
    public void setIdUsuario(int IdUsuario) {
        this.IdUsuario = IdUsuario;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public void setApellidoPaterno(String ApellidoPaterno) {
        this.ApellidoPaterno = ApellidoPaterno;
    }

    public void setApellidoMaterno(String ApellidoMaterno) {
        this.ApellidoMaterno = ApellidoMaterno;
    }

    public void setCelular(String Celular) {
        this.Celular = Celular;
    }

    public void setCurp(String Curp) {
        this.Curp = Curp;
    }

    public void setUserName(String UserName) {
        this.UserName = UserName;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public void setSexo(String Sexo) {
        this.Sexo = Sexo;
    }

    public void setTelefono(String Telefono) {
        this.Telefono = Telefono;
    }

    public void setFechaNacimiento(Date FechaNacimiento) {
        this.FechaNacimiento = FechaNacimiento;
    }

    public void setDirecciones(List<Direccion> Direcciones) {
        this.Direcciones = Direcciones;
    }

    /*GETTERS*/
    public int getIdUsuario() {
        return IdUsuario;
    }

    public String getNombre() {
        return Nombre;
    }

    public String getApellidoPaterno() {
        return ApellidoPaterno;
    }

    public String getApellidoMaterno() {
        return ApellidoMaterno;
    }

    public String getCelular() {
        return Celular;
    }

    public String getCurp() {
        return Curp;
    }

    public String getUserName() {
        return UserName;
    }

    public String getEmail() {
        return Email;
    }

    public String getPassword() {
        return Password;
    }

    public String getSexo() {
        return Sexo;
    }

    public String getTelefono() {
        return Telefono;
    }

    public Date getFechaNacimiento() {
        return FechaNacimiento;
    }

    public void setRol(Rol Rol) {
        this.Rol = Rol;
    }

    public Rol getRol() {
        return Rol;
    }

    public List<Direccion> getDirecciones() {
        return Direcciones;
    }

}
