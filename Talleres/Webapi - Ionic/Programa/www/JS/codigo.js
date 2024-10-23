/*Globales*/
const URLIMG = "https://babytracker.develotion.com/imgs/";
const URLBASE = "https://babytracker.develotion.com";
const ruteo = document.querySelector("#ruteo");
const menu = document.querySelector("#menu");
var map;
let latUsuario, longUsuario;
var Logueados;
var SinSesion
Inicio();
function Inicio() {
  //guardo las coordenadas del usuario
  navigator.geolocation.getCurrentPosition(guardarCoords, ImprimirError);
  //Obtengo las opciones de usuario logueado y sin sesion
  Logueados = document.getElementsByClassName("logueado");
  SinSesion = document.getElementsByClassName("sinsesion");
  AgregarEventos();
  if (localStorage.getItem("Token") == null || localStorage.getItem("Token") == "") {
    //Si no esta logueado, oculto todo y pusheo al login
    OcultarMenuLogueado();
    MostrarMenuSinLog();
    ruteo.push("/Login");
  }
  else {
    MostrarMenuLogueado();
    OcultarMenuSinLog();
    ruteo.push("/eventos");
  }
  document.querySelector("#inicio").style.display = "block";
  //Hago listener del Select de departamenteo en registreo para poder cargar las ciudades
  let dpto = document.querySelector("#selDptoReg");
  dpto.addEventListener("ionChange", CargarCiudades);
  //Listener de captura a Alta Evento
  document.querySelector("#enviarEvento").addEventListener("click", generarEvento);
  //Listener para captura de Baja Evento
  //Listener botones
  document.querySelector("#contenedorHoy").style.display = "none";
  document.querySelector("#contenedorHistorico").style.display = "none";

  document.querySelector("#botonHistorico").addEventListener("click", mostrarHistorico);
  document.querySelector("#botonActual").addEventListener("click", mostrarActual);
}

/*Mostrar u ocultar opciones en menu */
function MostrarMenuLogueado() {
  for (let i = 0; i < Logueados.length; i++) {
    Logueados[i].style.display = "inline";
  }
}
function OcultarMenuLogueado() {
  for (let i = 0; i < Logueados.length; i++) {
    Logueados[i].style.display = "none";
  }
}
function MostrarMenuSinLog() {
  for (let i = 0; i < SinSesion.length; i++) {
    SinSesion[i].style.display = "inline";
  }
}
function OcultarMenuSinLog() {
  for (let i = 0; i < SinSesion.length; i++) {
    SinSesion[i].style.display = "none";
  }
}

function AgregarEventos() {
  ruteo.addEventListener("ionRouteWillChange", MostrarSeccion);
  document.querySelector("#btnLogin").addEventListener("click", Login);
  document.querySelector("#btnRegistroForm").addEventListener("click", RegistroInterfaz);
}

/*Se estima que la funcion recibe por evt, el id del evento a eliminar
  -Luego carga la pagina de Eliminar evento con la info del mismo.
  y queda a espera de la confirmacion de la eliminacion en la funcion "ConfirmarBajaEvento()"
*/
function EnviarEliminacionEvento(idEvento) {
  console.log(idEvento);
  //Obtengo la pagina para poder mostrar la ventana de confirmacion
  let Vista = document.getElementById("#inicio");
  //Genero el alert
  let confirmarBaja = document.createElement('ion-alert');
  confirmarBaja.header = 'Confirmación';
  confirmarBaja.message = `¿Estás seguro de que deseas dar de baja el evento con ID: ${idEvento}?`;
  confirmarBaja.buttons = [
    {
      text: 'Cancelar',
      role: 'cancel',
    },
    {
      text: 'Confirmar',
      role: 'confirm',
      handler: () => {
        //Se confirma, se manda baja mediante api
        apiEliminarEvento(idEvento);
      },
    },
  ];
  //Mando el mensaje a la ventana y lo muestra.
  document.body.appendChild(confirmarBaja);
  confirmarBaja.present();
}


