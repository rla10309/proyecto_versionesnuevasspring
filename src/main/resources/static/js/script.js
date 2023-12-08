$(".delete-group").on("click", function (e) {
  e.preventDefault();
  console.log("Botón de eliminar clicado");
  Swal.fire({
    title:
      "¿Seguro que quieres eliminar este elemento? Perderás todos los datos",
    text: "Esta acción no se puede deshacer",
    icon: "warning",
    width: "40rem",
    showCancelButton: true,
    confirmButtonColor: "#3085d6",
    cancelButtonColor: "#d33",
    confirmButtonText: "Sí, elimínalo",
  }).then((result) => {
    console.log("Resultado de la ventana modal:", result);
    if (result.isConfirmed) {
      href = $(this).attr("href");
      console.log("Redirigiendo a:", href);
      href += "&success=true";
    } else {
      href = $(this).attr("href");
      console.log("Redirigiendo a:", href);
      href += "&error=true";
    }
    window.location.href = href;
  });
  $(".swal2-html-container").css({ "font-size": "1.8rem" });
  $("button").css({ "font-size": "1.5rem" });
});

$(".edit-group").on("click", function (e) {
  e.preventDefault();
  console.log(e.target);
  Swal.fire({
    title: "No deberías editar las ventas",
    icon: "error",
    width: "40rem",
    showCancelButton: true,
    confirmButtonColor: "#3085d6",
    cancelButtonColor: "#d33",
    confirmButtonText: "Quiero editar",
  }).then((result) => {
    if (result.isConfirmed) {
      href = $(this).attr("href");
      console.log(href);
      window.location.href = href;
    }
  });
  $(".swal2-html-container").css({ "font-size": "1.8rem" });
  $("button").css({ "font-size": "1.5rem" });
});

/** Lanza formulario buscar por fechas **/
$(".busca-por-fechas").on("click", function (e) {
  // $("#busca_por_fecha_form").css("visibility", "visible");
  $("#busca_por_fecha_form").show();
});

const dropdownElementList = document.querySelectorAll(".dropdown-toggle");
const dropdownList = [...dropdownElementList].map(
  (dropdownToggleEl) => new bootstrap.Dropdown(dropdownToggleEl)
);

$(function () {
  // TABLA DE CONCIERTOS
  $("#mitabla").DataTable({
    columnDefs: [{ orderable: false, targets: [1, 2, 4, 6, 7] }],
    info: false,
    oPaginate: false,
    language: {
      decimal: ",",
      thousands: ".",
      lengthMenu: "Mostrar _MENU_ registros",
      zeroRecords: "No se encontraron resultados",
      info: "Mostrando registros del _START_ al _END_ de un total de _TOTAL_ registros",
      infoEmpty: "Mostrando registros del 0 al 0 de un total de 0 registros",
      infoFiltered: "(filtrado de un total de _MAX_ registros)",
      sSearch: "Buscar:",
      oPaginate: {
        sFirst: "Primero",
        sLast: "Último",
        sNext: "Siguiente",
        sPrevious: "Anterior",
      },
      sProcessing: "Cargando...",
    },
  });

  // TABLA DE USUARIOS
  $("#tabla-usuarios").DataTable({
    columnDefs: [{ orderable: false, targets: [6] }],
    info: false,
    oPaginate: false,
    language: {
      decimal: ",",
      thousands: ".",
      lengthMenu: "Mostrar _MENU_ registros",
      zeroRecords: "No se encontraron resultados",
      info: "Mostrando registros del _START_ al _END_ de un total de _TOTAL_ registros",
      infoEmpty: "Mostrando registros del 0 al 0 de un total de 0 registros",
      infoFiltered: "(filtrado de un total de _MAX_ registros)",
      sSearch: "Buscar:",
      oPaginate: {
        sFirst: "Primero",
        sLast: "Último",
        sNext: "Siguiente",
        sPrevious: "Anterior",
      },
      sProcessing: "Cargando...",
    },
  });

  // TABLA DE VENTAS
  $("#tabla-ventas").DataTable({
    columnDefs: [{ orderable: false, targets: [5, 6] }],
    info: false,
    oPaginate: false,
    language: {
      decimal: ",",
      thousands: ".",
      lengthMenu: "Mostrar _MENU_ registros",
      zeroRecords: "No se encontraron resultados",
      info: "Mostrando registros del _START_ al _END_ de un total de _TOTAL_ registros",
      infoEmpty: "Mostrando registros del 0 al 0 de un total de 0 registros",
      infoFiltered: "(filtrado de un total de _MAX_ registros)",
      sSearch: "Buscar:",
      oPaginate: {
        sFirst: "Primero",
        sLast: "Último",
        sNext: "Siguiente",
        sPrevious: "Anterior",
      },
      sProcessing: "Cargando...",
    },
  });

  // TABLA DE INFORME DE VENTAS
  $("#tabla-informe").DataTable({
    columnDefs: [{ orderable: false, targets: [0, 1, 7] }],
    info: false,
    oPaginate: false,
    language: {
      decimal: ",",
      thousands: ".",
      lengthMenu: "Mostrar _MENU_ registros",
      zeroRecords: "No se encontraron resultados",
      info: "Mostrando registros del _START_ al _END_ de un total de _TOTAL_ registros",
      infoEmpty: "Mostrando registros del 0 al 0 de un total de 0 registros",
      infoFiltered: "(filtrado de un total de _MAX_ registros)",
      sSearch: "Buscar:",
      oPaginate: {
        sFirst: "Primero",
        sLast: "Último",
        sNext: "Siguiente",
        sPrevious: "Anterior",
      },
      sProcessing: "Cargando...",
    },
  });

  /** darse de baja */
  $("#baja").on("click", (e) => {
    e.preventDefault();
    Swal.fire({
      title: "¿Estás seguro/a?",
      icon: "warning",
      width: "40rem",
      showCancelButton: true,
      confirmButtonColor: "#3085d6",
      cancelButtonColor: "#d33",
      confirmButtonText: "Quiero darme de baja",
    }).then((result) => {
      if (result.isConfirmed) {
        window.location.href = "http://localhost:8080/user/darsedebaja";
        // Swal.fire({
        //   title: "Se ha dado de baja con éxito",
        //   icon: "success",
        //   width: "40rem",
        //   confirmButtonColor: "#3085d6",
        // });
        // window.location.href = "http://localhost:8080/logout";
      }
    });
  });


  if (window.location.href.includes("logout")) {
    Swal.fire({
      icon: "success",
      text: "Sesión cerrada correctamente",
      confirmButtonColor: "#20c997",
      width: "40rem",
    }).then((result) => {
      if (result.isConfirmed) {
        window.location.href = "http://localhost:8080/index";
      }
    });
  }
});
