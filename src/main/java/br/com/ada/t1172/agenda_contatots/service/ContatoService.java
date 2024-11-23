package br.com.ada.t1172.agenda_contatots.service;

import br.com.ada.t1172.agenda_contatots.model.Contato;
import br.com.ada.t1172.agenda_contatots.repository.ContatosRepository;
import org.springframework.stereotype.Service;

import java.nio.file.OpenOption;
import java.util.List;
import java.util.Optional;

@Service
public class ContatoService {

    private final ContatosRepository contatosRepository;

    public ContatoService(ContatosRepository contatosRepository) {
        this.contatosRepository = contatosRepository;
    }

    public Contato salvarContato(Contato contato) throws IllegalArgumentException {
        if (contato.getNome().isBlank()) throw new IllegalArgumentException("O nome do contato é obrigatório.");
        contatosRepository.findByEmail(contato.getEmail())
        .ifPresent(existingContato -> {
            throw new IllegalArgumentException("Já existe um contato com este e-mail.");
        });

        return contatosRepository.save(contato);
    }

    public Contato buscarContatoPorId(Long l) {
        return contatosRepository.findById(l)
            .orElseThrow(() -> new IllegalArgumentException("Contato não encontrado para o ID " + l));
    }

    public Contato buscarContatoPorEmail(String mail) {
        return contatosRepository.findByEmail(mail)
            .orElseThrow(() -> new IllegalArgumentException("Contato não encontrado para o email " + mail));
    }

    public void excluirContato(long l) {
        contatosRepository.deleteById(l);
    }
}
