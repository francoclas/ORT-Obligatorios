create database BaseObligatorio;
use BaseObligatorio;

create table Cabana(
	id int not null,
	nombre varchar(30),
	jacuzzi bit not null,
	reserva bit not null,
	numH int,
	capacidad int,
	foto varbinary not null
	)
	alter table Cabana
		add primary key (id)

	create table TipoCab(
	id int not null,
	descripcion varchar,
	costoP int   
	)
	alter table TipoCab
		add primary key (id)

	create table Mantenimiento(
		id int not null,
		descripcion varchar(10),
		tecnico varchar,
		fecha date,
		costo int
	)
	alter table Mantenimiento
		add primary key (id)
	
	create table Recibe(
		IDCabana int,
		IDMant int,
		Primary Key (IDCabana,IDMant),
		Foreign Key (IDCabana) References Cabana(id),
		Foreign Key (IDMant) References Mantenimiento(id)
		)
	create table Es_Tipo(
	IdTipo int,
	IDCab int
	Primary key (IdTipo,IDCab),
	Foreign Key (IdTipo) References TipoCab(id),
	Foreign key (IDCab) References Cabana(id)
	)