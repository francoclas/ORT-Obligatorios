package sistema;

import dominio.clases.Jugador;
import dominio.tads.abb.ABB;
import interfaz.*;

public class ImplementacionSistema implements Sistema {
    private ABB JugadoresSist;
    private ABB EquiposSist;
    private ABB SucursalesSist;
    //Funciones Interfaz obligatorio
    @Override
    public Retorno inicializarSistema(int maxSucursales) {
        /*
        * 1- Inicializo los arboles de Jugadores, Sucursales y equipos
        *
        *
        *
        *
        * */


        return Retorno.noImplementada();
    }

    @Override
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
    public Retorno buscarJugador(String alias) {
        /*
           Posibles excepciones
           1-Verifico si el alias esta vacio
           2-Busco dentro del arbol, si no existo tiro excepcion
        */
        if (alias == "" || alias == null) return new Retorno(Retorno.Resultado.ERROR_1,0,"El alias no puede ser vacio o null");
        //Declaro variable String para la posible salida
        String Jugador = ""; // Pendiente: Debo igualarlo al resultado de la funcion de buscar jugador en el arbol
        //Verifico si la busqueda encontro algo, por defecto si no encontro devolvera "";
        if(Jugador.isEmpty()) return new Retorno(Retorno.Resultado.ERROR_2,0,"No se encontro jugador con el alias: " + alias);
        //En este caso encontro devuelvo el string recibido del ToString() del jugador, debo setear el valorInteger a la cantidad de elementos recorridos
        return new Retorno(Retorno.Resultado.OK,0, Jugador);
    }

    @Override
    public Retorno listarJugadoresAscendente() {
        /*
           No hay error para devolver, siempre debe devolver la lista.
           Debe ser ordenados por alias alfabeticamente de manera creciente
           Formato: "alias#1;Nombre#1;apellido#1;categoria#1 | alias#2;Nombre#2;apellido#2;categoria#2

         */

        return Retorno.noImplementada();
    }

    @Override
    public Retorno listarJugadoresPorCategoria(Categoria unaCategoria) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno registrarEquipo(String nombre, String manager) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno agregarJugadorAEquipo(String nombreEquipo, String aliasJugador) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno listarJugadoresDeEquipo(String nombreEquipo) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno listarEquiposDescendente() {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno registrarSucursal(String codigo, String nombre) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno registrarConexion(String codigoSucursal1, String codigoSucursal2, int latencia) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno actualizarConexion(String codigoSucursal1, String codigoSucursal2, int latencia) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno analizarSucursal(String codigoSucursal) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno sucursalesParaTorneo(String codigoSucursalAnfitriona, int latenciaLimite) {
        return Retorno.noImplementada();
    }

}
