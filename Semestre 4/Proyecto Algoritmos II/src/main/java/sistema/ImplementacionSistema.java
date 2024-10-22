package sistema;

import dominio.clases.Equipo;
import dominio.clases.Jugador;
import dominio.clases.Sucursal;
import dominio.clases.resultadobusquedaJugador;
import dominio.tads.abb.ABB;
import dominio.tads.abb.NodoABBGen;
import dominio.tads.grafo.Grafo;
import interfaz.*;

public class ImplementacionSistema implements Sistema {
    public static int maxSucursalesSet;
    public static int sucursalesActual = 0;
    private ABB<Jugador>JugadoresSist;
    private ABB<Equipo> EquiposSist;
    private ABB<Sucursal> SucursalesSist;
    private Grafo ConexionesSucursales;
    //Funciones Interfaz obligatorio

    //Ejercicio 1 - Inicializar sistema
    @Override
    public Retorno inicializarSistema(int maxSucursales) {
        if (maxSucursales <= 3){
            return Retorno.error1("El maximo de sucursales no puede ser menor o igual a 3");
        }
        maxSucursalesSet = maxSucursales;
        JugadoresSist = new ABB<Jugador>();
        EquiposSist = new ABB<Equipo>();
        SucursalesSist = new ABB<Sucursal>();
        ConexionesSucursales = new Grafo(maxSucursales);
        return Retorno.ok();
    }

    @Override
    //Ejercicio 2 - Instancio un nuevo jugador y lo mando al sistema
    public Retorno registrarJugador(String alias, String nombre, String apellido, Categoria categoria) {
        /*
        Posibles excepciones
         1- Verifico la informacion del jugador recibido
         2- Verifico que no haya un jugador con ese alias
         3- Genero instancia del nuevo jugador y la cargo al arbol de jugador
        */
        //Verifico los valores recibidos
        if (alias.isEmpty() || alias == null || nombre.isEmpty() || nombre == null || apellido.isEmpty() || apellido == null){
            return Retorno.error1("Debe completar todos los campos");
        }
        //Instancio nuevo jugador
        Jugador nuevo = new Jugador(alias,nombre,apellido,categoria);
        //Verifico si existe
        if (JugadoresSist.existe(nuevo))
            return Retorno.error2("Ya existe jugador con ese alias");
        //Si no esta lo cargo
        JugadoresSist.insertar(nuevo);
        return Retorno.ok();
    }

    @Override
    //Ejercicio 3 - Busco jugador desde el sistema
    public Retorno buscarJugador(String alias) {
        /*
           Posibles excepciones
           1-Verifico si el alias esta vacio
           2-Busco dentro del arbol, si no existo tiro excepcion
        */

        if (alias == null || alias == "") return new Retorno(Retorno.Resultado.ERROR_1,0,"El alias no puede ser vacio o null");
        //Declaro variable String para la posible salida
        resultadobusquedaJugador Salida = buscarJugadorRec(alias);
        //Verifico si la busqueda encontro algo, por defecto si no encontro devolvera "";
        if(Salida.valorString == null ||Salida.valorString.isEmpty()) return new Retorno(Retorno.Resultado.ERROR_2,Salida.valorEntero,"No se encontro jugador con el alias: " + alias);
        //En este caso encontro devuelvo el string recibido del ToString() del jugador, debo setear el valorInteger a la cantidad de elementos recorridos
        return new Retorno(Retorno.Resultado.OK,Salida.valorEntero,Salida.valorString);

    }

    @Override
    //Ejercicio 4 - Devuelvo lista de jugadores desde el sistema
    public Retorno listarJugadoresAscendente() {
        /*
           No hay error para devolver, siempre debe devolver la lista.
           Debe ser ordenados por alias alfabeticamente de manera creciente
           Formato: "alias#1;Nombre#1;apellido#1;categoria#1 | alias#2;Nombre#2;apellido#2;categoria#2
         */
        return new Retorno(Retorno.Resultado.OK,0,JugadoresSist.listarAscendentemente());
    }

    @Override
    //Ejercicio 5 -
    public Retorno listarJugadoresPorCategoria(Categoria unaCategoria) {
        return new Retorno(Retorno.Resultado.OK,0,BuscarJugadoresPorCategoria(unaCategoria));


    }

