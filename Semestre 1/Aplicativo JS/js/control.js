/* Se utilizara "control.js" para el manejo de la aplicacion a nivel HTML, ya sea el despliegue o cierre de las distintas funciones de la solucion, como por ejemplo
la carga de datos a las tablas con informacion respecto a clientes,reservas, etc. No se espera utilizar "control.js" para el ingreso de datos de la aplicacion (directamente). */
window.addEventListener("load",inicio)
var divsIdFunciones = [];
var menuIdFunciones = [];
var usuarioLogeado; tipoUsuarioLogeado = 0;
function inicio () {
    //Listeners unicamente para el acceso a las funciones.
    document.querySelector("#btnHabilitarReservasVer").addEventListener("click",btnHabilitarReservaVer);
    document.querySelector("#btnModificarCupoMaxVer").addEventListener("click",btnModificarCupoMaxVer);
    document.querySelector("#btnVisualizarEstadisticasVer").addEventListener("click",btnVisualizarEstadisticasVer);
    document.querySelector("#btnFinalizarReservasVer").addEventListener("click",btnFinalizarReservas);
    document.querySelector("#btnVerListaVer").addEventListener("click",btnverReservasPendientes);
    document.querySelector("#btnSolicitarReservaVer").addEventListener("click",btnSolicitarReserva);
    document.querySelector("#btnCancelarReservaVer").addEventListener("click",btnCancelarReserva);
    document.querySelector("#btnCalificarReservaVer").addEventListener("click",btnCalificarReserva);
    document.querySelector("#btnVisualizarEstadisticasClienteVer").addEventListener("click",btnVisualizarEstadisticasVerCliente);
    document.querySelector("#btnCerrarSesionVer").addEventListener("click",btnCerrarSesion);
    document.querySelector("#btnCerrarSesionVer2").addEventListener("click",btnCerrarSesion);
    document.querySelector("#registroUsuario").addEventListener("click",btnRegistroCliente);

    /*Funciones para registro, e inicio de sesion unicamente para los botones.*/
    document.querySelector("#enviarRegistroUsuario").addEventListener("click",registroNuevoUsuario);
    document.querySelector("#enviarInicioSesion").addEventListener("click",inicioSesionVentana);
    /*Listeners de botones para funciones de Locales*/
    document.querySelector("#btnCargarNuevoCupoMax").addEventListener("click",btnModificarCupoMaxlocal);
    document.querySelector("#btnCargarEstadodeReserva").addEventListener("click",btnparaHabilitarReservas);
    document.querySelector("#btnBuscarReservasCliente").addEventListener("click",btnBuscarCliente);
    /*Listeners de botones para funciones de cliente */
    document.querySelector("#btnFinalizarReserva").addEventListener("click",btnparaFinalizarReserva);
    document.querySelector("#btncupoNuevaReserva").addEventListener("click",btnSolicitarNuevaReserva);
    document.querySelector("#enviarCancelarReserva").addEventListener("click",btnCancelarReservaSelec);
    document.querySelector("#enviarPuntajeReserva").addEventListener("click",btnEnviarPuntajeReserva);
    divsIdFunciones = obtenerIDs(document.querySelectorAll(".aplicacion"));
    menuIdFunciones = obtenerIDs(document.querySelectorAll(".menu"));
    tablasvacias();
    mostrarSeleccionada("",divsIdFunciones);
    mostrarSeleccionada(menuIdFunciones[tipoUsuarioLogeado],menuIdFunciones);
    cargaLocales();
    cargaUsuarios();
    cargaReservas();
    
}

