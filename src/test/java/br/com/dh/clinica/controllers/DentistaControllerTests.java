package br.com.dh.clinica.controllers;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import br.com.dh.clinica.dtos.DentistaDto;
import br.com.dh.clinica.entities.Dentista;
import br.com.dh.clinica.services.DentistaService;
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
        ResultActions resultado = mockMvc.perform(get("/dentistas/{id}", idExistente).accept(MediaType.APPLICATION_JSON));
        resultado.andExpect(status().isOk());
    }

}
