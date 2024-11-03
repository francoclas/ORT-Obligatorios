USE BaseOBFRGomez
go
INSERT INTO Usuarios 
VALUES ('Juan P�rez', 'Contra$ena123', 'juanperez@gmail.com', 'juanperez'),
       ('Ana G�mez', 'Segura_123', 'anagomez@yahoo.com', 'anagomez'),
       ('Luisa Fern�ndez', 'Contrase�a1!', 'luisafernandez@hotmail.com', 'luisafernandez'),
       ('Miguel Torres', 'Torres#456', 'migueltorres@gmail.com', 'migueltorres'),
       ('Mar�a Garc�a', 'Garcia567!', 'mariagarcia@gmail.com', 'mariagarcia'),
       ('Pedro L�pez', 'Lopez_89', 'pedrolopez@yahoo.com', 'pedrolopez'),
       ('Carla P�rez', 'Carla@123', 'carlaperez@gmail.com', 'carlaperez'),
       ('Gabriel Gonz�lez', 'Gonzalez1#', 'gabrielgonzalez@hotmail.com', 'gabrielgonzalez'),
       ('Jorge Rodr�guez', 'Rodriguez_01', 'jorgerodriguez@yahoo.com', 'jorgerodriguez'),
       ('Carolina Mart�nez', 'Carolina2@', 'carolinamartinez@gmail.com', 'carolinamartinez');

INSERT INTO [dbo].[TipoCabs] ([Nombre], [Desc], [CostoP]) VALUES
('Caba�a Cl�sica', 'Caba�a sencilla pero acogedora con una habitaci�n y ba�o privado.', 50000),
('Caba�a Premium', 'Caba�a amplia y lujosa con dos habitaciones, dos ba�os privados y jacuzzi.', 100000),
('Caba�a Familiar', 'Caba�a ideal para familias o grupos grandes con tres habitaciones y dos ba�os privados.', 150000),
('Caba�a de Monta�a', 'Caba�a r�stica ubicada en las monta�as con dos habitaciones y ba�o compartido.', 70000),
('Caba�a Rom�ntica', 'Caba�a perfecta para parejas con una habitaci�n y ba�o privado con jacuzzi.', 80000),
('Caba�a con Vista al Mar', 'Caba�a con una espectacular vista al mar y una habitaci�n con ba�o privado.', 90000),
('Caba�a con Piscina Privada', 'Caba�a con una piscina privada y dos habitaciones con ba�o privado.', 120000),
('Caba�a de Aventura', 'Caba�a para los m�s aventureros, ubicada en el bosque con una habitaci�n y ba�o compartido.', 60000),
('Caba�a con Chimenea', 'Caba�a acogedora con chimenea y una habitaci�n con ba�o privado.', 75000),
('Caba�a con Jard�n Privado', 'Caba�a con un hermoso jard�n privado y dos habitaciones con ba�o privado.', 110000);

INSERT INTO Cabanas 
VALUES 
('Caba�a del Bosque', 'Hermosa caba�a rodeada de naturaleza', 1, 0, 101, 4, 'Caba�a del Bosque_001.jpg', 1),
('Caba�a del Lago', 'Caba�a con vista al lago', 0, 1, 102, 6, 'Caba�a del Lago_001.jpg', 2),
('Caba�a del R�o', 'Caba�a con acceso privado al r�o', 1, 0, 103, 5, 'Caba�a del R�o_001.jpg', 3),
('Caba�a de la Monta�a', 'Caba�a con vista panor�mica de la monta�a', 0, 1, 104, 8, 'Caba�a de la Monta�a_001.jpg', 4),
('Caba�a del Valle', 'Caba�a en el coraz�n del valle', 1, 1, 105, 4, 'Caba�a del Valle_001.jpg', 5),
('Caba�a de la Playa', 'Caba�a con acceso directo a la playa', 1, 0, 106, 7, 'Caba�a de la Playa_001.jpg', 6),
('Caba�a del Glaciar', 'Caba�a con vista al glaciar', 0, 1, 107, 6, 'Caba�a del Glaciar_001.jpg', 7),
('Caba�a de la Pradera', 'Caba�a en medio de la pradera', 1, 1, 108, 5, 'Caba�a de la Pradera_001.jpg', 8),
('Caba�a de la Laguna', 'Caba�a con vista a la laguna', 0, 1, 109, 6, 'Caba�a de la Laguna_001.jpg', 9),
('Caba�a del Desierto', 'Caba�a en el desierto', 1, 0, 110, 4, 'Caba�a del Desierto_001.jpg', 10);

INSERT INTO Mantenimientos 
VALUES 
	('2020-01-12', 'Cambio de bombillas', 200, 'Juan Perez', 3),
	('2020-02-23', 'Reparaci�n de techo', 500, 'Pedro Gomez', 7),
	('2020-03-04', 'Cambio de tuber�as', 350, 'Luisa Torres', 2),
	('2020-04-16', 'Mantenimiento de jard�n', 150, 'Ana Rodriguez', 4),
	('2020-05-22', 'Limpieza de piscina', 100, 'Maria Ramirez', 6),
	('2020-06-07', 'Reparaci�n de puerta', 400, 'Juan Perez', 9),
	('2020-07-01', 'Cambio de cerradura', 250, 'Pedro Gomez', 1),
	('2020-08-14', 'Pintura exterior', 600, 'Luisa Torres', 10),
	('2020-09-27', 'Reparaci�n de aire acondicionado', 450, 'Ana Rodriguez', 5),
	('2020-10-19', 'Cambio de s�banas y toallas', 80, 'Maria Ramirez', 8),
	('2021-01-02', 'Reparaci�n de fuga de agua', 300, 'Juan Perez', 3),
	('2021-02-11', 'Limpieza general', 120, 'Pedro Gomez', 7),
	('2021-03-18', 'Cambio de bombillas', 200, 'Luisa Torres', 2),
	('2021-04-29', 'Mantenimiento de jard�n', 150, 'Ana Rodriguez', 4),
	('2021-05-03', 'Reparaci�n de techo', 500, 'Maria Ramirez', 6),
	('2021-06-16', 'Limpieza de piscina', 100, 'Juan Perez', 9),
	('2021-07-19', 'Cambio de cerradura', 250, 'Pedro Gomez', 1),
	('2022-01-01', 'Reparaci�n de la tuber�a de la cocina', 150.00, 'Juan P�rez', 3),
	('2022-01-05', 'Cambio de cerradura en la puerta principal', 80.00, 'Mar�a G�mez', 7),
	('2022-01-10', 'Limpieza y mantenimiento general', 120.00, 'Pedro Rodr�guez', 5),
	('2022-02-01', 'Reparaci�n del techo de la habitaci�n principal', 200.00, 'Laura Torres', 2),
	('2022-02-05', 'Revisi�n del sistema el�ctrico', 90.00, 'Jos� Gonz�lez', 10),
	('2022-02-10', 'Cambio de bombillas en todas las habitaciones', 60.00, 'Ana Garc�a', 1),
	('2022-03-01', 'Reparaci�n de la puerta del ba�o', 100.00, 'Javier Fern�ndez', 4),
	('2022-03-05', 'Limpieza de la piscina y mantenimiento del sistema de filtrado', 180.00, 'Marcela Ruiz', 8),
	('2022-03-10', 'Reparaci�n del aire acondicionado en la habitaci�n secundaria', 150.00, 'David S�nchez', 6),
	('2022-04-01', 'Revisi�n del sistema de calefacci�n', 120.00, 'Carla Mart�nez', 9);