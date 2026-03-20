import { cargarSelectCascada } from '../Helpers/HelpersUI.js'; // Ajusta la ruta según tu proyecto

export function PaisEstado() {
    // Configurar cascada para selectores normales (modal agregar)
    configurarCascadeoPaisEstado("#selectPais", "#selectEstado", "#selectMunicipio", "#selectColonia");

    // Configurar cascada para selectores de edición (modal editar)
    configurarCascadeoPaisEstado("#editSelectPais", "#editSelectEstado", "#editSelectMunicipio", "#editSelectColonia");
}

function configurarCascadeoPaisEstado(selectorPais, selectorEstado, selectorMunicipio, selectorColonia) {
    const $selectPais = $(selectorPais);
    const $selectEstado = $(selectorEstado);
    const $selectMunicipio = $(selectorMunicipio);
    const $selectColonia = $(selectorColonia);

    $selectPais.change(function () {
        cargarSelectCascada(
            $(this).val(),
            "/usuario/getEstadoByPais/",
            $selectEstado,
            "Selecciona un estado",
            "IdEstado",
            "Nombre",
            [
                { $el: $selectMunicipio, texto: "Selecciona un municipio" },
                { $el: $selectColonia, texto: "Selecciona una colonia" }
            ]
        );
    });
}