async function Login() {
  try {
    let nombreUsuario = document.querySelector("#txtNombreUsuario").value;
    let password = document.querySelector("#txtPassword").value;
    ValidarDatos(nombreUsuario, password);
    let Usuario = { "usuario": nombreUsuario, "password": password };
    await apiLogin(Usuario);
    //Verifico si se seteo el token o el id, si es el caso ruteo sino tiro error.

  } catch (Error) {
    ImprimirError(Error.message);
  }
}

function cerrarSesion() {
  localStorage.clear();
  document.querySelector("#menuLogin").style.display = "inline";
  document.querySelector("#menuRegistro").style.display = "inline";
  document.querySelector("#menuCerrarSesion").style.display = "none";
  document.querySelector("#menuInicio").style.display = "none";
  ruteo.push("/Login");
}

/* Credenciales y Session */

/* Registro - Login y Logout */
function RegistroInterfaz() {
  //Se obtienen valores del registro
  let nomUsuario = document.querySelector("#txtUserReg").value;
  let pass = document.querySelector("#txtPassReg").value;
  let passConf = document.querySelector("#txtConfPassReg").value;
  let Departamento = document.querySelector("#selDptoReg").value;
  let Ciudad = document.querySelector("#selCiudadReg").value;
  try {
    ValidarNuevoUsuario(nomUsuario, pass, passConf, Departamento, Ciudad);
    let Usuario = {
      "usuario": nomUsuario,
      "password": pass,
      "idDepartamento": Departamento,
      "idCiudad": Ciudad
    }
    //Se manda a la API
    apiRegistro(Usuario);
  } catch (Error) {
    ImprimirError(Error.message);
  }


}
/*Eventos*/

//Alta Evento
function generarEvento() {
  //Obtengo los datos para la nueva alta de evento.
  let CatID = document.querySelector("#selCategoria").value;
  let Detalle = document.querySelector("#detalle").value;
  let fecha = document.querySelector("#fechaEvento").value;
  try {
    //Reviso info ingresada.
    if (CatID == undefined)
      throw new Error("Debe seleccionar una categoria");
    //Reviso la fecha
    if (fecha == undefined) {
      //Si no selecciono fecha la seteo.
      fecha = "";
    } else {
      fecha = formatearFecha(fecha);
      ValidarFecha(fecha);
    }
    //Reviso la descripcion.
    if (Detalle == undefined) {
      //Si esta sin definir, lo dejo vacio.
      Detalle = "";
    }
    //Genero la insancia de Evento.
    let nuevoEvento = {
      "idCategoria": CatID,
      "idUsuario": localStorage.getItem("IDUsuario"),
      "detalle": Detalle,
      "fecha": fecha
    }
    apiEnviarEvento(nuevoEvento);
    //Si sale todo bien, mando al inicio para que cargue el evento nuevo
    ruteo.push("/");
  } catch (Error) {
    ImprimirError(Error.message);
  }

}

/* Conexion con API */


/* API : Registro - Login y Logout */
//Registro
function apiRegistro(Usuario) {
  fetch(URLBASE + "/usuarios.php",
    {
      method: "Post",
      headers: {
        "Content-type": "application/json"
      },
      body: JSON.stringify(Usuario)
    }
  )
    //Registro correcto
    .then(function (response) {
      return response.json();
    })
    .then(function (data) {

      if (data.codigo == 200) {
        //Iniciar sesion
        //Guardar token e id en localstorage
        localStorage.setItem("Usuario", Usuario.nomUsuario);
        localStorage.setItem("Token", data.apiKey);
        localStorage.setItem("IDUsuario", data.id);
        //Redirecciono a inicio de 
        ruteo.push("/");
      }
      if (data.codigo == 409) {
        throw new Error(data.mensaje);
      }
    })
    .catch(function (Error) {
      ImprimirError(Error.message);
    })
}

