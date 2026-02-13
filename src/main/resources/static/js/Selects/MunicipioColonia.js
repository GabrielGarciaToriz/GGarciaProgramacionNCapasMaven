export function MunicipioColonia() {
    $("#selectMunicipio").change(function () {
        console.log("Municipio Colonia Correcto");
        var IdMunicipio = $("#selectMunicipio").val();
        if (IdMunicipio != 0) {
            $.ajax({
                url: "/usuario/getColoniabyMunicipio/" + IdMunicipio, //petición asincrona
                type: "GET",
                dataType: "json",
                success: function (data, textStatus, jqXHR) {
                    console.log("Municipio Colonia Correcto");
                    $("#selectColonia").empty();
                    $("#selectColonia").append(
                        "<option value=0 selected>Selecciona una colonia</option>",
                    );
                    $.each(data.objects, function (i, colonia) {
                        $("#selectColonia").append(
                            "<option value=" +
                            colonia.IdColonia +
                            ">" +
                            colonia.Nombre +
                            "</option>",
                        );
                    });
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    alert("No se pudo procesar la tarea");
                },
            });
        } else {
            $("#selectColonia").empty();
            $("#selectColonia").append(
                "<option value=0 selected>Selecciona un colonia</option>",
            );
        }
    });
}
