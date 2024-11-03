window.addEventListener("load", inicio);

function inicio() {
    let select = document.getElementById("Selector");
    let DivTipo = document.getElementById("SelTipos");
    let DivTexto = document.getElementById("BusTexto");
    let btn = document.getElementById("btnForm");
    DivTexto.style.display = "none";
    DivTipo.style.display = "none";
    btn.style.display = "none"
    select.addEventListener("change", Cambio);
}
function Cambio() {
    let select = document.getElementById("Selector");
    let DivTipo = document.getElementById("SelTipos");
    let DivTexto = document.getElementById("BusTexto");
    let btn = document.getElementById("btnForm");
    var opc = select.value;
    if (opc === "2") {
        DivTipo.style.display = "block";
        btn.style.display = "block";
        DivTexto.style.display = "none";
    } else if (opc === "1" || opc === "4") {
        DivTexto.style.display = "block";
        btn.style.display = "block";
        DivTipo.style.display = "none";
    } else if (opc === "3") {
        btn.style.display = "block"
        DivTexto.style.display = "none";
        DivTipo.style.display = "none";
    } else {
        DivTexto.style.display = "none";
        DivTipo.style.display = "none";
        btn.style.display = "none"
    }
}