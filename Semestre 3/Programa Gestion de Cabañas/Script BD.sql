create database BaseObligatorio
Use BaseObligatorio

Create table TipoCaba�a(
	ID int,
	Nombre Char,
	Descrip Char,
	CostoP int,
	Primary key (ID)
)

Create table Caba�a(
	ID int,
	Nombre Char,
	Jacuzzi bit,
	Reserva bit,
	NumHabitacion int,
	CantPersMax int,
	Foto image,
	IDTipo int,
	Primary key (ID),
	Foreign Key (IDTipo) References TipoCaba�a(ID)
)

Create table Mantenimiento(
	ID int,
	Fecha DateTime,
	Descripcion Char,
	Costo int,
	Tecnico Char,
	IDCaba�a int,
	Primary Key (ID),
	Foreign Key (IDCaba�a) References Caba�a(ID)
)