package com.digis01.GGarciaProgramacionNCapasMaven.JPA;

import jakarta.persistence.*;
import com.digis01.GGarciaProgramacionNCapasMaven.JPA.Rol;
import com.digis01.GGarciaProgramacionNCapasMaven.JPA.Direccion;
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
    private int IdUsuario;
    private String Nombre;
    private String ApellidoPaterno;
    private String ApellidoMaterno;
    private String Celular;
    private String Curp;
    private String UserName;
    private String Email;
    private String Password;
    private String Sexo;
    private String Telefono;
    private String FechaNacimineot;
    @Valid
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idrol")
    public Rol Rol;
    @Valid
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    public List<Direccion> Direcciones;
    private int Estatus;
}



