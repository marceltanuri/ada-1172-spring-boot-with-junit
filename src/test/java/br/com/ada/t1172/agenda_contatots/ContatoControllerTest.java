package br.com.ada.t1172.agenda_contatots;

import br.com.ada.t1172.agenda_contatots.controller.ContatoController;
import br.com.ada.t1172.agenda_contatots.model.Contato;
import br.com.ada.t1172.agenda_contatots.service.ContatoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ContatoControllerTest {

    @InjectMocks
    private ContatoController contatoController;

    @Mock
    private ContatoService contatoService;

    private Contato contato;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        contato = new Contato();
        contato.setId(1L);
        contato.setNome("João Silva");
        contato.setEmail("joao.silva@example.com");
        contato.setTelefone("11987654321");
    }

    @Test
    public void listarContatos_DeveRetornarListaDeContatos() {
        when(contatoService.listarContatos()).thenReturn(Collections.singletonList(contato));

        Iterable<Contato> resultado = contatoController.listarContatos();

        assertNotNull(resultado);
        assertTrue(((List<Contato>) resultado).contains(contato));
        verify(contatoService, times(1)).listarContatos();
    }

    @Test
    public void buscarContatoPorId_DeveRetornarContato() {
        when(contatoService.buscarContatoPorId(1L)).thenReturn(contato);

        Contato resultado = contatoController.buscarContatoPorId(1L);

        assertNotNull(resultado);
        assertEquals(contato.getId(), resultado.getId());
        verify(contatoService, times(1)).buscarContatoPorId(1L);
    }

    @Test
    public void buscarContatoPorEmail_DeveRetornarContato() {
        when(contatoService.buscarContatoPorEmail("joao.silva@example.com")).thenReturn(contato);

        Contato resultado = contatoController.buscarContatoPorEmail("joao.silva@example.com");

        assertNotNull(resultado);
        assertEquals(contato.getEmail(), resultado.getEmail());
        assertEquals(contato.getNome(), resultado.getNome());
        verify(contatoService, times(1)).buscarContatoPorEmail("joao.silva@example.com");
    }

    @Test
    public void salvarContato_DeveSalvarComSucesso() {
        when(contatoService.salvarContato(contato)).thenReturn(contato);

        Contato resultado = contatoController.salvarContato(contato);

        assertNotNull(resultado);
        assertEquals(contato.getNome(), resultado.getNome());
        verify(contatoService, times(1)).salvarContato(contato);
    }

    @Test
    public void excluirContato_DeveChamarServico() {
        contatoController.excluirContato(1L);

        verify(contatoService, times(1)).excluirContato(1L);
    }
}
