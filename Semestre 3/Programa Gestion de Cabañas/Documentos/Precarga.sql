USE BaseOBFRGomez
go
INSERT INTO Usuarios 
VALUES ('Juan Pérez', 'Contra$ena123', 'juanperez@gmail.com', 'juanperez'),
       ('Ana Gómez', 'Segura_123', 'anagomez@yahoo.com', 'anagomez'),
       ('Luisa Fernández', 'Contraseña1!', 'luisafernandez@hotmail.com', 'luisafernandez'),
       ('Miguel Torres', 'Torres#456', 'migueltorres@gmail.com', 'migueltorres'),
       ('María García', 'Garcia567!', 'mariagarcia@gmail.com', 'mariagarcia'),
       ('Pedro López', 'Lopez_89', 'pedrolopez@yahoo.com', 'pedrolopez'),
       ('Carla Pérez', 'Carla@123', 'carlaperez@gmail.com', 'carlaperez'),
       ('Gabriel González', 'Gonzalez1#', 'gabrielgonzalez@hotmail.com', 'gabrielgonzalez'),
       ('Jorge Rodríguez', 'Rodriguez_01', 'jorgerodriguez@yahoo.com', 'jorgerodriguez'),
       ('Carolina Martínez', 'Carolina2@', 'carolinamartinez@gmail.com', 'carolinamartinez');

INSERT INTO [dbo].[TipoCabs] ([Nombre], [Desc], [CostoP]) VALUES
('Cabaña Clásica', 'Cabaña sencilla pero acogedora con una habitación y baño privado.', 50000),
('Cabaña Premium', 'Cabaña amplia y lujosa con dos habitaciones, dos baños privados y jacuzzi.', 100000),
('Cabaña Familiar', 'Cabaña ideal para familias o grupos grandes con tres habitaciones y dos baños privados.', 150000),
('Cabaña de Montaña', 'Cabaña rústica ubicada en las montañas con dos habitaciones y baño compartido.', 70000),
('Cabaña Romántica', 'Cabaña perfecta para parejas con una habitación y baño privado con jacuzzi.', 80000),
('Cabaña con Vista al Mar', 'Cabaña con una espectacular vista al mar y una habitación con baño privado.', 90000),
('Cabaña con Piscina Privada', 'Cabaña con una piscina privada y dos habitaciones con baño privado.', 120000),
('Cabaña de Aventura', 'Cabaña para los más aventureros, ubicada en el bosque con una habitación y baño compartido.', 60000),
('Cabaña con Chimenea', 'Cabaña acogedora con chimenea y una habitación con baño privado.', 75000),
('Cabaña con Jardín Privado', 'Cabaña con un hermoso jardín privado y dos habitaciones con baño privado.', 110000);

INSERT INTO Cabanas 
VALUES 
('Cabaña del Bosque', 'Hermosa cabaña rodeada de naturaleza', 1, 0, 101, 4, 'Cabaña del Bosque_001.jpg', 1),
('Cabaña del Lago', 'Cabaña con vista al lago', 0, 1, 102, 6, 'Cabaña del Lago_001.jpg', 2),
('Cabaña del Río', 'Cabaña con acceso privado al río', 1, 0, 103, 5, 'Cabaña del Río_001.jpg', 3),
('Cabaña de la Montaña', 'Cabaña con vista panorámica de la montaña', 0, 1, 104, 8, 'Cabaña de la Montaña_001.jpg', 4),
('Cabaña del Valle', 'Cabaña en el corazón del valle', 1, 1, 105, 4, 'Cabaña del Valle_001.jpg', 5),
('Cabaña de la Playa', 'Cabaña con acceso directo a la playa', 1, 0, 106, 7, 'Cabaña de la Playa_001.jpg', 6),
('Cabaña del Glaciar', 'Cabaña con vista al glaciar', 0, 1, 107, 6, 'Cabaña del Glaciar_001.jpg', 7),
('Cabaña de la Pradera', 'Cabaña en medio de la pradera', 1, 1, 108, 5, 'Cabaña de la Pradera_001.jpg', 8),
('Cabaña de la Laguna', 'Cabaña con vista a la laguna', 0, 1, 109, 6, 'Cabaña de la Laguna_001.jpg', 9),
('Cabaña del Desierto', 'Cabaña en el desierto', 1, 0, 110, 4, 'Cabaña del Desierto_001.jpg', 10);

INSERT INTO Mantenimientos 
VALUES 
	('2020-01-12', 'Cambio de bombillas', 200, 'Juan Perez', 3),
	('2020-02-23', 'Reparación de techo', 500, 'Pedro Gomez', 7),
	('2020-03-04', 'Cambio de tuberías', 350, 'Luisa Torres', 2),
	('2020-04-16', 'Mantenimiento de jardín', 150, 'Ana Rodriguez', 4),
	('2020-05-22', 'Limpieza de piscina', 100, 'Maria Ramirez', 6),
	('2020-06-07', 'Reparación de puerta', 400, 'Juan Perez', 9),
	('2020-07-01', 'Cambio de cerradura', 250, 'Pedro Gomez', 1),
	('2020-08-14', 'Pintura exterior', 600, 'Luisa Torres', 10),
	('2020-09-27', 'Reparación de aire acondicionado', 450, 'Ana Rodriguez', 5),
	('2020-10-19', 'Cambio de sábanas y toallas', 80, 'Maria Ramirez', 8),
	('2021-01-02', 'Reparación de fuga de agua', 300, 'Juan Perez', 3),
	('2021-02-11', 'Limpieza general', 120, 'Pedro Gomez', 7),
	('2021-03-18', 'Cambio de bombillas', 200, 'Luisa Torres', 2),
	('2021-04-29', 'Mantenimiento de jardín', 150, 'Ana Rodriguez', 4),
	('2021-05-03', 'Reparación de techo', 500, 'Maria Ramirez', 6),
	('2021-06-16', 'Limpieza de piscina', 100, 'Juan Perez', 9),
	('2021-07-19', 'Cambio de cerradura', 250, 'Pedro Gomez', 1),
	('2022-01-01', 'Reparación de la tubería de la cocina', 150.00, 'Juan Pérez', 3),
	('2022-01-05', 'Cambio de cerradura en la puerta principal', 80.00, 'María Gómez', 7),
	('2022-01-10', 'Limpieza y mantenimiento general', 120.00, 'Pedro Rodríguez', 5),
	('2022-02-01', 'Reparación del techo de la habitación principal', 200.00, 'Laura Torres', 2),
	('2022-02-05', 'Revisión del sistema eléctrico', 90.00, 'José González', 10),
	('2022-02-10', 'Cambio de bombillas en todas las habitaciones', 60.00, 'Ana García', 1),
	('2022-03-01', 'Reparación de la puerta del baño', 100.00, 'Javier Fernández', 4),
	('2022-03-05', 'Limpieza de la piscina y mantenimiento del sistema de filtrado', 180.00, 'Marcela Ruiz', 8),
	('2022-03-10', 'Reparación del aire acondicionado en la habitación secundaria', 150.00, 'David Sánchez', 6),
	('2022-04-01', 'Revisión del sistema de calefacción', 120.00, 'Carla Martínez', 9);