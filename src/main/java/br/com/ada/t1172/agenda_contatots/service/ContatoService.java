package br.com.ada.t1172.agenda_contatots.service;

import br.com.ada.t1172.agenda_contatots.model.Contato;
import br.com.ada.t1172.agenda_contatots.repository.ContatosRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContatoService {

    private final ContatosRepository contatosRepository;

    public ContatoService(ContatosRepository contatosRepository) {
        this.contatosRepository = contatosRepository;
    }

    public Iterable<Contato> listarContatos() {
        return contatosRepository.findAll();
    }

    public Contato salvarContato(Contato contato) {
        if (contato.getNome() == null || contato.getNome().isEmpty()) {
            throw new IllegalArgumentException("O nome do contato é obrigatório.");
        }
        if (contato.getEmail() == null || !contato.getEmail().matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            throw new IllegalArgumentException("O e-mail do contato é obrigatório e deve ser válido.");
        }
        if (contatosRepository.findByEmail(contato.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Já existe um contato com este e-mail.");
        }
        return contatosRepository.save(contato);
    }

    public Contato buscarContatoPorId(long l) {
        return contatosRepository.findById(l).orElse(null);
    }

    public Contato buscarContatoPorEmail(String mail) {
        return contatosRepository.findByEmail(mail).orElse(null);
    }

    public void excluirContato(long l) {
        contatosRepository.deleteById(l);
    }
}
