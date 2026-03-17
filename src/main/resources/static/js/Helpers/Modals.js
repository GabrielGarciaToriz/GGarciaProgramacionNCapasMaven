export function abrirModalEdicionDireccion(idDireccion) {

    const modalElement = document.getElementById('ModalEditarDireccionDinamico');
    const contenedor = document.getElementById('contenedorModalEditarDireccion');

    if (!modalElement || !contenedor) {
        console.warn('No tienes permisos para editar direcciones o el modal no esta disponible.');
        return;
    }

    const myModal = new bootstrap.Modal(modalElement);
    myModal.show();

    // 2. Solicitamos el fragmento al servidor
    fetch('/usuario/direccion/editar/' + idDireccion)
        .then(response => {
            if (!response.ok) throw new Error("Error de red");
            return response.text(); 
        })
        .then(html => {

            contenedor.innerHTML = html;
        })
        .catch(error => {
            console.error('Hubo un problema con la petición Fetch:', error);
            contenedor.innerHTML =
                '<div class="modal-body text-danger">Error al cargar los datos.</div>';
        });
}