$(function () {
  $("#grupo").on("change", function (e) {
    $("#concierto").children().remove();
    var text = $("#grupo option:selected").text();
    $.ajax({
      url: "http://localhost:8080/api/conciertos/" + text,
      type: "GET",
      contentType: "application/json",
      dataType: "json",
      success: function (conciertos) {
        mostrarDatos(conciertos);
      },
    });
  });

  $(".getpass").click(function (e) {   
    e.preventDefault();
   
     let dni = $(this).closest("tr").find("td:eq(3)").text();
     console.log(dni);
     $.ajax({
       url: "http://localhost:8080/api/usuario/" + dni,
       type: "GET",
       contentType: "application/json",
       dataType: "json",
       cache: false,
       success: function (resultado) {
        let mensaje = `La contraseña del usuario con dni ${resultado.dni} es ${resultado.password}`;
        // console.log(resultado);
        //  $("#msg").html(
        //      `La contraseña del usuario con dni ${resultado.dni} es ${resultado.password}`
        //    );
        //    console.log("Después de mostrar #muestra_password");
        //    $("#muestra_password").addClass("show");
             $("#muestra_password").remove();
             $("#appendElement").append(`
                <div id="muestra_password" class="alert alert-info alert-dismissible fade show mt-3" role="alert">
                    <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                    <span>${mensaje}</span>
                </div>
            `);
            $("#muestra_password").show();
       },
     });
  
     
     

   
    
  });
});
  function mostrarDatos(conciertos) {
    $("#concierto").append(
      `<option value="0" selected>---Seleccione concierto---</option>`
    );
    if (conciertos.length > 0) {
      $.each(conciertos, function (index, item) {
        $("#concierto").append(
          `<option value="${item.idconcierto}">${
            item.fecha + " - " + item.hora + " - " + item.plazas
          }</option>`
        );
      });
    } else {
      $("#concierto").append(
        `<option value="">NO HAY CONCIERTOS PARA ESTE GRUPO</option>`
      );
    }
  }
//  <option th:each=" c: ${concierto}" th:value="${c.idconcierto}" th:text="${#temporals.format(c.fecha, 'dd/MM/yyyy') + ' - ' + c.hora}"></option>
