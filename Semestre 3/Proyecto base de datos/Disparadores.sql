--Triggers

--A
	--se agrega el campo TotalAcumulado en el archivo "Script Creador" en la tabla de Participante
	Create Trigger MantenimientoPuntos on Participante After Update
	AS
	BEGIN
		declare @AnioActual int = YEAR(GETDATE()), @AnioUlt int, @TorneoId int,@IdParticipante int;

		Select @IdParticipante = participanteID
		from inserted
		--Obtengo la inscripccion del participante de su ultimo torneo para verificar si ese torneo fue en este año o no
		SELECT TOP 1  @TorneoId = t.torneoID, @AnioUlt = YEAR(t.fecha)
		FROM inscripcion ins, torneo t, inserted i
		where ins.torneoID = t.torneoID AND ins.participanteID = i.participanteID
		ORDER BY ins.participanteid, t.fecha DESC;

		if(@AnioActual <> @AnioUlt)
			begin
				UPDATE Participante
				SET TotalAcumulado = 0
				WHERE participanteID = @IdParticipante
			end
	end;
--B
	Create Trigger ModificacionTorneo on Inscripcion After Update
	AS
	Begin
		declare @ResultadoAnt decimal, @ResultadoPos decimal

		select @ResultadoAnt = i.posicion, @ResultadoPos = d.posicion
		from inserted i, deleted d
		if(@ResultadoAnt <> @ResultadoPos)
		begin
			insert into AuditInscripcion(AuditFecha,AuditHost,posAnterior,posActual)
			select GETDATE(),SUSER_NAME(),d.posicion,i.posicion
			from inserted i, deleted d
		end;
	End;
--C
	Create Trigger RegistroKiterTorneo on Inscripcion After Insert
	AS
	Begin
		declare @NivelParticipante int, @NivelTorneo int,@IDParticipante int, @IDTorneo int;

		select @NivelParticipante = p.experiencia, @NivelTorneo = t.Nivel, @IDParticipante = p.participanteID, @IDTorneo = t.torneoID
		from inserted i,Participante p, Torneo t
		where p.participanteID = i.participanteID AND i.torneoID = t.torneoID

		if (@NivelParticipante <= @NivelTorneo)
			begin
				INSERT INTO Inscripcion (torneoid, participanteid, inscripcionid, posicion)
				SELECT torneoid, participanteid, inscripcionid, posicion
				FROM INSERTED;
			end
		else
			begin
				insert into KitersNohabiles (torneoid,participanteid)
				values (@IDTorneo,@IDParticipante);
				select * from KitersNoHabiles where torneoid = @IDTorneo;
			end;	
	End;
