--Precarga
--Locacion
select * from Torneo
INSERT INTO Locacion (pais, ciudad)
VALUES
	('Estados Unidos', 'Nueva York'),
    ('Francia', 'París'),
    ('Reino Unido', 'Londres'),
    ('Alemania', 'Berlín'),
    ('España', 'Madrid'),
	('Brasil','Rio');
GO
--Torneos
INSERT INTO Torneo (nombre, fecha, locid, tipo, estado, nivel)
VALUES
    ('Torneo las torres', '2022-07-15', 1, 'Freestyle', 'Planificado', 5),
    ('Billabong Pro Teahupoo', '2023-05-20', 2, 'Kite-Surf', 'En competencia', 7),
    ('Vans Triple Crown of Surf', '2027-09-10', 3, 'Parkstyle', 'Finalizado', 3),
    ('Cesurfing', '2024-03-08', 4, 'Racing', 'Cancelado', 9),
	('Caralho league','2021-11-05',6,'Racing','Finalizado',2),
	('TorneoAGUAVERDE', '2022-07-15', 1, 'Freestyle', 'Planificado', 5),
    ('VILLAbalneario', '2023-05-20', 2, 'Kite-Surf', 'En competencia', 7),
    ('Vans KiteSurf Amateur', '2025-10-02', 3, 'Kite-Surf', 'Finalizado',6),
    ('KiteSurf PRO', '2024-09-19', 4, 'Kite-Surf', 'Cancelado', 9),
	('KiteSurf Juniors','2023-01-25',6,'Kite-Surf','Finalizado',2);
GO
--Participante
INSERT INTO Participante (nombre, locid, fecha_nacimiento, experiencia)
VALUES
    ('Juan Perez', 1, '1990-05-15', 8),
    ('Maria Garcia', 2, '1995-09-20', 6),
    ('Luis Rodríguez', 3, '1998-04-10', 4),
    ('Ana Martinez', 4, '1985-03-25', 10),
	('Monkey D Luffy',6,'2001-02-21',9),
	('Elena Petrova', 1, '1992-07-20', 8),
    ('Alejandro Mendoza', 2, '1995-06-13', 6),
    ('Luca Moretti', 3, '1997-01-29', 3),
    ('Sofia Oliveira', 4, '1995-03-18', 10),
	('Thiago Silva',6,'2000-11-11',9),
	('Marcela Rossi', 1, '1980-04-15', 4),
    ('Fatima Ali', 2, '1994-01-04', 2),
    ('Walter White', 3, '1999-10-10', 7),
    ('Isabella Costa', 4, '1995-05-22', 5),
	('Hiroshi Takahashi',6,'2002-02-10',10);
GO

--Inscripcion
select * from Inscripcion
INSERT INTO Inscripcion(torneoID,participanteID,posicion)
VALUES
--Torneo 1
	(1,1,1),
	(1,2,2),
	(1,3,3),
	(1,4,4),
	(1,5,5),
	(1,6,6),
	(1,7,7),
	(1,8,8),
--Torneo 2
	(2,15,1),
	(2,11,2),
	(2,3,3),
	(2,5,4),
	(2,9,5),
	(2,7,6),
	(2,4,7),
	(2,10,8),
	(2,14,9),
--Torneo 3
	(3,6,1),
	(3,2,2),
	(3,8,3),
	(3,13,4),
	(3,9,5),
	(3,11,6),
	(3,4,7),
	(3,15,8),
	(3,5,9),
	(3,3,10),
	(3,7,11),
	(3,10,12),
	(3,1,13),
--Torneo 4
	(4,9,1),
	(4,15,2),
	(4,8,3),
	(4,5,4),
	(4,1,5),
	(4,2,6),
	(4,7,7),
	(4,13,8),
--Torneo 5
	(5,5,1),
	(5,6,2),
	(5,1,3),
	(5,5,4),
	(5,9,5),
	(5,11,6),
	(5,2,7),
	--Torneo 6
	(6,2,1),
	(6,10,2),
	(6,4,3),
	(6,7,4),
	(6,8,5),
	(6,1,6),
	(6,11,7),
	(6,9,8),
	--Torneo 7
	(7,2,1),
	(7,5,2),
	(7,10,3),
	(7,4,4),
	(7,13,6),
	--Torneo 8
	(8,9,6),
	(8,3,6),
	(8,7,6),
	(8,1,6),
	(8,2,6),
	(8,10,6),
	--Torneo 9
	(9,10,1),
	(9,12,2),
	(9,1,3),
	(9,5,4),
	(9,6,5),
	(9,8,6),
	(9,3,7),
	--Torneo 10

	(10,6,1),
	(10,1,2),
	(10,9,3),
	(10,4,4),
	(10,2,5),
	(10,5,6)
GO
select * from Inscripcion
--Casos que no entran en por restricciones
--Torneos
INSERT INTO Torneo (nombre, fecha, locid, tipo, estado, nivel)
VALUES
    ('Torneo 5', '2015-12-05', 5, 'Freestyle', 'Planificado', 2),  -- Fecha anterior a 2016 y numero en nombre
    ('Torneo ejemplo', '2020-08-30', 6, 'Kite-Surf', 'En proceso', 12);  -- Estado no válido
GO
--Participantes
INSERT INTO Participante (nombre, locid, fecha_nacimiento, experiencia)
VALUES
    ('Pedro Gomez', 5, '2005-12-10', 2),  -- Menor de 18 años
    ('Laura Fernández', 6, '2000-02-28', 12); -- Experiencia fuera del rango
GO

--Locaciones
INSERT INTO Locacion (pais, ciudad)
VALUES
    ('Estados Unidos', 'Nueva York'); --Repetido

	select * from Locacion
	select * from Participante
	select * from Torneo
	select * from Inscripcion

	GO

--Tabla puntos
Insert INTO puesto_puntos(puesto,puntos)
VALUES 
	(1,100),
	(2,870),
	(3,770),
	(4,700),
	(5,580),
	(6,510),
	(7,470),
	(8,450),
	(9,420),
	(10,385),
	(11,340),
	(12,315),
	(13,280),
	(14,245);
	Go
	