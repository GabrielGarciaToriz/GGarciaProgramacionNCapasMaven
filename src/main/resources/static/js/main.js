import { SoloLetras, SoloLetrasBlur } from "./Validaciones/SoloLetras.js";
import { PaisEstado } from "./Selects/PaisEstado.js";
import { EstadoMunicipio } from "./Selects/EstadoMunicipio.js";
import { MunicipioColonia } from "./Selects/MunicipioColonia.js";
import { ValidarCorreo, ValidarCorreoBlur } from "./Validaciones/Correo.js";

const inicializarSelectores = () => {
    PaisEstado();
    EstadoMunicipio();
    MunicipioColonia();
}

const reglasValidacion = [
    { selector: ".validar-letras", evento: "keypress", accion: SoloLetras },
    { selector: ".validar-letras-blur", evento: "blur", accion: SoloLetrasBlur },
    { selector: ".validar-correo", evento: "keypress", accion: ValidarCorreo },
    { selector: ".validar-correo-blur", evento: "blur", accion: ValidarCorreoBlur }

]

const aplicarValidaciones = () => {
    reglasValidacion.forEach(({ selector, evento, accion }) => {
        $(selector).on(evento, function (event) {
            accion(this, event);
        });
    });
}

$(document).ready(function () {
    inicializarSelectores();
    aplicarValidaciones();
});
