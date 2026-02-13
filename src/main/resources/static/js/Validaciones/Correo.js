//EXPRESION REFULAR PARA VALIDAR EL CORREO
const regexCorreo = /[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/;

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


export function ValidarCorreo(input, event) {
    var correo = $(input).val().trim().toLowerCase() + event.key;
    if (regexCorreo.test(correo)) {
        marcarExito(input)
    } else {
        mostrarError(input)
    }

}

export function ValidarCorreoBlur(input) {
    var correo = $(input).val().trim().toLowerCase();
    $(input).val(correo);
    if (correo === "") {
        $("#error" + input.id).text("");
        $(input).removeClass("border-danger border-success");
        return;
    }
    if (regexCorreo.test(correo)) {
        marcarExito(input)
    } else {
        mostrarError(input)
    }

}