//EXPRESION REGULAR PARA SOLO USAR LETRAS
const regexLetras = /^[a-zA-Z\s]+$/

const mostrarError = (input, mensaje) => {
    const errorSpan = $(`#error${input.id}`);
    errorSpan.text(mensaje).css("color", "red");
    $(input).addClass("border border-danger").removeClass("border border-success")
}
const limpiarEstilos = (input) => {
    const errorSpan = $(`#error${input.id}`);
    errorSpan.text("");
    $(input).removeClass("border border-danger border-success");
};
const marcarExito = (input) => {
    const errorSpan = $(`#error${input.id}`);
    errorSpan.text("Correcto").css("color", "green");
    $(input).removeClass("border border-danger").addClass("border border-success");
};

export function SoloLetras(input, event) {
    console.log("Esta validando")
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
        return
    }
    if (regexLetras.test(valor)) {
        marcarExito(input)
    } else {
        mostrarError(input)
    }
}