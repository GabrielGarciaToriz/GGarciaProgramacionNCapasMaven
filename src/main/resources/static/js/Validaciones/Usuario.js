import { limpiarEstilos, marcarExito, mostrarError } from "../Helpers/HelpersUI.js";
//EXPRESION REGULAR PARA SOLO USAR LETRAS
const regexLetras = /^[a-zA-Z0-9]+$/
const msgCorrect = "Validado";
const msgError = "Usuario no valido";
export function Usuario(input, event) {
    var valorCompleto = $(input).val() + event.key;
    //EXPRESON REGULAR PARA SOLO ACEPTAR CARACTERES Y ESPACIOS
    if (!regexLetras.test(valorCompleto)) {
        event.preventDefault();
        marcarExito(input, msgCorrect)
    } else {
        limpiarEstilos(input);
    }
}

export function UsuarioBlur(input) {
    const valor = $(input).val();
    if (valor === "") {
        limpiarEstilos(input);
        return;
    }
    if (regexLetras.test(valor)) {
        marcarExito(input, msgCorrect)
    } else {
        mostrarError(input, msgError)
    }
}