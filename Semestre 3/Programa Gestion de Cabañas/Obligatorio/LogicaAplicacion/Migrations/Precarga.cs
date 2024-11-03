using Microsoft.EntityFrameworkCore.Migrations;

namespace LogicaAccesoDatos.Migrations
{
    public partial class Precarga : Migration
    {
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            //Usuarios
            migrationBuilder.InsertData("Usuarios", new[] {"Nombre","Pass","Correo","User"}, new Object[] { "Juan Pérez", "Contra$ena123", "juanperez@gmail.com", "juanperez" } );
            migrationBuilder.InsertData("Usuarios", new[] { "Nombre", "Pass", "Correo", "User" }, new Object[] { "Ana Gómez", "Segura_123", "anagomez@yahoo.com", "anagomez" });
            migrationBuilder.InsertData("Usuarios", new[] { "Nombre", "Pass", "Correo", "User" }, new Object[] { "Luisa Fernández", "Contraseña1!", "luisafernandez@hotmail.com", "luisafernandez" });
            migrationBuilder.InsertData("Usuarios", new[] { "Nombre", "Pass", "Correo", "User" }, new Object[] { "Miguel Torres", "Torres#456", "migueltorres@gmail.com", "migueltorres" });
            migrationBuilder.InsertData("Usuarios", new[] { "Nombre", "Pass", "Correo", "User" }, new Object[] { "María García", "Garcia567!", "mariagarcia@gmail.com", "mariagarcia" });


            //Tipos
            migrationBuilder.InsertData("TipoCabs", new[] { "Nombre", "Desc", "Costo{" }, new object[] { "Cabaña Clásica", "Cabaña sencilla pero acogedora con una habitación y baño privado.", 50000 });
            migrationBuilder.InsertData("TipoCabs", new[] { "Nombre", "Desc", "Costo{" }, new object[] { "Cabaña Premium", "Cabaña amplia y lujosa con dos habitaciones, dos baños privados y jacuzzi.", 5000 });
            migrationBuilder.InsertData("TipoCabs", new[] { "Nombre", "Desc", "Costo{" }, new object[] { "Cabaña Familiar", "Cabaña ideal para familias o grupos grandes con tres habitaciones y dos baños privados.", 150000 });
            migrationBuilder.InsertData("TipoCabs", new[] { "Nombre", "Desc", "Costo{" }, new object[] { "Cabaña de Montaña", "Cabaña rústica ubicada en las montañas con dos habitaciones y baño compartido.", 70000 });
            migrationBuilder.InsertData("TipoCabs", new[] { "Nombre", "Desc", "Costo{" }, new object[] { "Cabaña con Piscina Privada", "Cabaña con una piscina privada y dos habitaciones con baño privado.", 90000 });


            //Cabanas
            migrationBuilder.InsertData("Cabanas", new[] { "Nombre", "Desc", "HayJacuzzi", "HayReserva", "NumHabitacion", "CantPersMax", "Foto", "TipoCabId" }, new Object[] { "Cabaña del Bosque", "Hermosa cabaña rodeada de naturaleza",1,0,101,4,"Cabaña del Bosque_001.jpg", 1});
            migrationBuilder.InsertData("Cabanas", new[] { "Nombre", "Desc", "HayJacuzzi", "HayReserva", "NumHabitacion", "CantPersMax", "Foto", "TipoCabId" }, new Object[] { "Cabaña del Lago", "Cabaña con vista al lago",0,1,105,6, "Cabaña del Lago_001.jpg", 2});
            migrationBuilder.InsertData("Cabanas", new[] { "Nombre", "Desc", "HayJacuzzi", "HayReserva", "NumHabitacion", "CantPersMax", "Foto", "TipoCabId" }, new Object[] { "Cabaña de la Pradera", "Cabaña en medio de la pradera",1,1,15,3, "Cabaña de la Pradera_001.jpg", 3});
            migrationBuilder.InsertData("Cabanas", new[] { "Nombre", "Desc", "HayJacuzzi", "HayReserva", "NumHabitacion", "CantPersMax", "Foto", "TipoCabId" }, new Object[] { "Cabaña del Desierto", "Cabaña en el desierto",1,1,77,2, "Cabaña del Desierto_001.jpg", 1});

            //Mantenimientos
            migrationBuilder.InsertData("Mantenimientos", new[] { "Fecha", "Desc", "Costo", "Tecnico", "CabanaId" }, new object[] { DateTime.Now, "Cambio de bombillas", 150, "Juan rodriguez", 3 });
            migrationBuilder.InsertData("Mantenimientos", new[] { "Fecha", "Desc", "Costo", "Tecnico", "CabanaId" }, new object[] { DateTime.Now, "Reparación de aire acondicionado", 150, "Pedro Gomez", 2 });
            migrationBuilder.InsertData("Mantenimientos", new[] { "Fecha", "Desc", "Costo", "Tecnico", "CabanaId" }, new object[] { DateTime.Now, "Reparación de techo", 150, "Maria Ramirez", 1 });
            migrationBuilder.InsertData("Mantenimientos", new[] { "Fecha", "Desc", "Costo", "Tecnico", "CabanaId" }, new object[] { DateTime.Now, "Cambio de bombillas", 150, "Juan rodriguez", 4 });

        }
    }
}
