import { limpiarEstilos, marcarExito, mostrarError } from "../Helpers/HelpersUI.js";
var regexTelefono = /^[\(]?[\+]?(\d{2}|\d{3})[\)]?[\s]?((\d{6}|\d{8})|(\d{3}[\*\.\-\s]){3}|(\d{2}[\*\.\-\s]){4}|(\d{4}[\*\.\-\s]){2})|\d{8}|\d{10}|\d{12}$/;
const msgError = "El telefono no tiene el formato correcto";
const msgCorrect = "El telefono es correcto"
export function validarTelefono(input, event) {
    var telefono = $(input).val() + event.key;
    if (regexTelefono.test(telefono)) {
        marcarExito(input, msgCorrect)
    } else {
        mostrarError(input, msgError)
    }
}

export function validarTelefonoBlur(input) {
    var telefono = $(input).val();
    if (telefono === "") {
        limpiarEstilos(input);
        return;
    }

    if (regexTelefono.test(telefono)) {
        marcarExito(input, msgCorrect)
    } else {
        mostrarError(input, msgError)
    }
}