    //Ejercicio 6 - Genero instancia y mando a sistema
    @Override
    public Retorno registrarEquipo(String nombre, String manager) {
        //Verifico que los datos ingresados no esten vacios
        if (nombre.isEmpty() || nombre == null ||manager.isEmpty() || manager == null){
            return Retorno.error1("Debe completar todos los campos");
        }
        //Instancio un nuevo equipo
        Equipo nuevo = new Equipo(nombre,manager);
        //Verifico que no exista ese equipo en el sistema
        if (EquiposSist.existe(nuevo)){
            return Retorno.error2("Ya existe un equipo con ese nombre");
        }
        //Agrego el equipo a mi sistema
        EquiposSist.insertar(nuevo);
        return Retorno.ok();

    }
    //Ejercicio 7 - Obtengo jugador y equipo desde sistema, y agrego
    @Override
    public Retorno agregarJugadorAEquipo(String nombreEquipo, String aliasJugador) {
        //Validacion de datos
            if (nombreEquipo.isEmpty() ||nombreEquipo == null || aliasJugador.isEmpty() || aliasJugador == null){
                return Retorno.error1("Debe completar todos los campos");
            }
        //Obtengo jugador y equipo desde sistema
            Equipo equipoAux = EquiposSist.buscarDato(new Equipo(nombreEquipo));

            if (equipoAux == null){
                return Retorno.error2("No existe equipo con ese nombre");
            }
            Jugador jugAux = JugadoresSist.buscarDato(new Jugador(aliasJugador));
            if(jugAux == null){
                return Retorno.error3("No existe jugador con ese alias");
            }
            //Verifico si jugador es profesional
            if (!jugAux.esPro()){
                return Retorno.error5("El jugador no tiene la categoria profesional");
            }
            //Verifico que el jugador no tiene equipo
            if(jugAux.tieneEquipo()){
                return Retorno.error6("El jugador ya tiene equipo");
            }
            if(equipoAux.getCantJugadores() == 5){
                return  Retorno.error4("El equipo esta completo");
            }
            //Agrego al jugador
            equipoAux.AgregarJugador(jugAux);
            return Retorno.ok();

    }

    @Override
    //Ejercicio 8 - Se busca equipo desde sistema y se devuelve con funcion
    public Retorno listarJugadoresDeEquipo(String nombreEquipo) {
        //Validacion de datos
        if (nombreEquipo.isEmpty() ||nombreEquipo == null){
            return Retorno.error1("El nombre del equipo no puede ser vacio");
        }
        //Obtengo equipo desde sistema
        Equipo equipoAux = EquiposSist.buscarDato(new Equipo(nombreEquipo));
        if (equipoAux == null){
            return Retorno.error2("No existe equipo con ese nombre");
        }
        return new Retorno(Retorno.Resultado.OK,0,equipoAux.ListarJugadores());
    }

    //Ejercicio 9 - Solo devuelve lista de equipos desde sistema
    @Override
    public Retorno listarEquiposDescendente() {
        return new Retorno(Retorno.Resultado.OK,0,EquiposSist.listarDescendentemente());
    }

    //Ejercicio 10 - Genera instancia y manda al sistema
    @Override
    public Retorno registrarSucursal(String codigo, String nombre) {
        //Verifico cantidad de sucursales actual
        if (sucursalesActual == maxSucursalesSet)
            return Retorno.error1("Ya se encuentra el maximo de sucursales");
        //Reviso valores
        if (codigo.isEmpty() || codigo == null || nombre.isEmpty() || nombre == null)
            return Retorno.error2("Verifique los datos ingresados");
        //Instancio Sucursal
        Sucursal SucursalAux = new Sucursal(codigo,nombre);
        if (SucursalesSist.existe(SucursalAux))
            return Retorno.error3("La sucursal ya existe en el sistema");
        /*
        Agrego la sucursal al sistema, y la cargo en la matriz de adyacencia de mi sistema.
        Agrego sucursal a mi grafo de conexiones

        */
        ConexionesSucursales.agregarVertice(SucursalAux.getCodigo());
        SucursalesSist.insertar(SucursalAux);
        return Retorno.ok();
    }

