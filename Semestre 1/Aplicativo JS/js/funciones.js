/* Se plantea el archivo "funciones.js" desde donde se utilizaran funciones que refieren al manejo de datos del programa, alta de usuarios,reservas, etc. Control sobre funciones que
modifiquen informacion del sistema. */
var contenedorLocales = [];
var contenedorReservas = [];
var contenedorClientes = [];
var rutafotos = "fotos/";

/*Funcion utilizada para generar un nuevo objeto usuario, y almacenarlo en su respectivo contenedor, se espera utilizarlo unicamente en el registro.*/
function registroUsuario(nombre,usuario,contrasena) {  
    if(usuarioExiste(usuario) || !validarContrasena(contrasena)){
        return false;
    }else{
        let persona = new Cliente(contenedorClientes.length,nombre,usuario.toLowerCase(),contrasena);
        contenedorClientes.push(persona)
        return true;
    }
}
/*Se utilzan individualmente para verificar las credenciales, en caso de que sea cliente o local. Se itera mediante los contenedores respectivos en busqueda de una coincidencia.
si corresponde el usuario y la contraseña se verifica el acceso y devuelve verdadero.*/
//Funcion para el cliente.
function auxValidacionCredencialesCliente(usuario,contrasena){
    for(let i = 0; i <contenedorClientes.length; i++){
        if(contenedorClientes[i].verificarCredencialesCliente(usuario,contrasena)){
            return true;
        }
    }
    return false;
}
//Funcion para local
function auxValidacionCredencialesLocal(usuario,contrasena){
    for(let i = 0; i <contenedorLocales.length; i++){
        if(contenedorLocales[i].verificarCredencialesLocal(usuario,contrasena)){
            return true;
        }
    }
    return false;
}
/*Funcion que valida la existencia de un usuario para la gestion de un nuevo usuario en el registro.*/
function usuarioExiste(usuario){
    for (let i = 0 ; i < contenedorClientes.length; i++){
        let aux = contenedorClientes[i];
        if(aux.usuarioCliente === usuario){
            return true;
        }
    }
    return false;
}
/*Funcion para obtener el ID del usuario, a partir de su nombre de usuario.*/
function obtenerIdUsuario(usuario) { 
    let salida = "";
    for(let i = 0;i < contenedorClientes.length; i++){
        if(contenedorClientes[i].usuarioCliente === usuario){
            salida = contenedorClientes[i];
        }
    }
    return salida
 }
function obtenerIdLocal(usuario){
    let salida = "";
    for(let i = 0;i < contenedorLocales.length; i++){
        if(contenedorLocales[i].usuarioLocal === usuario){
            salida = contenedorLocales[i];
        }
    }
    return salida
}
/*Funcion que valida las pautas para que la contraseña sea valida, es decir que tenga almenos 6 caracteres, una mayuscula, una miniscula y un numero. */
function validarContrasena(contrasena){
    if(contrasena.length < 6){ 
        return false
    }else{
    let tieneMay = false, tieneMin = false, tieneNum = false;
        for(let i = 0 ; i < contrasena.length; i++) {
            let charAux = contrasena.charAt(i);
            if(!isNaN(charAux)){
                tieneNum = true;
            } else if (charAux===charAux.toLowerCase()){
                tieneMin = true;
            } else if (charAux===charAux.toUpperCase()){
                tieneMay = true;
            }
        }
        if(!tieneMay || !tieneMin || !tieneNum){
            return false;
        }else{
            return true;
        }
    }

}
/*Se accede al local a modificar y se cambia el estado segun el nuevo recibido.*/
function habilitacionReservaLocal(idlocal,estado){
    contenedorLocales[idlocal].modificarHabilitacion(estado);
}
//Funcion que devuelve el estado del local.
function localEstahabilitado(idlocal){
    return contenedorLocales[idlocal].estaHabilitado;
}
//Funcion que cambia el estado de la reserva enviada al estado "Finalizado", a su vez actualiza el cupo del local.
function finalizarReserva(idReserva){
    contenedorReservas[idReserva].finReserva();
    contenedorLocales[contenedorReservas[idReserva].local.idLocal].actualizarCupoActual(+contenedorReservas[idReserva].cupoReserva);
}
//Funcion que modifica el cupo del local, antes se verifica con la funcion "verReservasEnPendientes()" que no haya reservas en estado pendiente.
function modificarCupoLocal(idLocal,nuevoCupo) { 
    if(!verReservasEnPendientes(idLocal)) {
        contenedorLocales[idLocal].modificarCupo(nuevoCupo);
        alert("Se modifico el cupo antiguo" + contenedorLocales[idLocal].cupoMax +" con la nueva cantidad de cupos")
    }else{
        alert("No se puede modificar el cupo si tiene reservas pendientes.")
    }
 }
