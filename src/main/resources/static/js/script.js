$(".delete-group").on("click", function (e) {
  e.preventDefault();
  console.log("Botón de eliminar clicado");
  Swal.fire({
    title: "¿Seguro que quieres eliminar este elemento?",
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
      window.location.href = href;
    }

  });
  $(".swal2-html-container").css({ "font-size": "1.8rem" });
  $("button").css({ "font-size": "1.5rem" });


});

$(".edit-group").on("click", function(e) {
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



// const barras = document.querySelector(".barras");
// const navegacion = document.querySelector(".navegacion");
// const contenido = document.querySelector(".contenido-navegacion");
// barras.addEventListener("click", (e) => {
//   navegacion.style.display = "flex";
//   navegacion.style.flexDirection = "column";
//   barras.style.display = "none";
//   contenido.style.flexDirection = "column";
// });

/** Lanza formulario buscar por fechas **/
$(".busca-por-fechas").on("click", function(e){
  // $("#busca_por_fecha_form").css("visibility", "visible");
  $("#busca_por_fecha_form").show();
});

const dropdownElementList = document.querySelectorAll(".dropdown-toggle");
const dropdownList = [...dropdownElementList].map(
  (dropdownToggleEl) => new bootstrap.Dropdown(dropdownToggleEl)
);



