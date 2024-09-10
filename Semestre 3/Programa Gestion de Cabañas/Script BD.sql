create database BaseObligatorio
Use BaseObligatorio

Create table TipoCabaña(
	ID int,
	Nombre Char,
	Descrip Char,
	CostoP int,
	Primary key (ID)
)

Create table Cabaña(
	ID int,
	Nombre Char,
	Jacuzzi bit,
	Reserva bit,
	NumHabitacion int,
	CantPersMax int,
	Foto image,
	IDTipo int,
	Primary key (ID),
	Foreign Key (IDTipo) References TipoCabaña(ID)
)

Create table Mantenimiento(
	ID int,
	Fecha DateTime,
	Descripcion Char,
	Costo int,
	Tecnico Char,
	IDCabaña int,
	Primary Key (ID),
	Foreign Key (IDCabaña) References Cabaña(ID)
)