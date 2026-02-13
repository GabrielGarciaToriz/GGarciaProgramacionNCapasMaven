import { SoloLetras, SoloLetrasBlur } from "./Validaciones/Letras.js";
import { PaisEstado } from "./Selects/PaisEstado.js";
import { EstadoMunicipio } from "./Selects/EstadoMunicipio.js";
import { MunicipioColonia } from "./Selects/MunicipioColonia.js";
import { validarCorreo, validarCorreoBlur } from "./Validaciones/Correo.js";
import { validarPassword, validarPasswordBlur } from "./Validaciones/Password.js";
import { validarCurp, validarCurpBlur } from "./Validaciones/Curp.js";
import { validarCelular, validarCelularBlur } from "./Validaciones/Celular.js";

const inicializarSelectores = () => {
    PaisEstado();
    EstadoMunicipio();
    MunicipioColonia();
}

const reglasValidacion = [
    { selector: ".validar-letras", evento: "keypress", accion: SoloLetras },
    { selector: ".validar-letras-blur", evento: "blur", accion: SoloLetrasBlur },
    { selector: ".validar-correo", evento: "keypress", accion: validarCorreo },
    { selector: ".validar-correo-blur", evento: "blur", accion: validarCorreoBlur },
    { selector: ".validar-password", evento: "keypress", accion: validarPassword },
    { selector: ".validar-password-blur", evento: "blur", accion: validarPasswordBlur },
    { selector: ".validar-curp", evento: "keypress", accion: validarCurp },
    { selector: ".validar-curp-blur", evento: "blur", accion: validarCurpBlur },
    { selector: ".validar-celular", evento: "keypress", accion: validarCelular },
    { selector: ".validar-celular-blur", evento: "blur", accion: validarCelularBlur }
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
