
--Restricciones
--Torneo 
Alter table Torneo
	Add constraint NombreLetras check (nombre NOT LIKE '%[^a-zA-Z ]%');
Alter table Torneo
	Add constraint FechaMay check (fecha>'2016-12-31');
Alter table Torneo
	Add constraint Tipo check (Tipo in ('Freestyle','Kite-Surf','Parkstyle','Racing'));
Alter table Torneo
	Add constraint Estado check (Estado in ('Planificado','En competencia','Finalizado','Cancelado'));
Alter table Torneo 
	Add constraint Nivel110  check ( Nivel >0 AND Nivel<11); 
ALTER TABLE torneo
	Add constraint FechaNombre UNIQUE (fecha, nombre);
ALTER TABLE Torneo
	Add Foreign Key (LocId) references Locacion(locid) 

-- Participante
Alter table Participante
	add constraint NombreUnico Unique (nombre);
Alter table PArticipante
	add constraint FechaMayor check (DateDiff(year,fecha_nacimiento,getdate()) >=18);
alter table Participante
	add constraint Experiencia110 check (experiencia>0 AND experiencia <11);
ALTER TABLE Participante
	Add Foreign Key (LocId) references Locacion(locid)

-- Inscripcion 
alter table inscripcion
	add constraint posicion114 check (posicion>0 and posicion <14);
ALTER TABLE inscripcion
	Add Foreign Key (torneoID) references Torneo(TorneoID)
ALTER TABLE inscripcion
	Add Foreign Key (ParticipanteID) references Participante(ParticipanteID)


-- Locacion
alter table Locacion
	add constraint PaisUnico Unique(Pais);
alter table Locacion
	add constraint CiudadUnico Unique(Ciudad);
	