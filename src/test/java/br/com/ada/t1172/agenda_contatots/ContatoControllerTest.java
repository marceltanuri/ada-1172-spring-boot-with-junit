package br.com.ada.t1172.agenda_contatots;

import br.com.ada.t1172.agenda_contatots.controller.ContatoController;
import br.com.ada.t1172.agenda_contatots.model.Contato;
import br.com.ada.t1172.agenda_contatots.service.ContatoService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ContatoControllerTest {

    @InjectMocks
    private ContatoController contatoController;

    @Mock
    private ContatoService contatoService;

    private Contato contato;
    private AutoCloseable closeable;

    @BeforeEach
    public void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
        contato = new Contato();
        contato.setId(1L);
        contato.setNome("Dr. Jamil Mascarenhas");
        contato.setEmail("dr.jamil.mascarenhas@mascarenhas.adv.com.br");
        contato.setTelefone("4199191919");
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }

    @Test
    public void salvarContato_DeveRetornarContato() {
        when(contatoService.salvarContato(contato)).thenReturn(contato);

        ResponseEntity<Contato> response = contatoController.salvarContato(contato);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(contato, response.getBody());
        verify(contatoService, times(1)).salvarContato(contato);
    }

    @Test
    public void salvarContato_DeveRetornarBadRequest() {
        when(contatoService.salvarContato(contato)).thenThrow(new IllegalArgumentException("O nome do contato é obrigatório."));

        ResponseEntity<Contato> response = contatoController.salvarContato(contato);

        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNull(response.getBody());
        verify(contatoService, times(1)).salvarContato(contato);
    }

    @Test
    public void buscarContatoPorId_DeveRetornarContato() {
        when(contatoService.buscarContatoPorId(1L)).thenReturn(contato);

        ResponseEntity<Contato> response = contatoController.buscarContatoPorId(1L);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(contato, response.getBody());
        verify(contatoService, times(1)).buscarContatoPorId(1L);
    }

    @Test
    public void buscarContatoPorId_DeveRetornarNotFound() {
        when(contatoService.buscarContatoPorId(1L)).thenThrow(new NoSuchElementException());

        ResponseEntity<Contato> response = contatoController.buscarContatoPorId(1L);

        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
        verify(contatoService, times(1)).buscarContatoPorId(1L);
    }

    @Test
    public void buscarContatoPorEmail_DeveRetornarContato() {
        when(contatoService.buscarContatoPorEmail("dr.jamil.mascarenhas@mascarenhas.adv.com.br")).thenReturn(contato);

        ResponseEntity<Contato> response = contatoController.buscarContatoPorEmail("dr.jamil.mascarenhas@mascarenhas.adv.com.br");

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(contato, response.getBody());
        verify(contatoService, times(1)).buscarContatoPorEmail("dr.jamil.mascarenhas@mascarenhas.adv.com.br");
    }

    @Test
    public void buscarContatoPorEmail_DeveRetornarNotFound() {
        when(contatoService.buscarContatoPorEmail("dr.jamil.mascarenhas@mascarenhas.adv.com.br")).thenThrow(new NoSuchElementException());

        ResponseEntity<Contato> response = contatoController.buscarContatoPorEmail("dr.jamil.mascarenhas@mascarenhas.adv.com.br");

        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
        verify(contatoService, times(1)).buscarContatoPorEmail("dr.jamil.mascarenhas@mascarenhas.adv.com.br");
    }

    @Test
    public void buscarContatoPorNome_DeveRetornarContatos() {
        Contato contato2 = new Contato();
        contato2.setId(2L);
        contato2.setNome("Jamil Platão");
        contato2.setEmail("jamil.platao@jamil.com.br");
        contato2.setTelefone("4199191919");

        Collection<Contato> contatos = Arrays.asList(contato, contato2);
        when(contatoService.buscarContatosPorNome("Jamil")).thenReturn(contatos);

        ResponseEntity<Collection<Contato>> response = contatoController.buscarContatoPorNome("Jamil");

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().size());
        verify(contatoService, times(1)).buscarContatosPorNome("Jamil");
    }

    @Test
    public void buscarContatoPorNome_DeveRetornarNoContent() {
        when(contatoService.buscarContatosPorNome("Barão")).thenReturn(Collections.emptyList());

        ResponseEntity<Collection<Contato>> response = contatoController.buscarContatoPorNome("Barão");

        assertNotNull(response);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertNull(response.getBody());
        verify(contatoService, times(1)).buscarContatosPorNome("Barão");
    }

    @Test
    public void excluirContato_DeveRetornarNoContent() {
        doNothing().when(contatoService).excluirContato(1L);

        ResponseEntity<Void> response = contatoController.excluirContato(1L);

        assertNotNull(response);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(contatoService, times(1)).excluirContato(1L);
    }
}
