package it.epicode.u17_d5_eventi_epici.Prenotazioni;

import it.epicode.u17_d5_eventi_epici.auth.AppUser;
import it.epicode.u17_d5_eventi_epici.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@Validated
@RequiredArgsConstructor
public class PrenotazioneService {

    private final PrenotazioneRepository prenotazioneRepository;

    public Prenotazione findById(Long id) {
        return prenotazioneRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.valueOf(id)));
    }

    public List<Prenotazione> findAllByUtente(AppUser utente) {
        return prenotazioneRepository.findAllByUtente(utente);
    }

    public Prenotazione savePrenotazione(Prenotazione prenotazione) throws Exception {
        if ((long) prenotazioneRepository.findAllByEvento(prenotazione.getEvento()).size() >= prenotazione.getEvento().getPostiDisponibili()) {
            throw new Exception("Posti esauriti");
        } else {
            return prenotazioneRepository.save(prenotazione);
        }
    }

    public void deletePrenotazione(Long id) {
        Prenotazione found = this.findById(id);
        prenotazioneRepository.delete(found);
    }
}