function apiLogin(usuario) {
  //El usuario recibido tiene unicamente usuario y contraseña a diferencia del registro
  fetch("https://babytracker.develotion.com/login.php",
    {
      method: "Post",
      headers: {
        "Content-type": "application/json"
      },
      body: JSON.stringify(usuario)
    }
  )
    .then(function (response) {
      console.log(response);
      return response.json();
    })
    .then(function (data) {
      if (data.codigo == 200) {
        //Vuelvo a guardar credenciales
        console.log(data);
        localStorage.setItem("Usuario", usuario.usuario);
        localStorage.setItem("Token", data.apiKey);
        localStorage.setItem("IDUsuario", data.id);
        OcultarMenuSinLog();
        MostrarMenuLogueado();
        ruteo.push("/");
      }
      if (data.codigo == 409) {
        ImprimirError("Revisar las credenciales utilizadas");
      }
    })
    .catch(function (Error) {
      ImprimirError(Error);

    })
}

function Logout() {
  ruteo.push("/login");
  OcultarMenuLogueado();
  //Limpio el LocalStorage
  localStorage.clear();
  //Redirecciono a Inicio sin sesion
  MostrarMenuSinLog();

}
/* API : Eventos - Agregar, Listar y eliminar */

//Listar Deptos
function CargarDptos() {
  //Seteo select en registro, y lo limpio
  let dptos = document.querySelector("#selDptoReg");
  dptos.innerHTML = "";
  fetch(URLBASE + "/departamentos.php",
    {
      method: "Get",
      headers: {
        "Content-type": "application/json"
      }
    }
  )
    .then(function (response) {
      return response.json();
    })
    .then(function (data) {
      if (data.codigo != 200)
        return Promise.reject("No hubo conexion");
      datos = data.departamentos;
      let html = "";
      for (let i = 0; i < datos.length; i++) {
        html += `<ion-select-option value="${datos[i].id}">${datos[i].nombre}</ion-select-option>`
      }
      dptos.innerHTML = html;
    })
    .catch()


}

//Listar citys
function CargarCiudades() {
  let idDepto = document.querySelector("#selDptoReg").value;
  let Ciudades = document.querySelector("#selCiudadReg");
  Ciudades.innerHTML = "";
  fetch(URLBASE + `/ciudades.php?idDepartamento=${idDepto}`,
    {
      method: "Get",
      headers: {
        "Content-type": "application/json"
      }
    }
  )
    .then(function (response) {
      return response.json();
    })
    .then(function (data) {
      if (data.codigo != 200)
        return Promise.reject("No hubo conexion,puede intentar volviendo a iniciar sesion");
      let datos = data.ciudades;
      let html = "";
      for (let i = 0; i < datos.length; i++) {
        html += `<ion-select-option value="${datos[i].id}">${datos[i].nombre}</ion-select-option>`

      }
      Ciudades.innerHTML = html;
    })
}
//Listar Categorias
async function CargarCategorias() {
  let Categorias = document.querySelector("#selCategoria");

  return fetch(URLBASE + "/categorias.php", {
    method: "Get",
    headers: {
      "Content-type": "application/json",
      "apikey": localStorage.getItem("Token"), //Uso LocalStorage
      "iduser": localStorage.getItem("IDUsuario")
    }
  })
    .then(function (response) {
      return response.json();
    })
    .then(function (data) {
      if (data.codigo != 200)
        return Promise.reject(data.mensaje);

      let html = "";
      let cats = data.categorias;
      for (let i = 0; i < cats.length; i++) {
        html += `<ion-select-option value="${cats[i].id}">
              <ion-card>
                <img alt="${cats[i].tipo}" src="${URLIMG + cats[i].imagen}.png" />
                <ion-card-header>
                <ion-card-title>${cats[i].tipo}</ion-card-title>
              </ion-card-header>
          </ion-select-option>`
      }
      Categorias.innerHTML = html;
    })
}
/*Eventos */
//ALTA - evento
function apiEnviarEvento(Evento) {

  fetch(URLBASE + "/eventos.php",
    {
      method: "Post",
      headers: {
        "Content-type": "application/json",
        "apikey": localStorage.getItem("Token"),
        "iduser": localStorage.getItem("IDUsuario")
      },
      body: JSON.stringify(Evento)
    })
    .then(function (response) {
      return response.json();
    })
    .then(function (data) {
      if (data.codigo != 200) {
        throw new Error(data.mensaje);
      }
      ImprimirError("Se dio de alta nuevo evento. Codigo: " + data.idEvento);
    })
    .catch(function (Error) {
      ImprimirError(Error.message);
    })

}
//BAJA - Evento
function apiEliminarEvento(idEvento) {
  fetch(URLBASE + `/eventos.php?idEvento=${idEvento}`,
    {
      method: "DELETE",
      headers: {
        "Content-type": "application/json",
        "apikey": localStorage.getItem("Token"),
        "iduser": localStorage.getItem("IDUsuario")
      }
    }
  )
    .then(function (response) {
      return response.json();
    })
    .then(function (data) {
      ImprimirError(data.mensaje);
      CargarEventos();
    })
  //Como no devuelve informacion, indiferente de si se puede eliminar o no, se puede devolver el mensaje.
}
//Obtener eventos de usuario logueado

