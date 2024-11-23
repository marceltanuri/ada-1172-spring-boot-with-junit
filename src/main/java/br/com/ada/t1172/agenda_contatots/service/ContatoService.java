package br.com.ada.t1172.agenda_contatots.service;

import br.com.ada.t1172.agenda_contatots.model.Contato;
import br.com.ada.t1172.agenda_contatots.repository.ContatosRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ContatoService {

    private final ContatosRepository contatosRepository;

    public ContatoService(ContatosRepository contatosRepository) {
        this.contatosRepository = contatosRepository;
    }

    public Contato salvarContato(Contato contato) {
        if(contatosRepository.findByEmail(contato.getEmail()).isPresent()){
            throw new IllegalArgumentException("Já existe um contato com este e-mail.");
        }

        if(Objects.isNull(contato.getNome()) || contato.getNome().isEmpty())
            throw new IllegalArgumentException("O nome do contato é obrigatório.");

        if(!isValidEmail(contato.getEmail()))
            throw new IllegalArgumentException("O e-mail do contato é obrigatório e deve ser válido.");

        return contato;//contatosRepository.save(contato);
    }

    public Contato buscarContatoPorId(long id) {
        return contatosRepository.findById(id).get();
    }

    public Contato buscarContatoPorEmail(String mail) {
        return contatosRepository.findByEmail(mail).get();
    }

    public void excluirContato(long id) {
        contatosRepository.deleteById(id);
    }

    private boolean isValidEmail(String email) {
        return email != null && email.matches("^[A-Za-z0-9+_.-]+@(.+)$");
    }

}
