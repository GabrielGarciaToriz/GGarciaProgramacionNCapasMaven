import { limpiarEstilos, marcarExito, mostrarError } from "../Helpers/HelpersUI.js";
const regexPassword = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,16}$/;

export function validarPassword(input, event) {
    var password = $(input).val().trim() + event.key;
    if (regexPassword.test(password)) {
        marcarExito(input)
    } else {
        mostrarError(input)
    }
}

export function validarPasswordBlur(input) {
    var password = $(input).val().trim()
    if (password === "") {
        limpiarEstilos(input)
    }
    if (regexPassword.test(password)) {
        marcarExito(input)
    } else {
        mostrarError(input)
    }
}
