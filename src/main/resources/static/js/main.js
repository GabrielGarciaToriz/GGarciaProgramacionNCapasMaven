import { PaisEstado, EstadoMunicipio, MunicipioColonia } from "./Selects.js";
import { SoloLetras, SoloLetrasBlur } from "./validaciones.js";

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
});