/*Funciones respecto al manejo de las reservas*/
function calificarReserva(idReserva,puntaje,comentario){
    contenedorReservas[idReserva].enviarCalificacion(puntaje,comentario);
}
//Funcion que llama a la cancelacion de reserva.
function cancelarReserva(idReserva){
    contenedorReservas[idReserva].cancelarReserva();
}
/*Funcion que agrega una nueva reserva, segun el local solicitado, el cliente que este utilizando la aplicacion y el cupo. Se verifica que el cliente NO tenga una reserva  */
function agregarSolicitarReserva(local,cliente,cupo){
    if (local === undefined||cliente=== undefined || cupo === undefined){
        return false;
    } else{
        if(clienteTieneReservaPendiente(local,cliente)||localEstahabilitado(local.idLocal) === false){
            alert("Usuario ya posee una reserva en este local o no esta disponible las reservas!")
        }else{ 
            if(local.cupoActual < cupo){
                alert("La reserva no puede superar el cupo actual! El cupo actual es: " + local.cupoActual);
            }else{ 
                let nuevaReserva = new Reserva(local,cliente,contenedorReservas.length,cupo);
                contenedorReservas.push(nuevaReserva);
                local.actualizarCupoActual(-cupo);
                return true;
            }
        }
    }

}
/*Funcion verifica que el usuario posea o no una reserva en el local. */
function clienteTieneReserva(local,cliente) { 
    for(let i = 0; i < contenedorReservas.length; i++){
        if(contenedorReservas[i].local.idLocal === local.idLocal && contenedorReservas[i].cliente.idCliente=== cliente.idCliente){
            return true;
        }
    }
    return false;
 }
 /*Funcion verifica que el usuario posea o no una reserva pendiente en el local. */
function clienteTieneReservaPendiente(local,cliente){
    for(let i = 0; i < contenedorReservas.length; i++){
        if(contenedorReservas[i].local.idLocal === local.idLocal && contenedorReservas[i].cliente.idCliente=== cliente.idCliente && contenedorReservas[i].estado === "Pendiente"){
            return true;
        }
    }
    return false;
}
 /*Funcion devuelve la cantidad TOTAL de reservas que tiene un cliente, en un local*/
 function reservasClientelocal(idcliente,idlocal){
    let salida = 0;
    for(let i = 0 ; i<contenedorReservas.length; i++){
        if(contenedorReservas[i].local.idLocal === idlocal && contenedorReservas[i].cliente.idCliente=== idcliente){
            salida++;
        }
    }
    return salida;
 }
 /*Funcion que suma todas las reservas pertenecientes a un local, sin importar su estado. */
 function verReservasTotales(id){
    let salida=0;
    for(let i = 0; i< contenedorReservas.length; i ++){
        if(contenedorReservas[i].local.idLocal === id){
            salida++;
        }
    }
    return salida;
 }
 /*Funcion que calculael promedio de calificacion de todas las reservas que posean una. */
 function verPromedioCalificacion(id){
    let prom = 0, contador = 0;
    for(let i = 0; i<contenedorReservas.length; i++){
        if(contenedorReservas[i].local.idLocal===id){
            prom += contenedorReservas[i].puntuacion;
            contador++
        }
    }
    if(isNaN(prom/contador)||prom/contador === 0){
        return "No tiene calificaciones"
    }else {
        return prom/contador;
    }
 }
 /*Funcion que verifica si el local tiene alguna reserva en pendiente, devuelve VERDADERO si encuentra almenos 1, en el contrario devuelve FALSO. */
 function verReservasEnPendientes(id){
    for(let i = 0; i<contenedorReservas.length; i++){
        if(contenedorReservas[i].local.idLocal === id){
            if(contenedorReservas[i].estado === "Pendiente"){
                return true;
            }
        }
    }
    return false;
 }
