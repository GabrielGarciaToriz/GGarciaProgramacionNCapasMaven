import { limpiarEstilos, marcarExito, mostrarError } from "../Helpers/HelpersUI.js";
const regexCelular = /^(\+\d{1,3}\s?)?(\(?\d{2,3}\)?[\s.-]?)?\d{3,4}[\s.-]?\d{4}$/;
const msgError = "Formato no valido (Ej. 5668592402)";
const msgCorrect = "El numero es correcto"
export function validarCelular(input, event) {
    const tecla = event.key;
    const regexTeclasPermitidas = /^[0-9\s\+\-\.\(\)]$/;
    if (tecla === 1 && !regexTeclasPermitidas.text(tecla)) {
        event.preventDefault();
    }
    limpiarEstilos(input);
}

export function validarCelularBlur(input) {
    const celular = $(input).val().trim();
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