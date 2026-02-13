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
