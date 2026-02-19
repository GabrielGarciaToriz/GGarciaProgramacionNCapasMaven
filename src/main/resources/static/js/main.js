import {
    SoloLetras, SoloLetrasBlur, validarCorreo, validarCorreoBlur,
    validarPassword, validarPasswordBlur, validarCurp, validarCurpBlur,
    validarCelular, validarCelularBlur, validarTelefono, validarTelefonoBlur,
    abrirCalendario, soloCalendario, validarDirecciones, validarDireccionesBlur, Usuario, UsuarioBlur
} from "./Validaciones/index.js";

import {
    PaisEstado, EstadoMunicipio, MunicipioColonia, DireccionByCodigoPostal, CascadeoUbicacion
} from "./Selects/index.js";

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
    //Usuario
    { selector: ".validar-usuario", evento: "keypress", accion: Usuario },
    { selector: ".validar-usuario-blur", evento: "blur", accion: UsuarioBlur }
];

const inicializarSelectores = () => {
    PaisEstado();
    EstadoMunicipio();
    MunicipioColonia();
    DireccionByCodigoPostal();
    CascadeoUbicacion();
};

const aplicarValidaciones = () => {
    reglasValidacion.forEach(({ selector, evento, accion }) => {
        // Uso de Arrow Function y event.currentTarget en lugar de 'this'
        $(selector).on(evento, (event) => {
            accion(event.currentTarget, event);
        });
    });
};

// 4. Inicialización de la aplicación
$(document).ready(() => {
    inicializarSelectores();
    aplicarValidaciones();
});