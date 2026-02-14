import { limpiarEstilos, marcarExito, mostrarError } from "../Helpers/HelpersUI.js";
const regexPassword = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&.])[A-Za-z\d@$!%*?&.]{8,16}$/;
const msgError = "La contraseña no cumple los parametros";
const msgCorrect = "La contraseña cumple los parametros";
export function validarPassword(input, event) {
    var password = $(input).val().trim() + event.key;
    if (regexPassword.test(password)) {
        marcarExito(input, msgCorrect)
    } else {
        mostrarError(input, msgError)
    }
}

export function validarPasswordBlur(input) {
    var password = $(input).val().trim()
    if (password === "") {
        limpiarEstilos(input)
    }
    if (regexPassword.test(password)) {
        marcarExito(input, msgCorrect)
    } else {
        mostrarError(input, msgError)
    }
}
