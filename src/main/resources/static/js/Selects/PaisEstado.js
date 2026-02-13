export function PaisEstado() {
    $("#selectPais").change(function () {
        console.log("Pais Estado Correcto");
        var IdPais = $("#selectPais").val();
        if (IdPais != 0) {
            $.ajax({
                url: "/usuario/getEstadoByPais/" + IdPais, //petición asincrona
                type: "GET",
                dataType: "json",
                success: function (data, textStatus, jqXHR) {
                    console.log("Pais Estado Correcto");
                    $("#selectEstado").empty();
                    $("#selectEstado").append(
                        "<option value=0 selected>Selecciona un estado</option>",
                    );
                    $.each(data.objects, function (i, estado) {
                        $("#selectEstado").append(
                            "<option value=" +
                            estado.IdEstado +
                            ">" +
                            estado.Nombre +
                            "</option>",
                        );
                    });
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    alert("No se pudo procesar la tarea");
                },
            });
        } else {
            $("#selectEstado").empty();
            $("#selectEstado").append(
                "<option value=0 selected>Selecciona un estado</option>",
            );
            $("#selectMunicipio").empty();
            $("#selectMunicipio").append(
                "<option value=0 selected>Selecciona un municipio</option>",
            );
            $("#selectColonia").empty();
            $("#selectColonia").append(
                "<option value=0 selected>Selecciona una colonia</option>",
            );
        }
    });
}