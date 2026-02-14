import { limpiarEstilos, marcarExito, mostrarError } from "../Helpers/HelpersUI.js";
var regexCelular = /^[\(]?[\+]?(\d{2}|\d{3})[\)]?[\s]?((\d{6}|\d{8})|(\d{3}[\*\.\-\s]){3}|(\d{2}[\*\.\-\s]){4}|(\d{4}[\*\.\-\s]){2})|\d{8}|\d{10}|\d{12}$/;
const msgError = "El numero no tiene el formato correcto";
const msgCorrect = "El numero es correcto"
export function validarCelular(input, event) {
    var celular = $(input).val() + event.key;
    if (regexCelular.test(celular)) {
        marcarExito(input, msgCorrect)
    } else {
        mostrarError(input, msgError)
    }
}

export function validarCelularBlur(input) {
    var celular = $(input).val();
    if (celular === "") {
        limpiarEstilos(input);
        return;
    }

    if (regexCelular.test(celular)) {
        marcarExito(input, msgCorrect)
    } else {
        mostrarError(input, msgError)
    }
}