function abrirModalEdicionDireccion(idDireccion) {
    // 1. Mostramos el modal con el spinner de carga (opcional pero recomendado)
    const modalElement = document.getElementById('ModalEditarDireccionDinamico');
    const myModal = new bootstrap.Modal(modalElement);
    myModal.show();

    // 2. Solicitamos el fragmento al servidor
    fetch('/usuario/direccion/editar/' + idDireccion)
        .then(response => {
            if (!response.ok) throw new Error("Error de red");
            return response.text(); // Recibimos HTML, no JSON
        })
        .then(html => {
            // 3. Inyectamos el HTML devuelto por Thymeleaf en el contenedor
            document.getElementById('contenedorModalEditarDireccion').innerHTML = html;
        })
        .catch(error => {
            console.error('Hubo un problema con la petición Fetch:', error);
            document.getElementById('contenedorModalEditarDireccion').innerHTML =
                '<div class="modal-body text-danger">Error al cargar los datos.</div>';
        });
}