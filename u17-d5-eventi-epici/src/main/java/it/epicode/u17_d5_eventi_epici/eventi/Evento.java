package it.epicode.u17_d5_eventi_epici.eventi;

import it.epicode.u17_d5_eventi_epici.auth.AppUser;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "eventi")
public class Evento {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false, length = 50)
    private String titolo;
    @Column(nullable = false, length = 500)
    private String descrizione;
    @Column(nullable = false)
    private LocalDate data;
    @Column(nullable = false, length = 100)
    private String luogo;
    @Column(nullable = false)
    private int postiDisponibili;

    @ManyToOne
    private AppUser organizzatore;
}
