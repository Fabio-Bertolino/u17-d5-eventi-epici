package it.epicode.u17_d5_eventi_epici.Prenotazioni;

import it.epicode.u17_d5_eventi_epici.auth.AppUser;
import it.epicode.u17_d5_eventi_epici.auth.AuthController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/prenotazioni")
public class PrenotazioneController {
    @Autowired
    private PrenotazioneService prenotazioneService;
    @Autowired
    private AuthController authController;

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/{utente}")
    public List<Prenotazione> getPrenotazioni(@PathVariable @AuthenticationPrincipal AppUser utente) {
        return prenotazioneService.findAllByUtente(utente);
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/{prenotazioneId}")
    public Prenotazione findById(@PathVariable Long id) {
        return prenotazioneService.findById(id);
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public Prenotazione savePrenotazione(@RequestBody Prenotazione prenotazione) throws Exception {
        return prenotazioneService.savePrenotazione(prenotazione);
    }

    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePrenotazione(@PathVariable Long id, @AuthenticationPrincipal AppUser appUser) throws Exception {
        if (authController.getCurrentUser(appUser).getId() != prenotazioneService.findById(id).getUtente().getId()) {
            throw new Exception("Non puoi eliminare una prenotazione che non hai effettuato");
        } else {
            prenotazioneService.deletePrenotazione(id);
        }
    }
}
