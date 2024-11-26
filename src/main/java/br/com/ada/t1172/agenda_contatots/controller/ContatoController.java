package br.com.ada.t1172.agenda_contatots.controller;

import br.com.ada.t1172.agenda_contatots.model.Contato;
import br.com.ada.t1172.agenda_contatots.service.ContatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/contatos")
public class ContatoController {

    private final ContatoService contatoService;

    @Autowired
    public ContatoController(ContatoService contatoService) {
        this.contatoService = contatoService;
    }

    @PostMapping
    public ResponseEntity<Contato> salvarContato(@RequestBody Contato contato) {
        try {
            Contato novoContato = contatoService.salvarContato(contato);
            return ResponseEntity.ok(novoContato);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Contato> buscarContatoPorId(@PathVariable Long id) {
        try {
            Contato contato = contatoService.buscarContatoPorId(id);
            return ResponseEntity.ok(contato);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<Contato> buscarContatoPorEmail(@PathVariable String email) {
        try {
            Contato contato = contatoService.buscarContatoPorEmail(email);
            return ResponseEntity.ok(contato);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<Collection<Contato>> buscarContatoPorNome(@PathVariable String nome) {
        Collection<Contato> contatos = contatoService.buscarContatosPorNome(nome);
        if (contatos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(contatos);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirContato(@PathVariable Long id) {
        try {
            contatoService.excluirContato(id);
            return ResponseEntity.noContent().build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
