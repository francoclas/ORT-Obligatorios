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
            //Se utilizan para el error 4 del ejercicio 7
            Sis.registrarJugador("JugadorEj3", "Diego", "López", Categoria.PROFESIONAL);
            Sis.registrarJugador("JugadorEj4", "Diego", "López", Categoria.PROFESIONAL);
            Sis.registrarJugador("JugadorEj5", "Diego", "López", Categoria.PROFESIONAL);
            Sis.registrarJugador("JugadorEj6", "Diego", "López", Categoria.PROFESIONAL);
            Sis.registrarJugador("JugadorEj7", "Diego", "López", Categoria.PROFESIONAL);
            Sis.registrarJugador("JugadorEj8", "Diego", "López", Categoria.PROFESIONAL);


        //Agrego algunos jugadores a equipos - Ejercicio 7
                Sis.agregarJugadorAEquipo("DragonesGaming", "ShadowHunter");
                Sis.agregarJugadorAEquipo("DragonesGaming", "BladeMaster");

                Sis.agregarJugadorAEquipo("FénixEternal", "SilentArrow");

                Sis.agregarJugadorAEquipo("ShadowWarriors", "CyberKnight");
                Sis.agregarJugadorAEquipo("ShadowWarriors", "ThunderStrike");

                Sis.agregarJugadorAEquipo("CyberTitans", "DragonSlayer");
                Sis.agregarJugadorAEquipo("CyberTitans", "IronFist");

                //Se agregan 5 para poder verificar el error 4
                Sis.agregarJugadorAEquipo("NovaSquad", "NovaStorm");
                Sis.agregarJugadorAEquipo("NovaSquad", "NightWolf");
                Sis.agregarJugadorAEquipo("NovaSquad", "JugadorEj3");
                Sis.agregarJugadorAEquipo("NovaSquad", "JugadorEj4");
                Sis.agregarJugadorAEquipo("NovaSquad", "JugadorEj5");
                Sis.agregarJugadorAEquipo("NovaSquad", "JugadorEj6");
                Sis.agregarJugadorAEquipo("NovaSquad", "JugadorEj8");

                Retorno re = Sis.listarJugadoresDeEquipo("NovaSquad");
        System.out.println(re.getValorString());
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
        //Listar por jugadores por categoria - Ejercicio 5
            Retorno ListaJugadoresCategoria = Sis.listarJugadoresPorCategoria(Categoria.ESTANDARD);
            System.out.println("Listado de jugadores profesionales");
            System.out.println(ListaJugadoresCategoria.getValorString());
            System.out.println("----------------------------------------------------------------------");
        //Buscar un jugador
            Retorno BuscarJugador = Sis.buscarJugador("PhoenixFire");
        System.out.println("Resultado de jugador:");
        System.out.println(BuscarJugador.getValorString());
        System.out.println(String.valueOf(BuscarJugador.getResultado()));
        System.out.println(String.valueOf(BuscarJugador.getValorInteger()));
        System.out.println("----------------------------------------------------------------------");
        /*
            A partir de aca se testea unicamente la salida de errores:
        * */

        System.out.println("Listado de errores:");
            System.out.println("Ejercicio 2:");
            System.out.print("Forzando error 1:");
            Retorno RetEj2Error1 = Sis.registrarJugador("","Hugo","Anthony",Categoria.ESTANDARD);
            System.out.print(RetEj2Error1.getValorString());
            System.out.print(" | Forzando error 2:");
            Retorno RetEj2Error2 = Sis.registrarJugador("ShadowHunter","Hugo","Anthony",Categoria.ESTANDARD);
            System.out.println(RetEj2Error2.getValorString());
        System.out.println("_______________________________________________");
            System.out.println("Ejercicio 3:");
            System.out.print("Forzando error 1:");
            Retorno RetEj3Error1 = Sis.buscarJugador("");
            System.out.print(RetEj3Error1.getValorString());
            System.out.print("Forzando error 2:");
            Retorno RetEj3Error2 = Sis.buscarJugador("luffy");
            System.out.println(RetEj3Error2.getValorString());
        System.out.println("_______________________________________________");
        System.out.println("Ejercicios 4 - 5 - 9 no cuentan con posibles salida de errores");
        System.out.println("_______________________________________________");
            System.out.println("Ejercicio 6:");
            System.out.print("Forzando error 1:");
            Retorno RetEj6Error1 = Sis.registrarEquipo("LosPibeGamer","");
            System.out.print(RetEj6Error1.getValorString());
            System.out.print(" | Forzando error 2:");
            Retorno RetEj6Error2 = Sis.registrarEquipo("CyberTitans","Manager");
            System.out.println(RetEj6Error2.getValorString());
        System.out.println("_______________________________________________");
            System.out.println("Ejercicio 7:");
            System.out.print("Forzando error 1:");
            Retorno RetEj7Error1 = Sis.agregarJugadorAEquipo("","HUgoGamer");
            System.out.print(RetEj7Error1.getValorString());
            System.out.print(" | Forzando error 2:");
            Retorno RetEj7Error2 = Sis.agregarJugadorAEquipo("LosPibesGamer","Ejemplo");
            System.out.print(RetEj7Error2.getValorString());
            System.out.print(" | Forzando error 3:");
            Retorno RetEj7Error3 = Sis.agregarJugadorAEquipo("CyberTitans","huguito");
            System.out.print(RetEj7Error3.getValorString());
            System.out.print(" | Forzando error 4:");
            Retorno RetEj7Error4 = Sis.agregarJugadorAEquipo("NovaSquad", "JugadorEj7");
            System.out.print(RetEj7Error4.getValorString());
            System.out.print(" | Forzando error 5:");
            Retorno RetEJ7Error5 = Sis.agregarJugadorAEquipo("FénixEternal", "PhoenixFire");
            System.out.print(RetEJ7Error5.getValorString());
            System.out.print(" | Forzando error 6:");
            Retorno RetEj7Error6 = Sis.agregarJugadorAEquipo("CyberTitans", "JugadorEj6");
            System.out.println(RetEj7Error6.getValorString());
    }
}
