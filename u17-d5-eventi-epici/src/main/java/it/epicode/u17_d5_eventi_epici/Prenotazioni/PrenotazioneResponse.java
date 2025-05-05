package it.epicode.u17_d5_eventi_epici.Prenotazioni;

import it.epicode.u17_d5_eventi_epici.auth.AppUser;
import it.epicode.u17_d5_eventi_epici.eventi.Evento;
import lombok.Data;

import java.time.LocalDate;

@Data
public class PrenotazioneResponse {
    private Long id;
    private AppUser utente;
    private Evento evento;
    private LocalDate dataPrenotazione;
}