async function CargarEventos() {
  try{
    let Eventos = await ObtenerEventos();
    let Categorias = await ObtenerCategorias();
     //Listas
  let EventosDelDia = new Array(), EventosHistorico = new Array();
  let ListaDia = document.querySelector("#listaEventosUsuario");
  let ListaHistorico = document.querySelector("#listaHistorico");
  //Los limpio por las dudas
  ListaDia.innerHTML = "";
  ListaHistorico.innerHTML = "";
  GenerarHistorico(Eventos, EventosDelDia, EventosHistorico);
  console.log(EventosDelDia);
  //Verifico que las listas tengan algo, en caso de que no, muestro mensaje vacio
  if (EventosDelDia.length != 0) {
    generarEventosLista(ListaDia, EventosDelDia, Categorias);
  } else {
    ListaDia.innerHTML = "<ion-item><ion-card>No se registraron eventos hoy!</ion-card></ion-item>"
  }
  if (EventosHistorico.length != 0) {
    generarEventosLista(ListaHistorico, EventosHistorico, Categorias);
  } else {
    ListaHistorico.innerHTML = "<ion-item><ion-card>No se encontraron registros!</ion-card></ion-item>"

  }
  //Obtengo todos los botones de eliminar evento
  let Eliminados = document.getElementsByClassName("eliminarEvento");
  //Genero listener para posible eliminacion de eventos
  for (let i = 0; i < Eliminados.length; i++) {
    Eliminados[i].addEventListener("click", function () {
      //Obtengo id del evento y llamo a funcion de confirmarEliminado
      let ID = Eliminados[i].getAttribute("eventoID");
      EnviarEliminacionEvento(ID);
    })
  }
  }catch(Error){
    ImprimirError(Error.message);
  }
  
 
}
/*Se recibe 3 listas, el total, la lista que va tener las del dia actual
y luego la lista del historico de eventos. */
function GenerarHistorico(Total, Hoy, Historico) {
  let A, D, M, hoy = new Date();
  A = hoy.getFullYear();
  D = hoy.getDay();
  M = hoy.getMonth();
  for (let i = 0; i < Total.length; i++) {
    let fEvento = new Date(Total[i].fecha.replace(" ", "T"))
    if (fEvento.getDay() == D && fEvento.getMonth() == M && fEvento.getFullYear() == A) {
      Hoy.push(Total[i]);
    } else {
      //Para poder obtener unicamente el del dia, sin necesidad de cargar el resto.
      if (Historico != undefined) {
        Historico.push(Total[i]);
      }
    }
  }

}

