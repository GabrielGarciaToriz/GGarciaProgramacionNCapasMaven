package com.digis01.GGarciaProgramacionNCapasMaven.JPA;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Direccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int IdDireccion;
    @Column(name = "calle")
    private String Calle;
    @Column(name = "numeroexterior")
    private String NumeroExterior;
    @Column(name = "numerointerior")
    private String NumeroInterior;
    @Valid
    @ManyToOne
    @JoinColumn(name = "idcolonia")
    public Colonia colonia;
    @ManyToOne
    @JoinColumn(name = "idusuario")
    @JsonIgnore
    private Usuario usuario;
}
