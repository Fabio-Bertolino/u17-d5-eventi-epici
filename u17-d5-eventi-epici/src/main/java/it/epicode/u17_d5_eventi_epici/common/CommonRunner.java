package it.epicode.u17_d5_eventi_epici.common;

import com.github.javafaker.Faker;
import it.epicode.u17_d5_eventi_epici.auth.AppUser;
import it.epicode.u17_d5_eventi_epici.auth.AppUserRepository;
import it.epicode.u17_d5_eventi_epici.auth.AppUserService;
import it.epicode.u17_d5_eventi_epici.auth.Role;
import it.epicode.u17_d5_eventi_epici.eventi.Evento;
import it.epicode.u17_d5_eventi_epici.eventi.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class CommonRunner implements CommandLineRunner {
    @Autowired
    private Faker faker;
    @Autowired
    private EventoRepository eventoRepository;
    @Autowired
    private AppUserRepository appUserRepository;
    @Autowired
    private AppUserService appUserService;

    @Override
    public void run(String... args) throws Exception {

        AppUser organizer1 = appUserService.registerUser("organizer1", "organizer1", Set.of(Role.ROLE_EVENT_MANAGER));
        System.out.println("----------------------------------------");
        System.out.println("Organizzatore creato con id: " + organizer1.getId());

        AppUser organizer2 = appUserService.registerUser("organizer2", "organizer2", Set.of(Role.ROLE_EVENT_MANAGER));
        System.out.println("----------------------------------------");
        System.out.println("Organizzatore creato con id: " + organizer2.getId());

        AppUser utente1 = appUserService.registerUser("utente1", "utente1", Set.of(Role.ROLE_USER));
        System.out.println("----------------------------------------");
        System.out.println("Utente creato con id: " + utente1.getId());

        Evento evento1 = new Evento();
        evento1.setTitolo(faker.book().title());
        evento1.setDescrizione(faker.book().title());
        evento1.setData(faker.date().birthday().toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate());
        evento1.setLuogo(faker.address().cityName());
        evento1.setPostiDisponibili(faker.number().numberBetween(1, 100));
        evento1.setOrganizzatore(organizer1);
        eventoRepository.save(evento1);
        System.out.println("----------------------------------------");
        System.out.println("Evento creato con id: " + evento1.getId());
    }
}
