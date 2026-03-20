import {
    SoloLetras, SoloLetrasBlur, validarCorreo, validarCorreoBlur,
    validarPassword, validarPasswordBlur, validarCurp, validarCurpBlur,
    validarCelular, validarCelularBlur, validarTelefono, validarTelefonoBlur,
    abrirCalendario, soloCalendario, validarDirecciones, validarDireccionesBlur, Usuario, UsuarioBlur
} from "./Validaciones/index.js";

import {
    PaisEstado, EstadoMunicipio, MunicipioColonia, DireccionByCodigoPostal, CascadeoUbicacion
} from "./Selects/index.js";

import { confirmarEliminacionDireccionUsuario, confirmarEliminacionDireccion, verificarAlertasServidor, abrirModalEdicionDireccion } from "./Helpers/index.js";

import { initCargaMasiva } from "./Files/Archivos.js";

const reglasValidacion = [
    // Letras
    { selector: ".validar-letras", evento: "keypress", accion: SoloLetras },
    { selector: ".validar-letras-blur", evento: "blur", accion: SoloLetrasBlur },
    // Correo
    { selector: ".validar-correo", evento: "keypress", accion: validarCorreo },
    { selector: ".validar-correo-blur", evento: "blur", accion: validarCorreoBlur },
    // Password
    { selector: ".validar-password", evento: "keypress", accion: validarPassword },
    { selector: ".validar-password-blur", evento: "blur", accion: validarPasswordBlur },
    // Curp
    { selector: ".validar-curp", evento: "keypress", accion: validarCurp },
    { selector: ".validar-curp-blur", evento: "blur", accion: validarCurpBlur },
    // Celular y Teléfono
    { selector: ".validar-celular", evento: "keypress", accion: validarCelular },
    { selector: ".validar-celular-blur", evento: "blur", accion: validarCelularBlur },
    { selector: ".validar-telefono", evento: "keypress", accion: validarTelefono },
    { selector: ".validar-telefono-blur", evento: "blur", accion: validarTelefonoBlur },
    // Direcciones
    { selector: ".validar-direccion", evento: "keypress", accion: validarDirecciones },
    { selector: ".validar-direccion-blur", evento: "blur", accion: validarDireccionesBlur },
    // Calendario
    { selector: ".solo-calendario", evento: "keydown", accion: soloCalendario },
    { selector: ".abrir-calendario", evento: "click", accion: abrirCalendario },
    // Usuario
    { selector: ".validar-usuario", evento: "keypress", accion: Usuario },
    { selector: ".validar-usuario-blur", evento: "blur", accion: UsuarioBlur }
];

const aplicarValidaciones = () => {
    reglasValidacion.forEach(({ selector, evento, accion }) => {
        $(selector).on(evento, (event) => {
            accion(event.currentTarget, event);
        });
    });
};

const inicializarSelectores = () => {
    PaisEstado();
    EstadoMunicipio();
    MunicipioColonia();
    DireccionByCodigoPostal();
    CascadeoUbicacion();
};

const configurarCsrfAjax = () => {
    const csrfToken = document.querySelector('meta[name="_csrf"]')?.getAttribute('content');
    const csrfHeader = document.querySelector('meta[name="_csrf_header"]')?.getAttribute('content');

    if (csrfToken && csrfHeader) {
        $.ajaxSetup({
            beforeSend: function (xhr) {
                xhr.setRequestHeader(csrfHeader, csrfToken);
            }
        });
    }
};

const initUsuarioDetail = () => {
    // Inicializar selectores en cascada para el modal de agregar dirección
    inicializarSelectores();

    // Configurar eliminación de direcciones
    $(".btn-eliminar-direccion").on("click", function (event) {
        event.preventDefault();
        const url = $(this).data("url");
        console.log("Se va a ejecutar la ruta: ", url);
        confirmarEliminacionDireccion(url);
    });
};

const initFormularioUsuario = () => {
    inicializarSelectores();

    $(".btn-eliminar-direccion").on("click", function (event) {
        event.preventDefault();
        console.log("Me estas apretando");
        const url = $(this).data("url");
        console.log("Se va a ejecutar la ruta: ", url);
        confirmarEliminacionDireccion(url);
    });

    $("#formRegistroUsuario").on("submit", function () {
        const btn = $("#btnGuardar");
        btn.prop("disabled", true);
        btn.html('<span class="spinner-border spinner-border-sm me-2" role="status" aria-hidden="true"></span>Guardando..');
        return true;
    });

    const fechaHoy = new Date();
    const anioMaximo = fechaHoy.getFullYear() - 18;
    const mes = String(fechaHoy.getMonth() + 1).padStart(2, '0');
    const dia = String(fechaHoy.getDate()).padStart(2, '0');
    const fechaMaxima = `${anioMaximo}-${mes}-${dia}`;
    $("#FechaNacimiento").attr("max", fechaMaxima);

    if (typeof idDireccion !== 'undefined' && idDireccion !== null) {
        abrirModalEdicionDireccion(idDireccion);
    }
};


$(document).ready(() => {

    configurarCsrfAjax();
    verificarAlertasServidor();
    aplicarValidaciones();


    const paginaActual = document.body.getAttribute('data-page');

    switch (paginaActual) {
        case 'Usuario':
            initDirectorioUsuarios();
            break;

        case 'UsuarioForm':
            initFormularioUsuario();
            break;

        case 'UsuarioDetail':
            initUsuarioDetail();
            break;

        case 'UsuarioCargaMasiva':
            initCargaMasiva();
            break;

        default:
            console.log("Página cargada sin scripts específicos asignados o falta el atributo data-page.");
            break;
    }
});