function filtrarReservasUsuario(idlocal,idcliente){
    let salida = 0;
    for(let i = 0 ; i < contenedorReservas.length; i++){
        if(contenedorReservas[i].local.idLocal === idlocal && contenedorReservas[i].cliente.idCliente === idcliente){
            salida++;
        }
    }
    return salida;
}
/*Funciones para realizar la carga de datos en tablas de LOCAL*/
function cargaTablaReservaFinalizadaLocal(id){
    let tabla = document.querySelector("#tablaReservasPendientesLocal");
    tabla.innerHTML = ""
    let salida = "";
    for(let i = 0; i < contenedorReservas.length; i ++){
        if(contenedorReservas[i].local.idLocal === id && contenedorReservas[i].estado === "Pendiente"){
            salida += "<tr>"
            salida += "<td>" + contenedorReservas[i].idReserva + "</td>"
            salida += "<td>" + contenedorReservas[i].cliente.nombreCliente + "</td>";
            salida += "<td>" + contenedorReservas[i].cupoReserva + "</td>";
            salida += "<td> <input type ='button' class='btnSeleccionReservaFinalizada' value= 'Seleccionar' id='"+i+"'> </td>";
            salida += "</tr>";
        }
        
    }
    tabla.innerHTML = salida;
    let botonesSeleccion = document.querySelectorAll(".btnSeleccionReservaFinalizada");
    for(let k = 0; k < botonesSeleccion.length; k++){
        let boton = botonesSeleccion[k];
        boton.addEventListener("click",seleccionarInfo);
    }
    
}
function cargaTablaBuscarReservasCliente (nomBuscar,id){
    let tabla = document.querySelector("#tablaResultadoBusqueda");
    tabla.innerHTML ="",salida = "";
    for (let i = 0 ; i < contenedorReservas.length; i++) {
        if(contenedorReservas[i].local.idLocal === id && contenedorReservas[i].estado === "Pendiente"){
            if(contenedorReservas[i].cliente.nombreCliente.includes(nomBuscar)){
                salida += "<tr>"
                salida += "<td>" + contenedorReservas[i].idReserva + "</td>"
                salida += "<td>" + contenedorReservas[i].cliente.nombreCliente + "</td>";
                salida += "<td>" + contenedorReservas[i].cupoReserva + "</td>";
                salida += "<td> <input type ='button' class='btnSeleccionReservaFinalizada' value= 'Seleccionar' id='"+i+"'> </td>";
                salida += "</tr>";
            }
        }
    }
    if(salida === ""){
        salida = "<tr><td colspan = '4'>No se encontro informacion</td></tr>";
    }
    tabla.innerHTML = salida;
    let botonesSeleccion = document.querySelectorAll(".btnSeleccionReservaFinalizada");
    for(let k = 0; k < botonesSeleccion.length; k++){
        let boton = botonesSeleccion[k];
        boton.addEventListener("click",seleccionarInfo);
    }
}
function cargaTablaEstadisticasLocal(id){
    let tabla = document.querySelector("#tablaEstadisticasLocal");
    tabla.innerHTML = "";
    let salida = "";
        salida += "<tr>";
        salida += "<td>" + contenedorLocales[id].cupoMax + "</td>";
        salida += "<td>" + contenedorLocales[id].cupoActual + "</td>";
        salida += `<td> ${contenedorLocales[id].cupoMax - contenedorLocales[id].cupoActual} </td>`;    
        salida += "<td>" + verPromedioCalificacion(id) + "</td>";
        salida += "<td>" + verReservasTotales(id) + "</td>";
        salida += "</tr>";
    tabla.innerHTML = salida;
}
function cargaTablaEstadisticasLocalResenas(){
    let tabla = document.querySelector("#tablaResenasEstadisticas");
    let salida = "";
    tabla.innerHTML = "";
    for(let i = 0; i < contenedorReservas.length; i ++){
        if( contenedorReservas[i].local.idLocal === usuarioLogeado.idLocal){
            if(contenedorReservas[i].puntuacion != 0){
                salida += "<tr>";
                salida += "<td>" + contenedorReservas[i].cliente.nombreCliente + "</td>";
                salida += "<td>" + contenedorReservas[i].puntuacion + "</td>";
                salida += "<td>" + contenedorReservas[i].resena + "</td>";
                salida += "</tr>";
            }
        }  
    }
    tabla.innerHTML = salida;
}
function cargaTablaEstadisticasOtrosLocal(){
    let tabla = document.querySelector("#tablaCalificacionesLocal");
    tabla.innerHTML = "";
    let salida = "";
    for(let i = 0; i < contenedorLocales.length; i++){
        salida += "<tr>";
        salida += "<td>" + contenedorLocales[i].nombreLocal + "</td>";
        salida += "<td>" + verPromedioCalificacion(i) + "</td>";
        salida += "</tr>";
    }
    tabla.innerHTML = salida;
}

