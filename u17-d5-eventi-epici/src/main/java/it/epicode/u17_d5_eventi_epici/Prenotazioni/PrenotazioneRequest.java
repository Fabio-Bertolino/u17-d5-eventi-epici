package it.epicode.u17_d5_eventi_epici.Prenotazioni;

import it.epicode.u17_d5_eventi_epici.auth.AppUser;
import it.epicode.u17_d5_eventi_epici.eventi.Evento;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class PrenotazioneRequest {
    @NotNull
    private AppUser utente;

    @NotNull
    private Evento evento;

    @FutureOrPresent
    private LocalDate dataPrenotazione;
}
