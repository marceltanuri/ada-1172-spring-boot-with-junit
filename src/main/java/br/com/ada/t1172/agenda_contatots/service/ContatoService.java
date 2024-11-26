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

    public Contato salvarContato(Contato contato) {
        if (contatosRepository.findByEmail(contato.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Já existe um contato com este e-mail.");
        }
        if (contato.getNome().isEmpty()) {
            throw new IllegalArgumentException("O nome do contato é obrigatório.");
        }
        if (!contato.getEmail().contains("@") && !contato.getEmail().contains(".")) {
            throw new IllegalArgumentException("O e-mail do contato é obrigatório e deve ser válido.");
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

    public List<Contato> buscarContatoPorParteDoNome(String parteNome) {
        List<Contato> contatos = contatosRepository.findByNomeContaining(parteNome);
        if (contatos.isEmpty()) {
            throw new IllegalArgumentException("Nenhum contato encontrado.");
        }
        return contatos;
    }
}