function seleccionarInfo(){
    tabla = document.querySelector("#tablaReservasPendientesLocalSelecc");
    tabla.innerHTML = "";
    let salida = "";
    salida += "<tr>";
    salida += "<td id='celdaReservaAFinalizar' value='"+this.id+"' >" + contenedorReservas[this.id].idReserva + "</td>";
    salida += "<td>" + contenedorReservas[this.id].cliente.nombreCliente + "</td>";
    salida += "<td>" + contenedorReservas[this.id].cupoReserva + "</td>";
    salida += "</tr>"
    tabla.innerHTML = salida;
}
/*Funciones para realizar la carga de datos en tablas de CLIENTE*/
function cargaTablaPendientesCliente(idCliente,tabla,seCancela) { 
    tabla.innerHTML = "", salida = "";
    for (let i = 0; i < contenedorReservas.length; i++){
        if(contenedorReservas[i].cliente.idCliente===idCliente && contenedorReservas[i].estado === "Pendiente"){
            let idlocal = contenedorReservas[i].local.idLocal;
            salida += "<tr>";
            salida += "<td>"+ contenedorReservas[i].idReserva +"</td>";
            salida += "<td> <img src = '"+rutafotos+contenedorLocales[idlocal].foto+".jpg' width='100' height='100'>";
            salida += "<td>"+ contenedorReservas[i].local.nombreLocal +"</td>";
            salida += "<td>"+ contenedorReservas[i].cupoReserva +"</td>";
            if(seCancela===true){
                salida += "<td> <input type ='button' class='btnSeleccionarCancelado' value= 'Seleccionar' id='"+i+"'> </td>"
            }
            salida += "</tr>";
        }
    }
    tabla.innerHTML = salida
    if(seCancela===true){
        let botonesSeleccion = document.querySelectorAll(".btnSeleccionarCancelado");
        for(let k = 0; k < botonesSeleccion.length; k++){
            let boton = botonesSeleccion[k];
            boton.addEventListener("click",seleccionarReservaACancelar);
        }
    }
 }
