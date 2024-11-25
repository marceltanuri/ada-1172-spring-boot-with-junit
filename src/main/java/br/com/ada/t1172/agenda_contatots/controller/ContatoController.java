package br.com.ada.t1172.agenda_contatots.controller;

import br.com.ada.t1172.agenda_contatots.model.Contato;
import br.com.ada.t1172.agenda_contatots.service.ContatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("contato")
public class ContatoController {

    @Autowired
    private ContatoService contatoService;

    @PostMapping
    public ResponseEntity<Contato> salvar(@RequestBody Contato contato) {
        return ResponseEntity.status(HttpStatus.CREATED).body(contatoService.salvarContato(contato));
    }

    @GetMapping
    public ResponseEntity<List<Contato>> listarTodosContatos() {
        List<Contato> contatos = contatoService.listarTodosContatos();
        return ResponseEntity.ok(contatos);
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<Iterable<Contato>> buscarPorNome(@PathVariable("nome") String nome) {
        Iterable<Contato> contatos = contatoService.buscarContatosPorNome(nome);
        return ResponseEntity.ok(contatos);
    }

    @GetMapping("{id}")
    public ResponseEntity<Contato> buscarPorId(@PathVariable("id") Long id) {
        Contato contato = contatoService.buscarContatoPorId(id);
        return ResponseEntity.ok(contato);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<Contato> buscarPorEmail(@PathVariable("email") String email) {
        Contato contato = contatoService.buscarContatoPorEmail(email);
        return ResponseEntity.ok(contato);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        contatoService.excluirContato(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
