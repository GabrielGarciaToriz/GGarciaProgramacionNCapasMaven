import { SoloLetras, SoloLetrasBlur } from "./Validaciones/SoloLetras.js";
import { PaisEstado } from "./Selects/PaisEstado.js";
import { EstadoMunicipio } from "./Selects/EstadoMunicipio.js";
import { MunicipioColonia } from "./Selects/MunicipioColonia.js";

$(document).ready(function () {
    PaisEstado();
    EstadoMunicipio();
    MunicipioColonia();
    $(".validar-letras").on("keypress", function (event) {
        SoloLetras(this, event);
    });
    $(".validar-letras-blur").on("blur", function () {
        SoloLetrasBlur(this);
    });
    $(".validar-correo").on("keypress", function (event) {
        validarCorreo(this, event);
    });
    $(".validar-correo-blur").on("blur", function () {
        validarCorreoBlur(this);
    });
});