function cargaTablaSolicitarReserva(){
    let tabla = document.querySelector("#tablasLocalesParaReservar");
    tabla.innerHTML = "", salida = "";
    for(let i = 0 ; i < contenedorLocales.length; i++){
        if(contenedorLocales[i].estaHabilitado === true){
            salida += "<tr>";
            salida +="<td> <img src = '"+rutafotos+contenedorLocales[i].foto+".jpg' width='100' height='100'>";
            salida += "<td>" + contenedorLocales[i].nombreLocal + "</td>";
            salida += "<td>" + contenedorLocales[i].cupoActual + "</td>";
            salida += "<td>" + verPromedioCalificacion(i) + "</td>";
            salida += "<td>" + contenedorLocales[i].tipo + "</td>";
            salida += "<td> <input type ='button' class='btnSeleccionarparaReserva' value= 'Seleccionar' id='"+i+"-"+contenedorLocales[i].nombreLocal+"'> </td>";
        }
    }
    tabla.innerHTML = salida;
    let botonesSeleccion = document.querySelectorAll(".btnSeleccionarparaReserva");
    for(let k = 0; k < botonesSeleccion.length; k++){
        let boton = botonesSeleccion[k];
        boton.addEventListener("click",seleccionarReserva);
    }
}
function cargaTablaCalificarReservas(){
    let tabla = document.querySelector("#tablaReservasSinClasificar");
    tabla.innerHTML = "";
    let salida = "";

    for(let i = 0; i < contenedorReservas.length; i++){
        if(contenedorReservas[i].cliente.idCliente === usuarioLogeado.idCliente && contenedorReservas[i].estado === "Finalizado" && contenedorReservas[i].puntuacion === 0){
            let idlocal = contenedorReservas[i].local.idLocal
            salida += "<tr>";
            salida +="<td> <img src ='"+rutafotos+contenedorLocales[idlocal].foto +".jpg' width='100' height='100'>";
            salida += "<td>" + contenedorReservas[i].local.nombreLocal + "</td>";
            salida += "<td>" + contenedorReservas[i].cupoReserva + "</td>";
            salida += "<td> <input type ='button' class='btnSeleccionarReservaCalificar' value= 'Seleccionar' id='"+i+"-"+contenedorReservas[i].local.nombreLocal+"'> </td>";
        }
    }
    tabla.innerHTML = salida;
    let botonesSeleccion = document.querySelectorAll(".btnSeleccionarReservaCalificar");
    for(let k = 0; k < botonesSeleccion.length; k++){
        let boton = botonesSeleccion[k];
        boton.addEventListener("click",seleccionarReservaACalificar);
    }
}
function cargaTablaEstadisticasCliente(){
    let tabla = document.querySelector("#tablaReservasFinalizadas");
    tabla.innerHTML = "";
    let salida = "";
    for(let i = 0; i<contenedorLocales.length; i++){
        if(clienteTieneReserva(contenedorLocales[i],usuarioLogeado)){
            let tCliente = reservasClientelocal(i,usuarioLogeado.idCliente);
            let tTotal = verReservasTotales(i);
            salida += "<tr>";
            salida += "<td> <img src = '"+rutafotos+contenedorLocales[i].foto+".jpg' width='100' height='100'>";
                salida += "<td>" + contenedorLocales[i].nombreLocal + "</td>";
                salida += "<td>" + tCliente + "</td>";
                salida += "<td>" + porcentaje(tTotal,tCliente) + "</td>";
                salida += "</tr>";
        }
    }
    tabla.innerHTML= salida;
}
function cargaTablaEstadisticasClienteMayor(){
    let tabla = document.querySelector("#tablaLocalMasReservado");
    tabla.innerHTML= "";
    let comparador = -1, salida = "";
    
    for(let i = 0; i<contenedorReservas.length; i++){
        let aux = reservasClientelocal(usuarioLogeado.idCliente,i);
        if(aux ===0){
        }else if(comparador < aux){
            comparador = aux;
            salida += "<tr>";
            salida += "<td> <img src = '"+rutafotos+contenedorReservas[i].local.foto+".jpg' width='100' height='100'>" +"</td>";
            salida += "<td>"+ contenedorReservas[i].local.nombreLocal +"</td>";
            salida += "<td>"+ aux +"</td>";
            salida += "</tr>"
        }else if(aux===comparador){
            salida += "<tr>"
            salida += "<td> <img src = '"+rutafotos+contenedorReservas[i].local.foto+".jpg' width='100' height='100'>" +"</td>";
            salida += "<td>"+ contenedorReservas[i].local.nombreLocal +"</td>";
            salida += "<td>"+ aux +"</td>";
            salida += "</tr>"
        }
    }
    tabla.innerHTML= salida;
}
/*Funciones auxiliar para seleccionar el local a reservar, reservas para cancelar o calificar.*/
function seleccionarReserva() {
    let label = document.querySelector("#reservaSeleccionadaCliente");
    let salida = this.id.slice(this.id.indexOf("-")+1);
    let id = this.id.slice(0,this.id.indexOf("-"));
    label.innerHTML = salida;
    label.setAttribute("value",id);
  }
