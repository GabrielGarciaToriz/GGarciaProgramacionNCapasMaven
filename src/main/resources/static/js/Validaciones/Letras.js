import { limpiarEstilos, marcarExito , mostrarError} from "../Helpers/HelpersUI.js";
//EXPRESION REGULAR PARA SOLO USAR LETRAS
const regexLetras = /^[a-zA-Z\s]+$/

export function SoloLetras(input, event) {
    var valorCompleto = $(input).val() + event.key;
    //EXPRESON REGULAR PARA SOLO ACEPTAR CARACTERES Y ESPACIOS
    if (!regexLetras.test(valorCompleto)) {
        event.preventDefault();
        mostrarError(input, "Solo letras")
    } else {
        limpiarEstilos(input);
    }
}

export function SoloLetrasBlur(input) {
    const valor = $(input).val();
    if (valor === "") {
        limpiarEstilos(input);
        return;
    }
    if (regexLetras.test(valor)) {
        marcarExito(input)
    } else {
        mostrarError(input)
    }
}