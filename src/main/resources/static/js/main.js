import { SoloLetras, SoloLetrasBlur } from "./Validaciones/Letras.js";
import { PaisEstado } from "./Selects/PaisEstado.js";
import { EstadoMunicipio } from "./Selects/EstadoMunicipio.js";
import { MunicipioColonia } from "./Selects/MunicipioColonia.js";
import { ValidarCorreo, ValidarCorreoBlur } from "./Validaciones/Correo.js";
import { validarPassword, validarPasswordBlur } from "./Validaciones/Password.js";

const inicializarSelectores = () => {
    PaisEstado();
    EstadoMunicipio();
    MunicipioColonia();
}

const reglasValidacion = [
    { selector: ".validar-letras", evento: "keypress", accion: SoloLetras },
    { selector: ".validar-letras-blur", evento: "blur", accion: SoloLetrasBlur },
    { selector: ".validar-correo", evento: "keypress", accion: ValidarCorreo },
    { selector: ".validar-correo-blur", evento: "blur", accion: ValidarCorreoBlur },
    { selector: ".validar-password", evento: "keypress", accion: validarPassword },
    { selector: ".validar-password-blur", evento: "blur", accion: validarPasswordBlur }
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
