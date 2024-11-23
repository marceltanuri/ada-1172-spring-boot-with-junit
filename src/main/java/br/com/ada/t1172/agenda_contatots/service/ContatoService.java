package br.com.ada.t1172.agenda_contatots.service;

import br.com.ada.t1172.agenda_contatots.model.Contato;
import br.com.ada.t1172.agenda_contatots.repository.ContatosRepository;
import br.com.ada.t1172.agenda_contatots.validator.Validator;
import br.com.ada.t1172.agenda_contatots.validator.impl.EmailValidator;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class ContatoService {

    private final ContatosRepository contatosRepository;

    public ContatoService(ContatosRepository contatosRepository) {
        this.contatosRepository = contatosRepository;
    }

    public Contato salvarContato(Contato contato) {
        if (contato.getNome() == null || contato.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("O nome do contato é obrigatório.");
        }

        Validator emailValidator = new EmailValidator(contato.getEmail());
        if (!emailValidator.validate()) {
            throw new IllegalArgumentException("O e-mail do contato é obrigatório e deve ser válido.");
        }

        contatosRepository.findByEmail(contato.getEmail())
                .ifPresent(c -> {
                    throw new IllegalArgumentException("Já existe um contato com este e-mail.");
                });

        return contatosRepository.save(contato);
    }



    public Contato buscarContatoPorId(long l) {
        return contatosRepository.findById(l)
                .orElseThrow(NoSuchElementException::new);
    }

    public Contato buscarContatoPorEmail(String email) {
        return contatosRepository.findByEmail(email)
                .orElseThrow(NoSuchElementException::new);
    }


    public void excluirContato(long id) {
//        if (!contatosRepository.existsById(id)) {
//            throw new NoSuchElementException();
//        }
        contatosRepository.deleteById(id);
    }

}
