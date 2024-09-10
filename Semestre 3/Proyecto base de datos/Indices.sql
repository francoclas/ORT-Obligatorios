--Indices
create index IDXTorneoLocID ON Locacion(locID)
create index IDXParticipanteLocID on Locacion(locID)
create index IDXInscripcionTorneoID on Torneo(torneoId)
create index IDXInscripcionParticipanteID on Participante(participanteId)
