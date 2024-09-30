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
        //Registro equipos
            Sis.registrarEquipo("DragonesGaming", "Diego Rivas");
            Sis.registrarEquipo("FénixEternal", "Sofía Núñez");
            Sis.registrarEquipo("ShadowWarriors", "Lucas Pereira");
            Sis.registrarEquipo("CyberTitans", "Valentina Gómez");
            Sis.registrarEquipo("NovaSquad", "Martín Suárez");
        //Registro jugadores
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
        //Agrego algunos jugadores a equipos


        //Hago listados de equipos
            //Se llama al sistema, se guarda el retorno y se muestra el valor string
            Retorno ListaEquiposDes = Sis.listarEquiposDescendente();
            System.out.println("Listado de equipos descendente:");
            System.out.println(ListaEquiposDes.getValorString());
            System.out.println("----------------------------------------------------------------------");
        //Listado de jugadores
            //Igual que con los equipos, obtengo el retorno desde sistema y lo imprimo

    }
}
