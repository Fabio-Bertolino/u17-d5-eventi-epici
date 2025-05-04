package it.epicode.u17_d5_eventi_epici.eventi;

import it.epicode.u17_d5_eventi_epici.auth.AppUser;

import java.time.LocalDate;

public class EventoResponse {
    private Long id;
    private String titolo;
    private String descrizione;
    private LocalDate data;
    private String luogo;
    private int postiDisponibili;
    private AppUser organizzatore;
}
