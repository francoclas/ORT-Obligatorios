using System;
using Biblioteca;
namespace Obligatorio_1Er_Entrega
{
    class Sistemas
    {
        static void Main(string[] args)
        {
            Sistema sistema = Sistema.GetInstancia();
            bool seguirEjecutando = true;
            while (seguirEjecutando)
            {
                Console.Clear();
                Console.WriteLine("Digite opcion a la que quiere dirigirse 1-6 || 0 para salir.");
                Console.WriteLine("1-Alta periodista");
                Console.WriteLine("2-Asignar valor de mercado referencia para categoria");
                Console.WriteLine("3-Listar jugador por ID");
                Console.WriteLine("4-Listar jugadores expulsados");
                Console.WriteLine("5-Buscar partido con mas goles de una seleccion");
                Console.WriteLine("6-Listar jugador con almenos un gol.");
                Console.WriteLine("0-Para salir de la aplicacion");
                string comp = Console.ReadLine();
                switch (comp)
                {
                    case "1":
                        string nombre, apellido, mail, pass;
                        Console.WriteLine("Registro de Periodista");
                        Console.WriteLine("Ingrese el nombre de periodista.");
                        nombre = Console.ReadLine();
                        Console.WriteLine("Ingrese el apellido de periodista.");
                        apellido = Console.ReadLine();
                        Console.WriteLine("Ingrese el correo de periodista.");
                        mail = Console.ReadLine();
                        Console.WriteLine("Ingrese la contraseña de periodista.");
                        pass = Console.ReadLine();

                        try
                        {
                            Periodista periodistaNuevo = new Periodista(nombre, apellido, mail, pass);
                            sistema.AltaPeriodista(periodistaNuevo);
                            Console.WriteLine("Se dio de alta: " + periodistaNuevo.ToString());
                            
                        }
                        catch (Exception e)
                        {
                            Console.WriteLine(e.Message);
                        }
                        Console.WriteLine("Presione una tecla para salir");
                        Console.ReadKey();
                        break;
                    case "2":
                        Console.Clear();
                        Console.Write("Ingrese valor para modificar el valor de referencia del mercado:");
                        string valRead = Console.ReadLine();
                        int val = -1;
                        try
                        {
                            val = Int32.Parse(valRead);
                        }
                        catch (Exception)
                        {
                            Console.WriteLine("No se ingreso un numero valido");
                        }
                        try
                        {
                            sistema.ModificarValorReferencia(val);
                            Console.WriteLine("Se cambio el valor de referencia por:" + val);
                        }
                        catch (Exception e)
                        {

                            Console.WriteLine(e.Message);
                        }

                        Console.WriteLine("Presione una tecla para salir");
                        Console.ReadKey();
                        break;
                    case "3":
                        Console.WriteLine("Ingrese ID del jugador a buscar:");
                        string Id3 = Console.ReadLine();
                        int IDP3 = -1;
                        try
                        {
                             IDP3 = Int32.Parse(Id3);
                        }
                        catch
                        {
                            Console.WriteLine("No se ingreso un numero valido");
                        }
                        try
                        {
                            foreach (var item in sistema.ObtenerPartidoJugador(sistema.GetJugador(IDP3)))
                            {
                                Console.WriteLine(item.SalidaInc());
                            }
                        }
                        catch (Exception e)
                        {

                            Console.WriteLine(e.Message);
                        }
                        Console.WriteLine("Presione una tecla para salir");
                        Console.ReadKey();
                        break;
                    case "4":
                        try
                        {
                            foreach (var item in sistema.ObtenerJugadoresExpulsados())
                            {
                                Console.WriteLine(item);
                            }
                        }
                        catch (Exception e)
                        {

                            throw e;
                        }
                        Console.ReadKey();
                        Console.WriteLine("Presione una tecla para salir");
                        Console.ReadKey();
                        break;
                    case "5":
                        try
                        {
                            Console.WriteLine("Ingrese nombre del pais a buscar partido con mas goles:");
                            string pais = Console.ReadLine();
                            Console.WriteLine(sistema.BuscarSelecMasGoles(pais));
                            
                        }
                        catch (Exception e)
                        {

                            Console.WriteLine(e.Message);
                        }
                        Console.WriteLine("Presione una tecla para salir");
                        Console.ReadKey();
                        break;
                    case "6":
                        Console.WriteLine("Ingrese ID de partido a buscar:");
                        string Id6 = Console.ReadLine();
                        int IDP6 = -1;
                        try
                        {
                            IDP6 = Int32.Parse(Id6);
                        }
                        catch
                        {
                            Console.WriteLine("No se ingreso un numero valido");
                        }
                        try
                        {
                            Console.WriteLine("Lista de jugadores con gol:");
                            foreach (var item in sistema.ObtenerJugadoresGoleador(IDP6))
                            {
                                Console.WriteLine($"{item.NomCompleto}-{item.ValMercado}{item.TipoMoneda}-Es usuario vip: {item.esVip}");
                            }
                            
                        }
                        catch (Exception e)
                        {

                            Console.WriteLine(e.Message);
                        }
                        Console.WriteLine("Presione una tecla para salir");
                        Console.ReadKey();
                        break;
                    case "0":
                        seguirEjecutando = false;
                        break;

                
                }

            }

        }
    }
}
