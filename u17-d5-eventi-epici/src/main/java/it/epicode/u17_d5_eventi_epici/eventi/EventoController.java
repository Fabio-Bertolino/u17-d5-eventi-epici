package it.epicode.u17_d5_eventi_epici.eventi;

import it.epicode.u17_d5_eventi_epici.auth.AppUser;
import it.epicode.u17_d5_eventi_epici.auth.AuthController;
import it.epicode.u17_d5_eventi_epici.auth.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/eventi")
public class EventoController {
    @Autowired
    private EventoService eventoService;
    @Autowired
    private AuthController authController;

    @PreAuthorize("isAuthenticated()")
    @GetMapping("")
    public List<Evento> getEventi() {
        return eventoService.getEventi();
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{eventoId}")
    public Evento findById(@PathVariable Long eventoId) {
        return eventoService.findById(eventoId);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'EVENT_MANAGER')")
    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public Evento createEvento(@RequestBody Evento evento) throws Exception {
        return eventoService.save(evento);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'EVENT_MANAGER')")
    @PutMapping("/{eventoId}")
    public Evento updateEventoById(@PathVariable Long eventoId, @RequestBody Evento evento, @AuthenticationPrincipal AppUser appUser) throws Exception {
        if (authController.getCurrentUser(appUser).getId() != evento.getOrganizzatore().getId()) {
            throw new Exception("Non puoi modificare un evento che non hai creato");
        } else {
            return eventoService.update(eventoId, evento);
        }
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'EVENT_MANAGER')")
    @DeleteMapping("/{eventoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEventoById(@PathVariable Long eventoId, @AuthenticationPrincipal AppUser appUser) throws Exception {
        if (authController.getCurrentUser(appUser).getId() != eventoService.findById(eventoId).getOrganizzatore().getId() && !appUser.getRoles().contains(Role.ROLE_ADMIN)) {
            throw new Exception("Non puoi eliminare un evento che non hai creato");
        } else {
            eventoService.delete(eventoId);
        }
    }
}
