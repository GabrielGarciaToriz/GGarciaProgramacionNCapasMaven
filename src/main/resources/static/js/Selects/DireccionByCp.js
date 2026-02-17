export function DireccionByCodigoPostal() {
    $("#CodigoPostal").change(function () {
        
        var codigoPostal = $("#CodigoPostal").val();
        if (codigoPostal != "") {
            console.log(codigoPostal)
            $.ajax({
                url: "/usuario/getDireccionByCodigoPostal/" + codigoPostal,
                type: "GET",
                dataType: "json",
                succes: function (data) {
                    $.each(data.objects, function(i,Pais){
                        console.log(Pais)
                    })
                },
                error: function () {
                    alert("No recibe parametros");
                }
            })
        } else {
            console.log("El campo esta vacio")
        }
    })
}
