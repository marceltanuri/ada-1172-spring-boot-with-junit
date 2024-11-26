package br.com.ada.t1172.agenda_contatots.controller;

import br.com.ada.t1172.agenda_contatots.model.Contato;
import br.com.ada.t1172.agenda_contatots.service.ContatoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contatos")
public class ContatoController {
    private final ContatoService contatoService;

    public ContatoController(ContatoService contatoService) {
        this.contatoService = contatoService;
    }

    @PostMapping("/salvar")
    public Contato salvarContato(@RequestBody Contato contato) {
        return contatoService.salvarContato(contato);
    }

    @GetMapping("/buscar/{id}")
    public Contato buscarContatoPorId(@PathVariable Long id) {
        return contatoService.buscarContatoPorId(id);
    }

    @GetMapping("/buscar/{email}")
    public Contato buscarContatoPorEmail(@PathVariable String email) {
        return contatoService.buscarContatoPorEmail(email);
    }

    @GetMapping("/buscar/{parte-nome}")
    public List<Contato> buscarContatoPorParteDoNome(@PathVariable String parteNome) {
        return contatoService.buscarContatoPorParteDoNome(parteNome);
    }

    @DeleteMapping("/excluir/{id}")
    public void excluirContato(@PathVariable Long id) {
        contatoService.excluirContato(id);
    }
}
