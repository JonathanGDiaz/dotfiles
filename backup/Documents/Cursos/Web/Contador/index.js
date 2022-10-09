let contG = ""
let contador = 0
let contadorEl = document.getElementById('contador')
let guardadoEl = document.getElementById('guardadoT')
function incremento() {
  contador += 1
  contadorEl.innerText = contador
}

function decremento() {
  contador -= 1
  contadorEl.innerText = contador
}

function guardado() {
  if (contG == "") {
    contG = contadorEl.innerText
  } else {
    contG = contG + " - " + contadorEl.innerText
  }
  guardadoT.innerText = "Anteriores guardador: " + contG
}
