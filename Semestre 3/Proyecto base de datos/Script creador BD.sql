
Create DataBase TorneosKite
go
use TorneosKite
-- Creación de la tabla Locacion
CREATE TABLE Locacion (
locID INT IDENTITY (1,1) NOT NULL,
pais VARCHAR(100) NOT NULL UNIQUE,
ciudad VARCHAR(100) UNIQUE,

constraint PK_Loc Primary key (locID)
);
-- Creación de la tabla Torneo
CREATE TABLE Torneo (
    torneoID INT IDENTITY(1,1) NOT NULL,
    nombre VARCHAR(100) NOT NULL check (nombre NOT LIKE '%[^a-zA-Z ]%'), 
    fecha DATE check (fecha>'2016-12-31'),
	locID INT,
    tipo VARCHAR(50) check (Tipo in ('Freestyle','Kite-Surf','Parkstyle','Racing')),
    estado VARCHAR(50) check (Estado in ('Planificado','En competencia','Finalizado','Cancelado')),
	Nivel INT check ( Nivel >0 AND Nivel<11)

	constraint PK_torneoID Primary key(torneoID),
	constraint FK_LocacionID Foreign key (locID) references Locacion(locID)
);
-- Creación de la tabla Participante
CREATE TABLE Participante (
    participanteID INT IDENTITY(1,1) NOT NULL,
    nombre VARCHAR(100) Unique,
    locID INT,
    fecha_nacimiento DATE check (DateDiff(year,fecha_nacimiento,getdate()) >=18),
    experiencia INT  check (experiencia>0 AND experiencia <11),
	
	constraint PK_Participante Primary key(participanteID),
	constraint FK_LocID Foreign key (locID) references Locacion(locID)

);
-- Creación de la tabla Inscripcion
CREATE TABLE Inscripcion (
    inscripcionID INT IDENTITY,
    torneoID INT,
    participanteID INT,
	posicion INT check (posicion>0 and posicion <14),

	constraint FK_IDParticipante Foreign key(participanteID) references Participante(ParticipanteID),
	constraint FK_IDTorneo Foreign Key(torneoID) references Torneo(TorneoID),
	constraint PkInscripcion Primary key(inscripcionid,torneoid,participanteid)

);
--Creación de la tabla auxiliar puesto_puntos (asumiendo dos columnas, puesto y puntos)
CREATE TABLE puesto_puntos (
    puesto INT,
    puntos INT
);
--Creación de la tabla auditoria
CREATE TABLE AuditInscripcion(AuditID int identity not null,
        AuditFecha datetime,
		AuditHost varchar(30),
        posAnterior decimal,
		posActual decimal
	 )

--Tabla para registrar Kiters que quisieron inscribirse en un torneo pero no llegaban al nivel.
CREATE TABLE KitersNohabiles(
	 torneoid int,
	 participanteid int,

	 constraint FK_IDTorneoRegistrado Foreign Key(torneoid) references Torneo(TorneoID),
	 constraint FK_IDParticipanteNoHabil Foreign Key(participanteid) references Participante(ParticipanteID)
	 )

--Para los Disparadores A se genera el campo TotalACumulado en Participante

Alter table Participante
Add TotalAcumulado int null