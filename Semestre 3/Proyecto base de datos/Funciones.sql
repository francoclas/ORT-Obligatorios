--Funciones

--A
CREATE OR ALTER PROCEDURE MayorGanador @FechaUno date, @FechaDos date, @TipoTorneo varchar(50)
AS
begin
    SELECT TOP 1 I.participanteid,COUNT(I.participanteid) as Cant
    FROM Inscripcion I
    INNER JOIN Torneo T ON I.torneoid = T.torneoID
    WHERE T.fecha BETWEEN @FechaUno AND @FechaDos
        AND T.tipo = @TipoTorneo
        AND I.posicion = 1 
    GROUP BY I.participanteid
    ORDER BY Cant DESC;
END
EXEC MayorGanador '2020-01-01', '2029-01-01', 'Freestyle';
--B
	CREATE OR ALTER PROCEDURE ObtenerParticipantesDeOtrasCiudades @TorneoID INT
	AS
	BEGIN
		DECLARE @EstadoTorneo Varchar(30)
		SELECT @EstadoTorneo = Estado
		From Torneo
		WHERE torneoID = @TorneoID

		IF(@EstadoTorneo = 'Finalizado')
		BEGIN
			SELECT DISTINCT P.*
			FROM Participante P
			JOIN  Inscripcion I On I.participanteID = P.participanteID
			JOin Locacion L on L.locID = P.locID
			WHERE I.torneoID = @TorneoID AND  L.ciudad <> (SELECT L.ciudad 
															   FROM Torneo T 
															   Join Locacion L on T.locID = L.locID
															   WHERE T.torneoID = @TorneoID)
		END
		ELSE
		Print 'El torneo no esta finalizado';


	END

	Exec ObtenerParticipantesDeOtrasCiudades 3;

--C
	CREATE Function CantidadParticipantesPaises (@IDTorneo int) returns int
	AS
	BEGIN
		DECLARE @Cantidad int;

		SELECT @Cantidad = COUNT(Distinct I.participanteID)
		FROM Inscripcion I
		Join Torneo T on I.torneoID = T.torneoID
		JOIN Participante P on P.participanteID = I.participanteID
		WHERE P.locID = T.locID

		return @Cantidad
	END
	select dbo.CantidadParticipantesPaises(1) as ParticipantesLocales
--D
	CREATE Function ParticipanteGanadors (@IDParticipante int) returns int
	AS
	BEGIN
		DECLARE @CantidadVictorias INT;

		SELECT @CantidadVictorias =  COUNT(I.torneoID)
		FROM Inscripcion I
		WHERE I.participanteid = @IDParticipante AND posicion = 1;

		Return @CantidadVictorias;

	END;
	select dbo.ParticipanteGanadors(1) as Cantidadvictorias