/*Generador de estadisticas */
async function GenerarEstadisticas() {
  let fechaBiberon = null, fechaPanal = null;
  //Obtengo los labels desde la ventana
  let Biberon = document.querySelector("#cantBiberon");
  let ultBiberon = document.querySelector("#ultBiberon");
  let Panales = document.querySelector("#cantPanales");
  let ultPanal = document.querySelector("#utlPanal");
  let BCant = 0, PCant = 0;
  //Obtengo los eventos del dia
  let evtos = await ObtenerEventos();
  let evtosHoy = new Array()
  GenerarHistorico(evtos, evtosHoy);
  let Categorias = await ObtenerCategorias();
  //Si no registro ningun evento, muestro mensaje vacio
  if (evtosHoy.length != 0) {
    for (let i = 0; i < evtosHoy.length; i++) {
      let Cat = Categorias.find(c => c.id == evtosHoy[i].idCategoria);
      let fechaEvento = new Date(evtosHoy[i].fecha.replace(' ', 'T'));
      if (Cat != undefined) {
        if (Cat.tipo == "Biberón") {
          BCant++;
          //Reviso si es la mas reciente, o en caso de que sea null, le asigno la primera incidencia
          if (fechaBiberon == null || fechaEvento > fechaBiberon)
            fechaBiberon = fechaEvento;
        }
        if (Cat.tipo == "Pañal") {
          PCant++;
          //Reviso si es la mas reciente, o en caso de que sea null, le asigno la primera incidencia
          if (fechaPanal == null || fechaEvento > fechaPanal)
            fechaPanal = fechaEvento;
        }
      }
    }

    if (BCant == 0) {
      Biberon.innerHTML = "No hay resultados"
    } else {
      Biberon.innerHTML = "" + BCant;
      ultBiberon.innerHTML = "Ultimo biberon fue hace: " + calcularDiferencia(fechaBiberon) + " (HH:MM).";
    }
    if (PCant == 0) {
      Panales.innerHTML = "    No hay resultados"
    } else {
      Panales.innerHTML = "    " + PCant;
      ultPanal.innerHTML = "Ultimo pañal fue hace:" + calcularDiferencia(fechaPanal) + " (HH:MM).";
    }

  } else {
    let cont = document.querySelector("#contEstadisticas");
    cont.innerHTML = "<ion-item> ¡No se encontraron registros hoy!</ion-item>"
  }
}


/* Funcion carga eventos en lista, recibe como parametro la lista, en formato document query
  Tambien la lista de eventos a cargar, se estima que recibira la lista con eventos del dia de hoy
  luego se vuelve a llamar con el resto de eventos 
*/
function generarEventosLista(Lista, Eventos, Categorias) {
  let html = "";
  for (let i = 0; i < Eventos.length; i++) {
    let Cat = Categorias.find(c => c.id == Eventos[i].idCategoria);
    //Inicio de item
    if (Cat != undefined) {
      html += `<ion-card>
                    <ion-card-header>
                      <ion-item>
                          <ion-avatar slot="start">
                            <img alt="${Cat.tipo}" src="${URLIMG + Cat.imagen}.png" />
                          </ion-avatar>
                        <ion-label>${Cat.tipo} <ion-label>
                        <ion-card-subtitle>ID: ${Eventos[i].id} </ion-card-subtitle>
                      </ion-item>
                    </ion-card-header>
                    <ion-card-content>
                      <ul>
                        <li>Fecha: ${Eventos[i].fecha}</li>
                      `
      //Verifico si tiene descripcion
      if (Eventos[i].detalle != "") {
        html += `
                        <li>
                          <ion-label>Detalles: ${Eventos[i].detalle} </ion-label>
                        </li>
                        <ion-button slot="end" color="danger" class="eliminarEvento" eventoID="${Eventos[i].id}">Eliminar</ion-button>
                        `
      }
      //Cierre de item
      html += ` </ul>
                    </ion-card-content>
                    </ion-card>`

    }
  }
  Lista.innerHTML = html;
}

