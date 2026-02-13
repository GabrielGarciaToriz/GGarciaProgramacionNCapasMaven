import { limpiarEstilos, marcarExito, mostrarError } from "../Helpers/HelpersUI.js";
//EXPRESION REFULAR PARA VALIDAR EL CORREO
const regexCorreo = /[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/;


export function validarCorreo(input, event) {
    var correo = $(input).val().trim().toLowerCase() + event.key;
    if (regexCorreo.test(correo)) {
        marcarExito(input)
    } else {
        mostrarError(input)
    }

}

export function validarCorreoBlur(input) {
    var correo = $(input).val().trim().toLowerCase();
    if (correo === "") {
        limpiarEstilos(input);
        return;
    }
    if (regexCorreo.test(correo)) {
        marcarExito(input)
    } else {
        mostrarError(input)
    }

}