var w = window.innerWidth;
var h = window.innerHeight;

var intro = document.getElementsByClassName("intro")[0];
var historia = document.getElementsByClassName("historia")[0];
var parrafos = document.getElementsByClassName("parrafos")[0];
var sonido = document.getElementById("sonido");

intro.style.fontSize = w / 30 + "px";
historia.style.fontSize = w / 20 + "px";
parrafos.style.height = h + "px";

window.addEventListener("resize", function() {
  w = canvas.width = window.innerWidth;
  h = canvas.height = window.innerHeight;
  intro.style.fontSize = w / 30 + "px";
  historia.style.fontSize = w / 20 + "px";
  parrafos.style.height = h + "px";
  /*Fondo de estrellas*/
  inicio();
  nevada();
});

function animar() {
  intro.className = 'intro texto_intro animacion_intro';
  historia.className = 'historia texto_historia animacion_historia';
  sonido.play();
}


