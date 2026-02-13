export function SoloLetras(input, event) {

    var valorCompleto = $(input).val().trim() + event.key;

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

    if (valor === "") {
        $("#error" + input.id).text("");
        $(input).removeClass("border-danger border-success");
        return;
    }
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
export function validarCorreo(input){
    var correo =  $(input).val().trim().toLoweCase();
    $(input).val(correo);
    var regex = /[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
    if(correo){
        
    }
}