/*Se presenta la funcion "mostrarSeleccionada(idDiv)" para poder controlar la interfaz del usuario. Segun el manejo de botones
se mostrara la "ventana" deseada. Si se envia vacio, sirve para setear todas las ventanas a no visibles.
*/
function mostrarSeleccionada(idDiv,array){
    for(let i = 0, noSeteado = true; i < array.length; i++){
        if(!noSeteado||idDiv === array[i]){
            document.querySelector("#" + idDiv).style.display = "Block";  
            seEncontro = false
        }else{
            document.querySelector("#" + array[i]).style.display = "None";
        }
    }
}
/*Se utiliza la funcion para poder obtener todos los ID de los divs de las distinta funcionalidades, para poder utilizar a futuro la funcion "mostrarSeleccionada(idDiv,array)"*/
function obtenerIDs(array){
    let arraySalida = [];
    for(let i = 0; i < array.length; i++){
        arraySalida.push(array[i].id)
    }
    return arraySalida;
}
/**/
function inicioSesionVentana(){
    let usuario = document.querySelector("#usuarioInicioSesion").value;
    let contra = document.querySelector("#contrasenaInicioSesion").value;
    let tipo = auxinicioSesionVentana();
    let form = document.querySelector("#formInicioSesion");
    let labelUsuario = document.querySelector("#usuarioSesionIniciada");
    if(tipo === "Cliente"){
        if(auxValidacionCredencialesCliente(usuario,contra)){
            mostrarSeleccionada("menuNavegacionCliente",menuIdFunciones);
            usuarioLogeado = obtenerIdUsuario(usuario);
            tipoUsuarioLogeado = "2"
            labelUsuario.innerHTML = "Usuario actual: " + usuario;
        }else{
            alert("Revise informacion ingresada, si es un Local, recuerde marcar la casilla.");
            form.reset();
            
        }
    }else{
        if(auxValidacionCredencialesLocal(usuario,contra)){
            mostrarSeleccionada("menuNavegacionLocal",menuIdFunciones);
            usuarioLogeado = obtenerIdLocal(usuario);
            tipoUsuarioLogeado = "1";
            labelUsuario.innerHTML = "Usuario actual: " + usuario;
        }else{
            alert("Revise informacion ingresada, si es un Local, recuerde marcar la casilla.");
            form.reset();
        }
    }
}
/*Funcion para el inicio de sesion de los locales. */
function auxinicioSesionVentana(){
    let salida = "Cliente";
    if(document.getElementById("esLocalInicioSesion").checked){
        salida = "Local";
    }
    return salida;
}
/*Funcion de cierre de sesion donde se setean las credenciales a undefined y se carga el menu inicial.*/
function cerrarSesion(){
    usuarioLogeado = undefined;
    tipoUsuarioLogeado = "0"
    document.querySelector("#usuarioSesionIniciada").innerHTML = "";
    mostrarSeleccionada("vacio",divsIdFunciones);
    mostrarSeleccionada(menuIdFunciones[tipoUsuarioLogeado],menuIdFunciones)
}
function registroNuevoUsuario(){
    let nombre = document.querySelector("#nombreRegistroUsuario").value;
    let usuario = document.querySelector("#usuarioRegistroUsuario").value;
    let contra = document.querySelector("#contrasenaRegistroUsuario").value;
    let form = document.querySelector("#formularioRegistro");
    if (registroUsuario(nombre,usuario,contra)){
        alert("Se registro correctamente. Por favor inicie sesion.")
        mostrarSeleccionada("vacio",divsIdFunciones);
        form.reset();
    }else{
        alert("Revisar informacion ingresada.")
    }

}
// Funciones utilizadas para la navegacion en el menu de aplicaciones del usuario/local. 
function btnHabilitarReservaVer() { 
    mostrarSeleccionada("divHabilitarReserva", divsIdFunciones);
    let label = document.querySelector("#estadoActualdeReserva"), estado = "";
    if(usuarioLogeado.estaHabilitado === true){
        estado = "Habilitado para reservas"
    }else{
        estado = "Deshabilitado para reservas"
    }
    label.innerHTML = estado;
}
function btnModificarCupoMaxVer() { 
    mostrarSeleccionada("divModificarCupo", divsIdFunciones);
    label = document.querySelector("#estadoActualdeCupoMax");
    label.innerHTML = contenedorLocales[usuarioLogeado.idLocal].cupoMax;
}

function btnVisualizarEstadisticasVer() { 
    mostrarSeleccionada("divVerEstadisticaLocal", divsIdFunciones);
    cargaTablaEstadisticasLocal(usuarioLogeado.idLocal);
    cargaTablaEstadisticasOtrosLocal();
    cargaTablaEstadisticasLocalResenas();
    tablasvacias();
}

function btnFinalizarReservas() { 
    mostrarSeleccionada("divFinalizarReserva", divsIdFunciones);
    cargaTablaReservaFinalizadaLocal(usuarioLogeado.idLocal);
    tablasvacias();
}

function btnverReservasPendientes(){
    mostrarSeleccionada("divVerReservasPendiente", divsIdFunciones);
    let tabla = document.querySelector("#tablasReservasPendientesCliente")
    cargaTablaPendientesCliente(usuarioLogeado.idCliente,tabla,false);
}

function btnSolicitarReserva(){
    mostrarSeleccionada("divSolicitarReserva", divsIdFunciones);
    cargaTablaSolicitarReserva();
}

function btnCancelarReserva() {
    let tabla = document.querySelector("#tablaReservasPendientesClienteCancelar");
    mostrarSeleccionada("divCancelarReserva", divsIdFunciones);
    cargaTablaPendientesCliente(usuarioLogeado.idCliente,tabla,true);
}

function btnCalificarReserva(){
    mostrarSeleccionada("divCalificarReserva", divsIdFunciones);
    cargaTablaCalificarReservas();
}

function btnVisualizarEstadisticasVerCliente(){
    mostrarSeleccionada("divVerEstadisticaCliente", divsIdFunciones);
    cargaTablaEstadisticasCliente();
    cargaTablaEstadisticasClienteMayor();
    tablasvacias();
}

