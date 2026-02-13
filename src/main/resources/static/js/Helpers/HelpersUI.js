
export const mostrarError = (input, mensaje) => {
    const errorSpan = $(`#error${input.id}`);
    errorSpan.text(mensaje).css("color", "red");
    $(input).addClass("border border-danger").removeClass("border border-success")
}
export const limpiarEstilos = (input) => {
    const errorSpan = $(`#error${input.id}`);
    errorSpan.text("");
    $(input).removeClass("border border-danger border-success");
};
export const marcarExito = (input) => {
    const errorSpan = $(`#error${input.id}`);
    errorSpan.text("Correcto").css("color", "green");
    $(input).removeClass("border border-danger").addClass("border border-success");
};