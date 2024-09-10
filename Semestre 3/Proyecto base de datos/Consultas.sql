--Consultas

--A
	SELECT P.participanteID, P.nombre, T.tipo, SUM(PP.Puntos) AS PuntajePorTipo
	FROM Participante P
	JOIN Inscripcion I ON P.participanteID = I.participanteid
	JOIN Torneo T ON I.torneoid = T.torneoID
	JOIN Puesto_Puntos PP ON I.posicion = PP.puesto
	WHERE YEAR(T.fecha) = 2021
	GROUP BY P.participanteID, P.nombre,T.tipo
	

--B
	SELECT P.participanteID, p.nombre,T.tipo, MAX(PP.Puntos) AS MayorPuntaje, AVG(PP.Puntos) AS PromedioPuntaje, MIN(PP.Puntos) AS MenorPuntaje
	FROM Participante P
	JOIN Inscripcion I on I.participanteID = P.participanteID
	JOIN Torneo T on T.torneoID = I.torneoID
	JOIN puesto_puntos PP on PP.puesto = I.posicion
	group by P.participanteID, P.nombre,T.tipo
	order by P.participanteID

--C
	SELECT T.nombre, L.pais, L.ciudad, T.Nivel,T.tipo,T.estado, count(I.inscripcionID) as Inscripciones
	FROM Torneo T
	JOIN Inscripcion I on I.TorneoID = T.torneoID
	JOIN Participante P on P.participanteID = I.participanteID
	JOIN Locacion L on L.locID = T.locID
	WHERE T.tipo in (Select tipo 
					 From Torneo
					 Where Estado = 'Finalizado')
	GROUP BY  T.nombre, L.pais, L.ciudad, T.Nivel,T.tipo,T.estado
	ORDER BY T.tipo

--D
	SELECT P.participanteID,P.nombre,P.fecha_nacimiento,P.experiencia
    FROM Inscripcion I
    INNER JOIN Torneo T ON I.torneoid = T.torneoID
	INNER JOIN Participante P on I.participanteID = P.participanteID
    WHERE T.fecha >= DATEADD(YEAR, -2, GETDATE())  -- Torneos de los últimos 2 años
      AND T.torneoid not in (select T.torneoid
						 From Torneo T
						 where T.tipo = 'Racing')
    GROUP BY P.participanteID,P.nombre,P.fecha_nacimiento,P.experiencia
--E	
	SELECT P.*
	FROM Participante P
	WHERE P.participanteID in (SELECT P.participanteID
							   From Participante P	
							   JOIN Inscripcion I on I.participanteID = P.participanteID
							   JOIN Torneo T on T.torneoID = I.torneoID
							   WHERE T.tipo in( 'Freestyle','Kite-Surf')
							   GROUP BY P.participanteID
							   HAVING COUNT(I.inscripcionID)>=3
							   )
	AND P.ParticipanteID in (select p.participanteid
							 from participante p
							 JOIN Inscripcion I on I.participanteID = P.participanteID
							 JOIN Torneo T on T.torneoID = I.torneoID
							 JOIN puesto_puntos pp on pp.puesto = i.posicion
							 group by p.participanteid, year(t.fecha)
							 having sum(pp.puntos) > 1200
							)

--F
	SELECT TOP 1 T.nombre, COUNT(inscripcionID) as CantInscripciones
	FROM TORNEO T
	JOIN Inscripcion I on T.torneoID = I.torneoID
	
	WHERE T.torneoID not in (SELECT T.torneoID
						  FROM Torneo T
						  JOIN Locacion L on L.locID = T.locID
						  WHERE L.pais = 'Brasil')
	AND
		T.torneoID not in (SELECT T.torneoID
						   From Torneo T
						   JOIN Inscripcion I on I.torneoID = T.torneoID
						   JOIN Participante P on P.participanteID = I.participanteID
						   JOIN Locacion L on L.locID = P.locID
						   WHERE I.posicion = 1 AND L.pais = 'Brasil')
	AND
		T.fecha >= DATEADD(YEAR,-5,GETDATE())
	GROUP BY T.nombre
	ORDER BY CantInscripciones DESC