function btnCerrarSesion(){
    cerrarSesion();
}

function btnRegistroCliente(){
    mostrarSeleccionada("divRegistro",divsIdFunciones);
}
/*Funciones para botones dentro de local*/
function btnModificarCupoMaxlocal(){
    let cupo = parseInt(document.querySelector("#modificarCupoMax").value);
    if(!isNaN(cupo)){
        modificarCupoLocal(usuarioLogeado.idLocal,cupo);
    }else{
        alert("Revisar el dato ingresado.")
    }
}
function btnCargaEstadisticasLocal(){
}
/*Funciones para manejo de botones dentro de la aplicacion Local */
function btnparaHabilitarReservas() { 
    let estado = document.querySelector("#modificarEstadoDeReserva").value;
    habilitacionReservaLocal(usuarioLogeado.idLocal,estado);
    let label = document.querySelector("#estadoActualdeReserva"), esta = "";
    if(usuarioLogeado.estaHabilitado === true){
        esta = "Habilitado para reservas"
    }else{
        esta = "Deshabilitado para reservas"
    }
    label.innerHTML = esta;
}
function btnparaModificarCupo() { 
    let cupo = parseInt(document.querySelector("#modificarCupoMax").value);
    modificarCupoLocal(idUsuarioLogeado,cupo);
 }
function btnparaFinalizarReserva(){
    let idreserva = parseInt(document.getElementById("celdaReservaAFinalizar").innerHTML);
    finalizarReserva(idreserva);
    document.querySelector("#tablaReservasPendientesLocalSelecc").innerHTML = "";
    cargaTablaReservaFinalizadaLocal(usuarioLogeado.idLocal);
    tablasvacias();
}
function btnBuscarCliente(){
    let nom = document.querySelector("#clienteABuscar").value;
    cargaTablaBuscarReservasCliente(nom,usuarioLogeado.idLocal);
}
/*Funciones para manejo de botones dentro de la aplicacion cliente.
-Funcion que invocara la solicitud de una nueva reserva, filtrando que se haya seleccionado un local, e ingresado el cupo.*/
function btnSolicitarNuevaReserva() { 
    let local = parseInt(document.getElementById("reservaSeleccionadaCliente").getAttribute("value"));
    let cupo = parseInt(document.querySelector("#cupoNuevaReserva").value);
    if(isNaN(cupo)||cupo <= 0){
        alert("El cupo no puede ser 0 o menor.")
    }else{
        if(agregarSolicitarReserva(contenedorLocales[local],usuarioLogeado,cupo)){
            alert("Se genero reserva!")
            document.querySelector("#cupoNuevaReserva").innerHTML = "";
            document.querySelector("#reservaSeleccionadaCliente").innerHTML = "";
            document.getElementById("reservaSeleccionadaCliente").removeAttribute("value");
        }else{
            alert("Verificar que se haya seleccionado un restaurante y/o ingresado.")
        }
    }
 }
 /*Funcion que invocara la cancelacion de una reserva, filtrando que se haya seleccionado una reserva para cancelar.*/
function btnCancelarReservaSelec(){
    id = parseInt(document.querySelector("#celdaReservaACancelar").innerHTML);
    tabla = document.querySelector("#tablaReservaSeleccionadaCancelar");
    tablaAux =document.querySelector("#tablaReservasPendientesClienteCancelar");
    if(isNaN(id)){
        alert("Verificar que se haya seleccionado una reserva.");
    }else{
        cancelarReserva(id);
        tabla.innerHTML = "";
        tablasvacias();
        cargaTablaPendientesCliente(usuarioLogeado.idCliente,tablaAux,true);
        alert("Se cancelo reserva con ID:" + id);
    }
}
/*Funcion que invocara la calificacion de una reserva, filtrando que los campos necesarios no esten */
function btnEnviarPuntajeReserva(){
    let resena = document.getElementById("comentarioCalificacionReserva").value;
    let puntos = parseInt(document.getElementById("puntajeCalificacionReserva").value);
    let reserva = document.getElementById("reservaAPuntaje").getAttribute("value");
    if(resena === ""){
        alert("Verifique que haya seleccionado una reserva o que este ingresado el comentario");
    }else{
        calificarReserva(reserva,puntos,resena);
        cargaTablaCalificarReservas();
        tablasvacias();
        alert ("Se genero calificacion");
    }
}
/*Funcion general para que las tablas sin contenido presenten un mensaje automatizado. */
function tablasvacias(){
    let p = document.querySelectorAll("tbody");
    for(let i = 0; i < p.length; i++){
        if(p[i].innerHTML === ""){
            p[i].innerHTML = "<tr><td colspan = '4'>No hay informacion para mostrar</td></tr>"
        }
    }
}