export function SoloLetras(input, event) {
    var valorCompleto = $(input).val().trim() + event.key;
    //EXPRESON REGULAR PARA SOLO ACEPTAR CARACTERES Y ESPACIOS
    var regex = /^[a-zA-Z\s]+$/;

    if (regex.test(valorCompleto)) {
        $("#error" + input.id + "").text("");
        $(input).removeClass("border border-danger");
    } else {
        event.preventDefault();
        $(input).addClass("border border-danger");
        $("#error" + input.id + "")
            .text("Solo letras")
            .css("color", "red");
    }
}

export function SoloLetrasBlur(input) {
    var regex = /^[a-zA-Z\s]+$/;
    var valor = $(input).val().trim();
    //SI EL INPUT ESTA VACIO QUITA LA CLASES
    if (valor === "") {
        $("#error" + input.id).text("");
        $(input).removeClass("border-danger border-success");
        return;
    }
    //SI NO CUMPLE LA REGEX DESPUES DE SALIR DEL INPUT EL BORDE SE VUELVE ROJO, CASO CONTRARIO SE VUELVE VERDE
    if (regex.test(valor)) {
        $("#error" + input.id + "").text("");
        $(input).removeClass("border border-danger");
        $(input).addClass("border border-success");
    } else {
        $("#error" + input.id).text("Formato no valido");
        $(input).removeClass("border border-success");
        $(input).addClass("border border-danger");
    }
}