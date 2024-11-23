package br.com.ada.t1172.agenda_contatots.service;

import br.com.ada.t1172.agenda_contatots.model.Contato;
import br.com.ada.t1172.agenda_contatots.repository.ContatosRepository;
import br.com.ada.t1172.agenda_contatots.util.Impl.EmailValidator;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ContatoService {

    private final ContatosRepository contatosRepository;

    public ContatoService(ContatosRepository contatosRepository) {
        this.contatosRepository = contatosRepository;
    }

    public Contato salvarContato(Contato contato) {
        if (contato.getNome().isEmpty()) throw new IllegalArgumentException("O nome do contato é obrigatório.");
        if (!new EmailValidator(contato.getEmail()).validate()) throw new IllegalArgumentException("O e-mail do contato é obrigatório e deve ser válido.");
        if (contatosRepository.findByEmail(contato.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Já existe um contato com este e-mail.");
        }
        return contatosRepository.save(contato);
    }

    public Contato buscarContatoPorId(long id) {
        Optional<Contato> contato = contatosRepository.findById(id);
        if (contato.isEmpty()) {
            throw new EntityNotFoundException("Contato não encontrado");
        }
        return contato.get();
    }

    public Contato buscarContatoPorEmail(String mail) {
        Optional<Contato> contato = contatosRepository.findByEmail(mail);
        if (contato.isEmpty()) {
            throw new EntityNotFoundException("Contato não encontrado");
        }
        return contato.get();
    }

    public void excluirContato(long id) {
//        if (!contatosRepository.existsById(id)) {
//            throw new EntityNotFoundException("Contato nao encontrado");
//        }
        contatosRepository.deleteById(id);
    }
}
