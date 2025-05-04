package it.epicode.u17_d5_eventi_epici.eventi;

import it.epicode.u17_d5_eventi_epici.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@Validated
@RequiredArgsConstructor
public class EventoService {
    private final EventoRepository eventoRepository;

    public List<Evento> getEventi() {
        return eventoRepository.findAll();
    }

    public Evento findById(Long id) {
        return eventoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.valueOf(id)));
    }

    public Evento save(Evento evento) {
        return eventoRepository.save(evento);
    }

    public void delete(Long id) {
        Evento found = this.findById(id);
        eventoRepository.delete(found);
    }

    public Evento update(Long id, Evento evento) {
        Evento found = this.findById(id);
        found.setTitolo(evento.getTitolo());
        found.setDescrizione(evento.getDescrizione());
        found.setData(evento.getData());
        found.setLuogo(evento.getLuogo());
        found.setPostiDisponibili(evento.getPostiDisponibili());
        return eventoRepository.save(found);
    }

    
}