//Terminar y probar
function ObtenerCategorias() {
  return fetch(URLBASE + "/categorias.php", {

    method: "Get",
    headers: {
      "Content-type": "application/json",
      "apikey": localStorage.getItem("Token"), //Uso LocalStorage
      "iduser": localStorage.getItem("IDUsuario")
    }
  })
    .then(function (response) {
      return response.json();
    })
    .then(function (data) {
      return data.categorias;
    })
    .catch(function (error) {
      console.log(error);
    })
}
function ObtenerEventos() {
  return fetch(URLBASE + `/eventos.php?idUsuario=${localStorage.getItem("IDUsuario")}`,
    {
      method: "Get",
      headers: {
        "Content-type": "application/json",
        "apikey": localStorage.getItem("Token"),
        "iduser": localStorage.getItem("IDUsuario")
      }

    }
  )
    .then(function (response) {
      return response.json();
    })
    .then(function (data) {
      if (data.codigo != 200){
        if(data.codigo == 401){
          //Si no tiene el token, le cierro la sesion y pido que vuelva a conectarse
          Logout();
          throw new Error("Se vencio su sesion, vuelva a iniciar para recargar");
        }
        throw new Error(data.mensaje);
        
      }else{
      return data.eventos;
      }
    })
    

}
/*Manejo interfaz IONIC */
function MostrarSeccion(evt) {
  OcultarSecciones();
  console.log(evt);
  let rutaDestino = evt.detail.to;
  switch (rutaDestino) {
    case "/":
      GenerarEstadisticas();
      document.querySelector("#inicio").style.display = "block";
      break;
    case "/Registro":
      CargarDptos();
      document.querySelector("#registro").style.display = "block";
      break;
    case "/Login":
      document.querySelector("#login").style.display = "block";
      break;
    case "/Logout":
      cerrarSesion();
      break;
    case "/eventos":
      document.querySelector("#eventos").style.display = "block";
      CargarEventos();
      break;

    case "/listarEvento":
      document.querySelector("#listarEvento").style.display = "block";
      break;
    case "/altaEvento":
      CargarCategorias();
      document.querySelector("#altaEvento").style.display = "block";
      break;
    case "/plazas":
      CargarPlazas();
      document.querySelector("#plazas").style.display = "block";
      break;

  }
}
function mostrarHistorico() {
  //Muestro div de historico
  document.querySelector("#contenedorHistorico").style.display = "block";
  //Oculto div de eventos del dia
  document.querySelector("#contenedorHoy").style.display = "none";

}
function mostrarActual() {
  //Muestro div del dia
  document.querySelector("#contenedorHoy").style.display = "block";
  //Oculto div de historico
  document.querySelector("#contenedorHistorico").style.display = "none";
}

function cerrarMenu() {
  menu.close();
}
function OcultarSecciones() {
  let divs = document.querySelectorAll(".ion-page");
  for (let i = 1; i < divs.length; i++) {
    divs[i].style.display = "none";
  }
}

/*Errores */
function ImprimirError(Mensaje) {
  let toast = document.createElement("ion-toast");
  toast.message = Mensaje;
  toast.duration = 2000;
  toast.position = "bottom";
  toast.present();
  document.body.appendChild(toast);
}

/*Funciones del mapa */
//Cargar las listas en el map
async function CargarPlazas() {
  if (map != null)
    map.remove();
  let Plazas = await apiObtenerPlazas();
  console.log(latUsuario, longUsuario);
  //Seteo el mapa
  map = L.map('map').setView([latUsuario, longUsuario], 13);
  L.tileLayer('https://tile.openstreetmap.org/{z}/{x}/{y}.png', {
    maxZoom: 19,
    attribution: '&copy; <a href="http://www.openstreetmap.org/copyright">OpenStreetMap</a>'
  }).addTo(map);
  //Marco la posicion del usuario en el mapa
  L.marker([latUsuario, longUsuario]).addTo(map).bindPopup("Papi esta aqui");

  //Genero los marcadores de las plazas
  for (let i = 0; i < Plazas.length; i++) {
    if (Plazas[i] != undefined) {
      let html = "";
      if (Plazas[i].accesible == 1) {
        html += "<p>Es accesible</p>"
      } else {
        html += "<p>No es accesible</p>"
      }
      if (Plazas[i].aceptaMascotas == 1) {
        html += "<p>Acepta mascotas</p>"
      } else {
        html += "<p>No acepta mascotas</p>"

      }
      L.marker([Plazas[i].latitud, Plazas[i].longitud], { icon: IconoMapa }).addTo(map).bindPopup(html);
    }

  }
}
function guardarCoords(position) {
  latUsuario = position.coords.latitude;
  longUsuario = position.coords.longitude;
}

