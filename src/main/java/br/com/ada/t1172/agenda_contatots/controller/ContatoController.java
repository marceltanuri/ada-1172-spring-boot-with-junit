package br.com.ada.t1172.agenda_contatots.controller;

import br.com.ada.t1172.agenda_contatots.model.Contato;
import br.com.ada.t1172.agenda_contatots.service.ContatoService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("contatos")
public class ContatoController {

    private final ContatoService contatoService;

    public ContatoController(ContatoService contatoService) {
        this.contatoService = contatoService;
    }

    @GetMapping("/")
    public Iterable<Contato> listarContatos() {
        return contatoService.listarContatos();
    }

    @GetMapping("/{id}")
    public Contato buscarContatoPorId(@PathVariable Long id) {
        return contatoService.buscarContatoPorId(id);
    }

    @GetMapping("/email")
    public Contato buscarContatoPorEmail(@RequestBody String email) {
        return contatoService.buscarContatoPorEmail(email);
    }

    @PostMapping("/salvar")
    public Contato salvarContato(@RequestBody Contato contato) {
        return contatoService.salvarContato(contato);
    }

    @DeleteMapping("/excluir/{id}")
    public void excluirContato(@PathVariable Long id) {
        contatoService.excluirContato(id);
    }
}
