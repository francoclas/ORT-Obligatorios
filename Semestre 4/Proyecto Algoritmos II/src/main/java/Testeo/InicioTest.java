package Testeo;

import interfaz.Categoria;
import interfaz.Retorno;
import sistema.ImplementacionSistema;


/*
    Esta clase tiene  como funcion probar todos los ejercicios solicitados por la letra del obligatorio.

 */
public class InicioTest {
    public static void main(String[] args) {

        ImplementacionSistema Sis = new ImplementacionSistema();
        Sis.inicializarSistema(5);
        //Registro equipos - Ejercicio 6
            Sis.registrarEquipo("DragonesGaming", "Diego Rivas");
            Sis.registrarEquipo("FénixEternal", "Sofía Núñez");
            Sis.registrarEquipo("ShadowWarriors", "Lucas Pereira");
            Sis.registrarEquipo("CyberTitans", "Valentina Gómez");
            Sis.registrarEquipo("NovaSquad", "Martín Suárez");
        //Registro jugadores - Ejercicio 2
            Sis.registrarJugador("ShadowHunter", "Lucas", "Pereira", Categoria.PROFESIONAL);
            Sis.registrarJugador("PhoenixFire", "Sofía", "Núñez", Categoria.ESTANDARD);
            Sis.registrarJugador("CyberKnight", "Diego", "Rivas", Categoria.PRINCIPIANTE);
            Sis.registrarJugador("DragonSlayer", "Valentina", "Gómez", Categoria.PROFESIONAL);
            Sis.registrarJugador("NovaStorm", "Martín", "Suárez", Categoria.ESTANDARD);
            Sis.registrarJugador("BladeMaster", "Andrea", "Santos", Categoria.PROFESIONAL);
            Sis.registrarJugador("ThunderStrike", "José", "González", Categoria.ESTANDARD);
            Sis.registrarJugador("NightWolf", "Laura", "Martínez", Categoria.PRINCIPIANTE);
            Sis.registrarJugador("IronFist", "Pablo", "Rodríguez", Categoria.PROFESIONAL);
            Sis.registrarJugador("SilentArrow", "Camila", "Fernández", Categoria.ESTANDARD);
            Sis.registrarJugador("StormBringer", "Diego", "López", Categoria.PROFESIONAL);
        //Agrego algunos jugadores a equipos - Ejercicio 7
                Sis.agregarJugadorAEquipo("DragonesGaming", "ShadowHunter");
                Sis.agregarJugadorAEquipo("DragonesGaming", "BladeMaster");

                Sis.agregarJugadorAEquipo("FénixEternal", "PhoenixFire");
                Sis.agregarJugadorAEquipo("FénixEternal", "SilentArrow");

                Sis.agregarJugadorAEquipo("ShadowWarriors", "CyberKnight");
                Sis.agregarJugadorAEquipo("ShadowWarriors", "ThunderStrike");

                Sis.agregarJugadorAEquipo("CyberTitans", "DragonSlayer");
                Sis.agregarJugadorAEquipo("CyberTitans", "IronFist");

                Sis.agregarJugadorAEquipo("NovaSquad", "NovaStorm");
                Sis.agregarJugadorAEquipo("NovaSquad", "NightWolf");

        //Hago listados de equipos - Ejercicio 9
            //Se llama al sistema, se guarda el retorno y se muestra el valor string
            Retorno ListaEquiposDes = Sis.listarEquiposDescendente();
            System.out.println("Listado de equipos descendente:");
            System.out.println(ListaEquiposDes.getValorString());
            System.out.println("----------------------------------------------------------------------");
        //Listado de jugadores - Ejercicio 4
            //Igual que con los equipos, obtengo el retorno desde sistema y lo imprimo
                Retorno ListaJugadoresAsc = Sis.listarJugadoresAscendente();
                System.out.println("Listado de Jugadores:");
                System.out.println(ListaJugadoresAsc.getValorString());
                System.out.println("----------------------------------------------------------------------");
        //Listado de jugadores de equipos - Ejercicio 8
            Retorno ListaJugadoresEquipo = Sis.listarJugadoresDeEquipo("DragonesGaming");
            System.out.println("Listado de jugadores de DragonesGaming");
            System.out.println(ListaJugadoresEquipo.getValorString());
            System.out.println("----------------------------------------------------------------------");
        //Listar por jugadores por categoria
            Retorno ListaJugadoresCategoria = Sis.listarJugadoresPorCategoria(Categoria.ESTANDARD);
            System.out.println("Listado de jugadores profesionales");
            System.out.println(ListaJugadoresCategoria.getValorString());
            System.out.println("----------------------------------------------------------------------");
        //Buscar un juugador
            Retorno BuscarJugador = Sis.buscarJugador("sdsd");
        System.out.println("Resultado de jugador:");
        System.out.println(BuscarJugador.getValorString());
        System.out.println(String.valueOf(BuscarJugador.getResultado()));
        System.out.println(String.valueOf(BuscarJugador.getValorInteger()));
        System.out.println("----------------------------------------------------------------------");


    }
}
