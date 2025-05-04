package it.epicode.u17_d5_eventi_epici.eventi;

import it.epicode.u17_d5_eventi_epici.auth.AppUser;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class EventoRequest {
    @NotBlank
    private String titolo;
    @NotBlank
    private String descrizione;
    @FutureOrPresent
    private LocalDate data;
    @NotBlank
    private String luogo;
    @NotNull
    private int postiDisponibili;

    @ManyToOne
    private AppUser organizzatore;
}