function seleccionarReservaACancelar(){
    tabla = document.querySelector("#tablaReservaSeleccionadaCancelar");
    tabla.innerHTML = "";
    let salida = "";
    salida += "<tr>";
    salida += "<td id='celdaReservaACancelar' value='"+this.id+"' >" + contenedorReservas[this.id].idReserva + "</td>";
    salida += "<td> <img src = '"+rutafotos+contenedorReservas[this.id].local.foto +".jpg' width='100' height='100'>";
    salida += "<td>" + contenedorReservas[this.id].local.nombreLocal + "</td>";
    salida += "<td>" + contenedorReservas[this.id].cupoReserva + "</td>";
    salida += "</tr>"
    tabla.innerHTML = salida;
}
function seleccionarReservaACalificar(){
    let label = document.querySelector("#reservaAPuntaje");
    let salida = this.id.slice(this.id.indexOf("-")+1);
    let id = this.id.slice(0,this.id.indexOf("-"));
    label.innerHTML = salida;
    label.setAttribute("value",id);
}
/* Funciones para la carga de informacion por defecto en el sistema. Se implementan las funciones anteriores.*/
function cargaLocales(){
    let Restaurante1 = new Local(0,"Cocina Urbana","cocinaUrbana","Cu.12345","Restaurante","DireccionEjemplo1","100",true);
    let Restaurante2 = new Local(1,"Deleitable","deleitable","De.12345","Restaurante","DireccionEjemplo2","80",true);
    let Restaurante3 = new Local(2,"Cocina Maestro Yi","cocinaMaestroYi","Cm.12345","Restaurante","DireccionEjemplo3","134",true);
    let Museo1 = new Local(3,"Museo artes plasticas","museoPlastico","Mp.12345","Museo","DireccionEjemplo4","200",true);
    let Museo2 = new Local(4,"Museo de musica interactiva","museoMusical","Mm.12345","Museo","DireccionEjemplo5","160",true)
    let Teatro1 = new Local(5,"Teatro Victoria","teatroVictoria","Tv.12345","Teatro","DireccionEjemplo6","500",true);
    let Teatro2 = new Local(6,"Teatro del Canal","teatroCanal","Tc.12345","Teatro","DireccionEjemplo7","350",true);
    nuevoLocal(Restaurante1);
    nuevoLocal(Restaurante2);
    nuevoLocal(Restaurante3);
    nuevoLocal(Museo1);
    nuevoLocal(Museo2);
    nuevoLocal(Teatro1);
    nuevoLocal(Teatro2);
}
function cargaUsuarios(){
    let cliente1 = new Cliente(0,"Nahuel","usNahuel","Na.12345");
    let cliente2 = new Cliente(1,"Diego Gonzalez","usDiego","Dg.12345");
    let cliente3 = new Cliente(2,"Rodrigo Lopez","usRodri","Rl.12345");
    let cliente4 = new Cliente(3,"Francisco Postiglione","kaster","9Za.12345");
    let cliente5= new Cliente(4,"Monkey D Luffy","luffy","Onepiece1.");
    let cliente6 = new Cliente(5,"Alejandro","Alexelcapo","Al.12345");
    let cliente7 = new Cliente(6,"Martincito","martinlol","marTi.123");
    let cliente8 = new Cliente(7,"Bob","Esponja","Crutaceo.23");
    let cliente9 = new Cliente(8,"Alicia","wonderwoman","Socrates09");
    nuevoUser(cliente1);
    nuevoUser(cliente2);
    nuevoUser(cliente3);
    nuevoUser(cliente4);
    nuevoUser(cliente5);
    nuevoUser(cliente6);
    nuevoUser(cliente7);
    nuevoUser(cliente8);
    nuevoUser(cliente9);

}
function cargaReservas(){
    //Se cargan reservas pendientes.
    agregarSolicitarReserva(contenedorLocales[3],contenedorClientes[1],12);
    agregarSolicitarReserva(contenedorLocales[4],contenedorClientes[1],23);
    agregarSolicitarReserva(contenedorLocales[5],contenedorClientes[2],20);
    agregarSolicitarReserva(contenedorLocales[1],contenedorClientes[2],14);
    agregarSolicitarReserva(contenedorLocales[0],contenedorClientes[3],56);
    agregarSolicitarReserva(contenedorLocales[2],contenedorClientes[3],9);
    agregarSolicitarReserva(contenedorLocales[3],contenedorClientes[4],4);
    agregarSolicitarReserva(contenedorLocales[5],contenedorClientes[4],34);
    agregarSolicitarReserva(contenedorLocales[4],contenedorClientes[5],21);
    agregarSolicitarReserva(contenedorLocales[0],contenedorClientes[5],7);
    agregarSolicitarReserva(contenedorLocales[1],contenedorClientes[6],32);
    agregarSolicitarReserva(contenedorLocales[5],contenedorClientes[6],11);
    //Se finalizan reservas pendientes, sin calificar.
    finalizarReserva(9);
    finalizarReserva(10);
    finalizarReserva(11);
}
/*Funcion para cargar los nuevos locales, nuevos usuarios y nuevas reservas, auxiliar de cargalocales, cargausuarios y cargareservas. */
function nuevoUser(user){
    contenedorClientes.push(user);
    console.log("Se cargo usuario" + user)
}
function nuevoLocal (local){
    contenedorLocales.push(local);
    console.log("Se cargo local" + local)
}
function porcentaje(val1,val2){
    if(val1===1){
        return 100;
    }else{
        return (val2*100)/val1;
    }
}