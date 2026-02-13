
export function validarCorreo(input, event) {
    // Expresion regular para validar el correo 
    var regex = /[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;

    var correo = $(input).val().trim().toLowerCase() + event.key;
    if (regex.test(correo)) {
        $("#error" + input.id + "").text("");
        $(input).removeClass("border border-danger");
        $(input).addClass("border border-success");
    } else {
        $("#error" + input.id).text("Formato no valido");
        $(input).removeClass("border border-success");
        $(input).addClass("border border-danger");
    }

}

export function validarCorreoBlur(input) {
    var correo = $(input).val().trim().toLowerCase();
    $(input).val(correo);
    var regex = /[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/;
    if (correo === "") {
        $("#error" + input.id).text("");
        $(input).removeClass("border-danger border-success");
        return;
    }
    if (regex.test(correo)) {
        $("#error" + input.id + "").text("");
        $(input).removeClass("border border-danger");
        $(input).addClass("border border-success");
    } else {
        $("#error" + input.id).text("Formato no valido");
        $(input).removeClass("border border-success");
        $(input).addClass("border border-danger");
    }

}