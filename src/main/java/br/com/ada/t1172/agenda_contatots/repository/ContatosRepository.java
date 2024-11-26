package br.com.ada.t1172.agenda_contatots.repository;

import br.com.ada.t1172.agenda_contatots.model.Contato;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.Optional;

public interface ContatosRepository extends CrudRepository<Contato, Long> {

    Collection<Contato> findByNomeContaining(String nome);

    Optional<Contato> findByEmail(String email);

}