    @Override
    public Retorno registrarConexion(String codigoSucursal1, String codigoSucursal2, int latencia) {
        //Verifico la latencia ingresada
            if (latencia < 0){
                return Retorno.error1("La latencia no puede ser menor a 0");
            }
        //Reviso valor ingresado de las sucursales
            if (codigoSucursal1 == null || codigoSucursal1.isEmpty() || codigoSucursal2 == null || codigoSucursal2.isEmpty()) {
                return Retorno.error2("Ninguno de los valores ingresados puede ser vacio");
            }
        //Verifico que existan las sucursales
            if (!existeSucursal(codigoSucursal1)){
                return Retorno.error3("La primera sucursal ingresada no existe");

            }
            if(!existeSucursal(codigoSucursal2)){
                return Retorno.error3("La segunda sucursal ingresada no existe");
            }
        //Verifico si hay conexion
            if (ConexionesSucursales.sonAdyacentes(codigoSucursal1,codigoSucursal2)){
                return Retorno.error4("Ya existe una conexion entre ambas sucursales");
            }
        //Registro conexion
            ConexionesSucursales.agregarArista(codigoSucursal1,codigoSucursal2,latencia);
            return Retorno.ok();
    }

    @Override
    public Retorno actualizarConexion(String codigoSucursal1, String codigoSucursal2, int latencia) {
        //Verifico la latencia ingresada
        if (latencia < 0){
            return Retorno.error1("La latencia no puede ser menor a 0");
        }
        //Reviso valor ingresado de las sucursales
        if (codigoSucursal1 == null || codigoSucursal1.isEmpty() || codigoSucursal2 == null || codigoSucursal2.isEmpty()) {
            return Retorno.error2("Ninguno de los valores ingresados puede ser vacio");
        }
        //Verifico que existan las sucursales
        if (!existeSucursal(codigoSucursal1)){
            return Retorno.error3("La primera sucursal ingresada no existe");

        }
        if(!existeSucursal(codigoSucursal2)){
            return Retorno.error3("La segunda sucursal ingresada no existe");
        }
        //Verifico si hay conexion
        if (ConexionesSucursales.sonAdyacentes(codigoSucursal1,codigoSucursal2)){
            return Retorno.error4("Ya existe una conexion entre ambas sucursales");
        }
        //Actualizo el valor de la conexion

        return Retorno.ok();

    }

    @Override
    public Retorno analizarSucursal(String codigoSucursal) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno sucursalesParaTorneo(String codigoSucursalAnfitriona, int latenciaLimite) {
        return Retorno.noImplementada();
    }

    //Funciones auxiliares

    //Para ejercio 3
    private resultadobusquedaJugador buscarJugadorRec(String Alias){
        return resultadobuscarJugadorRec(JugadoresSist.obtenerRaiz(),Alias,new resultadobusquedaJugador());
    }
    private resultadobusquedaJugador resultadobuscarJugadorRec(NodoABBGen<Jugador> nodo, String alias,resultadobusquedaJugador Salida){
        //Instancio una clase de resultadobusquedaJugador para poder retornar
        if(nodo != null) {
            Salida.valorEntero += 1;
            //caso base
            if (nodo.getDato().equals(new Jugador(alias))){
                Salida.valorString = nodo.getDato().toString();
                return Salida;
            }else{
                resultadobuscarJugadorRec(nodo.getIzq(),alias,Salida);
                resultadobuscarJugadorRec(nodo.getDer(),alias,Salida);
            }
        }
        return Salida;
    }
    //Como el ejercicio 05, pide hacer un filtro que no corresponde a un objeto generico tipo T, se realizan las siguientes funciones con el objetivo de resolverlo
    private String BuscarJugadoresPorCategoria(Categoria catAux){
        return filtrarJugadores(JugadoresSist.obtenerRaiz(),catAux);
        //Revisar
    }

    private String filtrarJugadores(NodoABBGen<Jugador> nodo, Categoria catAux){
        if (nodo != null){
            //Busco por las ramas
            String SalIzq = filtrarJugadores(nodo.getIzq(),catAux);
            String SalDer = filtrarJugadores(nodo.getDer(),catAux);
            if (nodo.getDato().perteneceCat(catAux)){
                return SalIzq + " | " + nodo.getDato() + " | " +SalDer;
            }else{
                return SalIzq + SalDer;

            }
        }
        return "";

    }
    private boolean existeSucursal(String codSucursal){
        /*
            Tiene como funcion ver si existe la sucursal que se ingresa, se utiliza en registra conexion
            y registrar sucursal.
        */
        Sucursal aux = new Sucursal(codSucursal);
        return SucursalesSist.existe(aux);
    }
}
