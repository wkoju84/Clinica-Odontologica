package br.com.dh.clinica.controllers;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import br.com.dh.clinica.dtos.DentistaDto;
import br.com.dh.clinica.entities.Dentista;
import br.com.dh.clinica.services.DentistaService;
import br.com.dh.clinica.services.exceptions.BancoDeDadosException;
import br.com.dh.clinica.services.exceptions.EntidadeNaoEncontradaException;
import br.com.dh.clinica.tests.Factory;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;

@WebMvcTest(DentistaController.class)
public class DentistaControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DentistaService service;

    @Autowired
    private ObjectMapper objectMapper;

    private Integer idExistente;
    private Integer idInexistente;
    private Integer idDependente;
    private DentistaDto dto;
    private List<DentistaDto> list;

    @BeforeEach
    void setup() throws Exception{
        idExistente = 1;
        idInexistente = 999;
        idDependente = 2;
        dto = Factory.criarDentistaDto();
        list = new ArrayList<>();

        // Mock do findAll
        when(service.buscarTodos()).thenReturn(list);

        //Mock do findById
        when(service.buscarPorId(idExistente)).thenReturn(dto);
        when(service.buscarPorId(idInexistente)).thenThrow(EntidadeNaoEncontradaException.class);

        //Mock do save
        when(service.inserir(any())).thenReturn(dto);

        //Mock do update
        when(service.atualizar(eq(idExistente), any())).thenReturn(dto);
        when(service.atualizar(eq(idInexistente), any())).thenThrow(EntidadeNaoEncontradaException.class);

        //Mock do delete
        doNothing().when(service).excluir(idExistente);

        //Mock do delete com Exception de entidade não encontrada
        doThrow(EntidadeNaoEncontradaException.class).when(service).excluir(idInexistente);

        //Mock do delete com Exception de violação de integridade
        doThrow(BancoDeDadosException.class).when(service).excluir(idDependente);
    }

    // Teste do método findAll
    @Test
    public void findAllDeveriaRetornarUmaLista() throws Exception {
        ResultActions resultado = mockMvc.perform(get("/dentistas").accept(MediaType.APPLICATION_JSON));
        resultado.andExpect(status().isOk());
    }

    // Teste do método findById
    @Test
    public void findByIdDeveriaRetornarUmDto() throws Exception{
        ResultActions resultado = mockMvc.perform(get("/dentistas/{id}", idExistente)
                .accept(MediaType.APPLICATION_JSON));
        resultado.andExpect(status().isOk());
        resultado.andExpect(jsonPath("$.id").exists());
        resultado.andExpect(jsonPath("$.nome").exists());
    }

    // Teste do método findById retornando uma Exceção
    @Test
    public void findByIdDeveriaRetornarUm404() throws Exception{
        ResultActions resultado = mockMvc.perform(get("/dentistas/{id}", idInexistente)
                .accept(MediaType.APPLICATION_JSON));
        resultado.andExpect(status().isNotFound());
    }

    // Teste do método save
    @Test
    public void insertDeveriaRetornarUm201() throws Exception{
        String jsonBody = objectMapper.writeValueAsString(dto);

        ResultActions resultado = mockMvc.perform(post("/dentistas")
                .content(jsonBody)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        resultado.andExpect(status().isCreated());
        resultado.andExpect(jsonPath("$.id").exists());
        resultado.andExpect(jsonPath("$.nome").exists());
    }

    // Teste do método update
    @Test
    public void updateDeveriaRetornarUm200QuandoOIdExistir() throws Exception{
        String jsonBody = objectMapper.writeValueAsString(dto);

        ResultActions resultado = mockMvc.perform(put("/dentistas/{id}", idExistente)
                .content(jsonBody)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        resultado.andExpect(status().isOk());
        resultado.andExpect(jsonPath("$.id").exists());
        resultado.andExpect(jsonPath("$.nome").exists());
    }

    // Teste do método update com exceção
    @Test
    public void updateDeveriaRetornarUm404QuandoOIdNaoExistir() throws Exception{
        String jsonBody = objectMapper.writeValueAsString(dto);

        ResultActions resultado = mockMvc.perform(put("/dentistas/{id}", idInexistente)
                .content(jsonBody)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        resultado.andExpect(status().isNotFound());
    }

    // Teste do método delete
    @Test
    public void deleteDeveriaRetornarUm204QuandoOIdExistir() throws Exception{
        ResultActions resultado = mockMvc.perform(delete("/dentistas/{id}", idExistente)
                .accept(MediaType.APPLICATION_JSON));
        resultado.andExpect(status().isNoContent());
    }

    // Teste do método delete com exceção de recurso não encontrado
    @Test
    public void deleteDeveriaRetornarUm404QuandoOIdNaoExistir() throws Exception{
        ResultActions resultado = mockMvc.perform(delete("/dentistas/{id}", idInexistente)
                .accept(MediaType.APPLICATION_JSON));
        resultado.andExpect(status().isNotFound());
    }

}
