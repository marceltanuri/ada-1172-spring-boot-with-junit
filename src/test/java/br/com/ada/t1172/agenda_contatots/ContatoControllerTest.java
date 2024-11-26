package br.com.ada.t1172.agenda_contatots;

import br.com.ada.t1172.agenda_contatots.controller.ContatoController;
import br.com.ada.t1172.agenda_contatots.model.Contato;
import br.com.ada.t1172.agenda_contatots.service.ContatoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class ContatoControllerTest {

    @InjectMocks
    private ContatoController contatoController;

    @Mock
    private ContatoService contatoService;

    private Contato contato;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this); // Inicializa os mocks
        contato = new Contato();
        contato.setId(1L);
        contato.setNome("pedro Silva");
        contato.setEmail("pedro.silva@example.com");
        contato.setTelefone("11987654321");
    }

    @Test
    public void salvarContato_DeveSalvarComSucesso() {
        when(contatoService.salvarContato(contato)).thenReturn(contato);

        ResponseEntity<Contato> response = contatoController.salvar(contato);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("pedro Silva", response.getBody().getNome());
        verify(contatoService, times(1)).salvarContato(contato);
    }

    @Test
    public void listarTodosContatos_DeveRetornarListaDeContatos() {
        List<Contato> contatos = Arrays.asList(contato);
        when(contatoService.listarTodosContatos()).thenReturn(contatos);

        ResponseEntity<List<Contato>> response = contatoController.listarTodosContatos();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
        verify(contatoService, times(1)).listarTodosContatos(); // Verifica se o listarTodosContatos foi chamado
    }

    @Test
    public void buscarPorId_DeveRetornarContato() {
        when(contatoService.buscarContatoPorId(1L)).thenReturn(contato);

        ResponseEntity<Contato> response = contatoController.buscarPorId(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1L, response.getBody().getId());
        assertEquals("pedro Silva", response.getBody().getNome());
        verify(contatoService, times(1)).buscarContatoPorId(1L);
    }

    @Test
    public void buscarPorEmail_DeveRetornarContato() {
        when(contatoService.buscarContatoPorEmail("pedro.silva@example.com")).thenReturn(contato);

        ResponseEntity<Contato> response = contatoController.buscarPorEmail("pedro.silva@example.com");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("pedro.silva@example.com", response.getBody().getEmail());
        verify(contatoService, times(1)).buscarContatoPorEmail("pedro.silva@example.com");
    }

    @Test
    public void delete_DeveExcluirComSucesso() {
        doNothing().when(contatoService).excluirContato(1L);

        ResponseEntity<Void> response = contatoController.delete(1L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(contatoService, times(1)).excluirContato(1L);
    }
}
