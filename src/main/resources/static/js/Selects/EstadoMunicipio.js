
export function EstadoMunicipio() {
    $("#selectEstado").change(function () {
        console.log("Estado Municipio Correcto");
        var IdEstado = $("#selectEstado").val();
        if (IdEstado != 0) {
            $.ajax({
                url: "/usuario/getMunicipioByEstado/" + IdEstado, //petición asincrona
                type: "GET",
                dataType: "json",
                success: function (data, textStatus, jqXHR) {
                    console.log("Estado Municipio Correcto");
                    $("#selectMunicipio").empty();
                    $("#selectMunicipio").append(
                        "<option value=0 selected>Selecciona un municipio</option>",
                    );
                    $.each(data.objects, function (i, municipio) {
                        $("#selectMunicipio").append(
                            "<option value=" +
                            municipio.IdMunicipio +
                            ">" +
                            municipio.Nombre +
                            "</option>",
                        );
                    });
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    alert("No se pudo procesar la tarea");
                },
            });
        } else {
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