package br.com.ada.t1172.agenda_contatots.repository;

import br.com.ada.t1172.agenda_contatots.model.Contato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ContatosRepository extends CrudRepository<Contato, Long> {

    Iterable<Contato> findByNomeContaining(String nome);

    Optional<Contato> findByEmail(String email);

}