//Obtener la lista de plazas
function apiObtenerPlazas() {
  return fetch(URLBASE + "/plazas.php",
    {
      method: "Get",
      headers: {
        "Content-type": "application/json",
        "apikey": localStorage.getItem("Token"),
        "iduser": localStorage.getItem("IDUsuario")
      }
    }
  )
    .then(function (response) {
      return response.json();
    })
    .then(function (data) {
      if (data.codigo == 200) {
        return data.plazas;
      } else {
        throw new Error(data.mensaje);
      }

    })
    .catch(function (error) {
      ImprimirError(error.message);
    }
    )
}
//Iconos extra
var IconoMapa = L.icon({
  iconUrl: 'mapIcon.png',

  iconSize: [50, 50], // size of the icon
  shadowSize: [50, 64], // size of the shadow
  iconAnchor: [22, 94], // point of the icon which will correspond to marker's location
  shadowAnchor: [4, 62],  // the same for the shadow
  popupAnchor: [-3, -76] // point from which the popup should open relative to the iconAnchor
});
/*Manejo y formateo de informacion */
  //Se pasa la hora del ultimo evento, sea de biberones o de pañales y devuelve la cantidad de tiempo que se hizo.
  function calcularDiferencia(Fecha) {
    let ahora = new Date();
    let diferenciaMilisegundos = ahora - Fecha;
    // Convertir la diferencia en horas y minutos
    let horas = Math.floor(diferenciaMilisegundos / (1000 * 60 * 60));
    let minutos = Math.floor((diferenciaMilisegundos % (1000 * 60 * 60)) / (1000 * 60));
    // Formatear el resultado como HH:mm
    let horasFormateadas = String(horas).padStart(2, '0');
    let minutosFormateados = String(minutos).padStart(2, '0');

    return `${horasFormateadas}:${minutosFormateados}`;
  }

/* Validaciones de datos */
//Registro e Inicio
function ValidarNuevoUsuario(Usuario, pass, confPass, Departamento, Ciudad) {
  if (Usuario.trim().length == 0) {
    throw new Error("El usuario es obligatorio");
  }
  if (Departamento == "" || Departamento == undefined) {
    throw new Error("Debe seleccionar Departamento");
  }
  if (Ciudad == undefined || Ciudad.trim().length == 0) {
    throw new Error("Debe seleccionar Ciudad");
  }
  if (pass.trim().length == 0) {
    throw new Error("La password es obligatoria");
  }
  if (confPass.trim().length == 0) {
    throw new Error("La confirmacion de password es obligatoria");
  }
  if (pass.trim() != confPass.trim()) {
    throw new Error("La password y la confirmación de la misma no coinciden");
  }
}
function ValidarInicioUsuario(Usuario, Pass) {
  if (Usuario == "" || Usuario == undefined) {
    throw new Error("El usuario es obligatorio");
  }
  if (Pass == "" || Pass == undefined) {
    throw new Error("La password es obligatoria");
  }
}
function ValidarDatos(nombreUsuario, password) {
  if (nombreUsuario.trim().length == 0 || password.trim().length == 0) {
    throw new Error("Los datos no son correctos");
  }
}

function ValidarDatosRegistro(nombre, password, confPassword, depto, ciudad) {
  if (nombre.trim().length == 0) {
    throw new Error("El nombre es obligatorio");
  }
  if (password.trim().length == 0) {
    throw new Error("La password es obligatoria");
  }
  if (password.trim() != confPassword.trim()) {
    throw new Error("La password y la confirmación de la misma no coinciden");
  }
  if (depto) {

  }
}
function ValidarFecha(Fecha) {
  let fe = new Date(Fecha);
  let hoy = new Date();
  if (fe > hoy) {
    throw new Error("No se puede agregar una fecha que no llego :o");
  }
}
function LimpiarCampos() {
  document.querySelector("#txtNombre").value = "";
  document.querySelector("#txtApellido").value = "";
  document.querySelector("#txtDireccion").value = "";
  document.querySelector("#txtEmail").value = "";
  document.querySelector("#txtPasswordRegistro").value = "";
  document.querySelector("#txtConfPassword").value = "";
}

//Formateo de fechas
function formatearFecha(Fecha) {
  let salida = new Date(Fecha);
  return salida.getFullYear() + '-' +
    (salida.getMonth() + 1).toString().padStart(2, '0') + '-' +
    salida.getDate().toString().padStart(2, '0') + ' ' +
    salida.getHours().toString().padStart(2, '0') + ':' +
    salida.getMinutes().toString().padStart(2, '0') + ':' +
    salida.getSeconds().toString().padStart(2, '0');
}