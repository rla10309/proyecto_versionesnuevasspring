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
     let dni = $("#fila-usuario").children().eq(3).text();
     $.ajax({
       url: "http://localhost:8080/api/usuario/" + dni,
       type: "GET",
       contentType: "application/json",
       dataType: "json",
       success: function (resultado){
        $("#muestra_password").children().eq(1).html(
          `La contraseña del usuario con dni ${resultado.dni} es ${resultado.password}`);
          $("#muestra_password").show();

        
       }
     });
     

    e.preventDefault();
    console.log(dni);
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
