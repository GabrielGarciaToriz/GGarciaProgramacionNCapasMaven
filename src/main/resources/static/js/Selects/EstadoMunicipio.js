import { cargarSelectCascada } from '../Helpers/HelpersUI.js';

export function EstadoMunicipio() {
    // Configurar cascada para selectores normales (modal agregar)
    configurarCascadeoEstadoMunicipio("#selectEstado", "#selectMunicipio", "#selectColonia");

    // Configurar cascada para selectores de edición (modal editar)
    configurarCascadeoEstadoMunicipio("#editSelectEstado", "#editSelectMunicipio", "#editSelectColonia");
}

function configurarCascadeoEstadoMunicipio(selectorEstado, selectorMunicipio, selectorColonia) {
    const $selectEstado = $(selectorEstado);
    const $selectMunicipio = $(selectorMunicipio);
    const $selectColonia = $(selectorColonia);

    $selectEstado.change(function () {
        cargarSelectCascada(
            $(this).val(),
            "/usuario/getMunicipioByEstado/",
            $selectMunicipio,
            "Selecciona un municipio",
            "IdMunicipio",
            "Nombre",
            [
                { $el: $selectColonia, texto: "Selecciona una colonia" }
            ]
        );
    });
}