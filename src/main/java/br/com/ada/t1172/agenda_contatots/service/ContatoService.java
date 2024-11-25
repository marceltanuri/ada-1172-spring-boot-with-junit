package br.com.ada.t1172.agenda_contatots.service;

import br.com.ada.t1172.agenda_contatots.model.Contato;
import br.com.ada.t1172.agenda_contatots.repository.ContatosRepository;
import br.com.ada.t1172.agenda_contatots.service.validations.ValidatorContato;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContatoService {

    private final ContatosRepository contatosRepository;

    public ContatoService(ContatosRepository contatosRepository) {
        this.contatosRepository = contatosRepository;
    }

    public Contato salvarContato(Contato contato) {
        if(this.buscarContatoPorEmail(contato.getEmail()) != null)
            throw new IllegalArgumentException("Já existe um contato com este e-mail.");

        ValidatorContato.getInstance().isValid(contato);
        return contatosRepository.save(contato);
    }

    public Contato buscarContatoPorId(long l) {
        Optional<Contato> result = contatosRepository.findById(l);
        return result.orElse(null);
    }

    public Contato buscarContatoPorEmail(String mail) {

        Optional<Contato> result = contatosRepository.findByEmail(mail);
        return result.orElse(null);
    }

    public void excluirContato(long l) {
        contatosRepository.deleteById(l);
    }
}
