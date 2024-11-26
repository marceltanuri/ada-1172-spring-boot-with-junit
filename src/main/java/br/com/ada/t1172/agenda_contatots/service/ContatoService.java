package br.com.ada.t1172.agenda_contatots.service;

import br.com.ada.t1172.agenda_contatots.model.Contato;
import br.com.ada.t1172.agenda_contatots.repository.ContatosRepository;
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
        if (contato.getNome() == null || contato.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("O nome do contato é obrigatório.");
        }

        Optional<Contato> contatoComEmailExistente = contatosRepository.findByEmail(contato.getEmail());

        if (contatoComEmailExistente.isPresent()) {
            throw new IllegalArgumentException("Já existe um contato com este e-mail.");
        }

        if (contato.getEmail() == null || !contato.getEmail().matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            throw new IllegalArgumentException("O e-mail do contato é obrigatório e deve ser válido.");
        }

        return contatosRepository.save(contato);
    }

    public List<Contato> listarTodosContatos() {
        return (List<Contato>) contatosRepository.findAll();
    }

    public Iterable<Contato> buscarContatosPorNome(String nome) {
        return contatosRepository.findByNomeContainingIgnoreCase(nome);
    }

    public Contato buscarContatoPorId(long l) {
        Contato contato = contatosRepository.findById(l)
                .orElseThrow(() -> new RuntimeException("Contato: " + l + " - não encontrado."));

        return contato;
    }

    public Contato buscarContatoPorEmail(String mail) {
        Contato contato = contatosRepository.findByEmail(mail)
                .orElseThrow(() -> new RuntimeException("Contato: " + mail + " - não encontrado."));

        return contato;
    }

    public void excluirContato(long l) {
        contatosRepository.deleteById(l);
    }
}
