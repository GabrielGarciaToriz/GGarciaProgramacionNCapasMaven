package com.digis01.GGarciaProgramacionNCapasMaven.JPA;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int IdUsuario;
    @Column(name = "nombre")
    private String Nombre;
    @Column(name = "apellidopaterno")
    private String ApellidoPaterno;
    @Column(name = "apellidomaterno")
    private String ApellidoMaterno;
    @Column(name = "celular")
    private String Celular;
    @Column(name = "curp")
    private String Curp;
    @Column(name = "username")
    private String UserName;
    @Column(name = "email")
    private String Email;
    @Column(name = "password")
    private String Password;
    @Column(name = "sexo")
    private String Sexo;
    @Column(name = "telefono")
    private String Telefono;
    @Column(name = "fechanacimiento")
    private String FechaNacimiento;
    @Valid
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idrol")
    public Rol Rol;
    @Valid
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    public List<Direccion> Direcciones;
    private int Estatus;
}
