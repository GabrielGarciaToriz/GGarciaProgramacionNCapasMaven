export function DireccionByCodigoPostal() {
    // Configurar para selectores normales (modal agregar)
    configurarBusquedaPorCP("#CodigoPostal", "#selectPais", "#selectEstado", "#selectMunicipio", "#selectColonia");

    // Configurar para selectores de edición (modal editar) - si existe el campo
    if ($("#editCodigoPostal").length > 0) {
        configurarBusquedaPorCP("#editCodigoPostal", "#editSelectPais", "#editSelectEstado", "#editSelectMunicipio", "#editSelectColonia");
    }
}

function configurarBusquedaPorCP(selectorCP, selectorPais, selectorEstado, selectorMunicipio, selectorColonia) {
    $(selectorCP).change(function () {
        var codigoPostal = $(selectorCP).val();

        if (codigoPostal !== "") {
            $.ajax({
                url: "/usuario/getDireccionByCodigoPostal/" + codigoPostal,
                type: "GET",
                dataType: "json",
                success: function (data) {
                    // Verificamos que el JSON traiga resultados
                    if (data.objects && data.objects.length > 0) {

                        var primeraColonia = data.objects[0];

                        var idPais = primeraColonia.Municipio.Estado.Pais.IdPais;
                        var idEstado = primeraColonia.Municipio.Estado.IdEstado;
                        var nombreEstado = primeraColonia.Municipio.Estado.Nombre;
                        var idMunicipio = primeraColonia.Municipio.IdMunicipio;
                        var nombreMunicipio = primeraColonia.Municipio.Nombre;

                        $(selectorPais).val(idPais).trigger('change');

                        $(selectorEstado).empty()
                            .append(`<option value="${idEstado}">${nombreEstado}</option>`)
                            .val(idEstado).trigger('change');

                        $(selectorMunicipio).empty()
                            .append(`<option value="${idMunicipio}">${nombreMunicipio}</option>`)
                            .val(idMunicipio).trigger('change');

                        $(selectorColonia).empty();
                        $(selectorColonia).append('<option value="0">Selecciona una colonia</option>');

                        $.each(data.objects, function (i, colonia) {
                            $(selectorColonia).append(
                                `<option value="${colonia.IdColonia}" data-cp="${colonia.CodigoPostal}">${colonia.Nombre}</option>`
                            );
                        });

                    } else {
                        alert("No se encontró ninguna dirección con este Código Postal.");
                        limpiarSelectsUbicacion();
                    }
                },
                error: function () {
                    alert("Ocurrió un error al buscar el Código Postal.");
                }
            });
        } else {
            console.log("El campo está vacío");
            limpiarSelectsUbicacion();
        }
    });
}

export function CascadeoUbicacion() {

    // Función auxiliar para manejar cascada de municipios a colonias
    function configurarCascadeoMunicipioColonia(selectorMunicipio, selectorColonia, selectorCodigoPostal) {
        $(selectorMunicipio).change(function () {
            var idMunicipio = $(this).val();

            if (idMunicipio != "0") {
                $.ajax({
                    url: "/usuario/getColoniabyMunicipio/" + idMunicipio,
                    type: "GET",
                    dataType: "json",
                    success: function (data) {
                        $(selectorColonia).empty();
                        $(selectorColonia).append('<option value="0" data-cp="">Selecciona una colonia</option>');

                        $.each(data.objects, function (i, colonia) {
                            $(selectorColonia).append(
                                `<option value="${colonia.IdColonia}" data-cp="${colonia.CodigoPostal}">${colonia.Nombre}</option>`
                            );
                        });
                    },
                    error: function () {
                        alert("Error al cargar las colonias.");
                    }
                });
            } else {
                $(selectorColonia).empty().append('<option value="0">Selecciona una colonia</option>');
                if (selectorCodigoPostal) $(selectorCodigoPostal).val("");
            }
        });

        $(selectorColonia).change(function () {
            if (selectorCodigoPostal) {
                var optionSeleccionado = $(this).find('option:selected');
                var codigoPostalAsignado = optionSeleccionado.data('cp');

                if (codigoPostalAsignado) {
                    $(selectorCodigoPostal).val(codigoPostalAsignado);
                } else if ($(this).val() === "0" || $(this).val() === 0) {
                    $(selectorCodigoPostal).val("");
                }
            }
        });
    }

    // Configurar cascada para selectores normales (modal agregar)
    configurarCascadeoMunicipioColonia("#selectMunicipio", "#selectColonia", "#CodigoPostal");

    // Configurar cascada para selectores de edición (modal editar)
    configurarCascadeoMunicipioColonia("#editSelectMunicipio", "#editSelectColonia", null);
}

function limpiarSelectsUbicacion() {
    $("#selectPais, #editSelectPais").val("0");
    $("#selectEstado, #selectMunicipio, #selectColonia, #editSelectEstado, #editSelectMunicipio, #editSelectColonia")
        .empty()
        .append('<option value="0" selected>Selecciona una opción</option>');
}