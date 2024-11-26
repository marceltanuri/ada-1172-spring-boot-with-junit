package br.com.ada.t1172.agenda_contatots;

import br.com.ada.t1172.agenda_contatots.controller.ContatoController;

import br.com.ada.t1172.agenda_contatots.model.Contato;
import br.com.ada.t1172.agenda_contatots.service.ContatoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
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
        contato.setEmail("joao.silva@teste.com");
        contato.setTelefone("11987654321");
    }

    @Test
    public void salvarContato_DeveSalvarComSucesso() {
        when(contatoService.salvarContato(contato)).thenReturn(contato);

        Contato resultado = contatoController.salvarContato(contato);

        assertNotNull(resultado);
        assertEquals("João Silva", resultado.getNome());
        assertEquals("joao.silva@teste.com", resultado.getEmail());
    }

    @Test
    public void buscarContatoPorId_DeveRetornarContato() {
        when(contatoService.buscarContatoPorId(1L)).thenReturn(contato);

        Contato resultado = contatoController.buscarContatoPorId(1L);

        assertNotNull(resultado);
        assertEquals("João Silva", resultado.getNome());
        assertEquals(1L, resultado.getId());
    }

    @Test
    public void buscarContatoPorEmail_DeveRetornarContato() {
        when(contatoService.buscarContatoPorEmail(anyString())).thenReturn(contato);

        Contato resultado = contatoController.buscarContatoPorEmail("joao.silva@teste.com");

        assertNotNull(resultado);
        assertEquals("João Silva", resultado.getNome());
    }

    @Test
    public void buscarContatoPorParteDoNome_DeveRetornarListaDeContatos() {
        when(contatoService.buscarContatoPorParteDoNome("João")).thenReturn(List.of(contato));

        List<Contato> resultado = contatoController.buscarContatoPorParteDoNome("João");

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("João Silva", resultado.get(0).getNome());
    }

    @Test
    public void excluirContato_DeveExcluirComSucesso() {
        doNothing().when(contatoService).excluirContato(1L);

        contatoController.excluirContato(1L);

        verify(contatoService, times(1)).excluirContato(1L);
    }
}