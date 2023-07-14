package com.simplesdental.desafiojavabackend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.simplesdental.desafiojavabackend.controller.impl.ContatoControllerImpl;
import com.simplesdental.desafiojavabackend.dto.ContatoDto;
import com.simplesdental.desafiojavabackend.service.ContatoService;
import com.simplesdental.desafiojavabackend.util.HateoasUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Map;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ContatoControllerImpl.class)
@AutoConfigureMockMvc
class ContatoControllerImplIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ContatoService contatoService;

    @MockBean
    private HateoasUtil<ContatoDto> hateoasUtil;

    @MockBean
    private HateoasUtil<Map<String, Object>> hateoasUtilMap;

//    @Test
//    void getAllContatos_ReturnsOk() throws Exception {
//        List<Map<String, Object>> contatos = new ArrayList<>();
//        contatos.add(Map.of("id", 1L, "nome", "casa"));
//        contatos.add(Map.of("id", 2L, "nome", "celular"));
//        PagedResponse<Map<String, Object>> pagedResponse = new PagedResponse<>(contatos, 2);
//
//        when(contatoService.getAllContatos(any(Pageable.class), anyString(), any()))
//                .thenReturn(Page.empty())
//                .thenReturn(new PageImpl<>(contatos, Pageable.unpaged(), 2));
//
//        mockMvc.perform(get("/api/v1/contatos")
//                        .param("q", "texto")
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.content", hasSize(2)))
//                .andExpect(jsonPath("$.totalElements", is(2)));
//
//        verify(contatoService, times(2)).getAllContatos(any(Pageable.class), anyString(), any());
//    }

//    @Test
//    void createContato_ReturnsCreated() throws Exception {
//        ContatoRequest contatoRequest = ContatoRequest.builder()
//                .nome("Nome")
//                .contato("contato@example.com")
//                .build();
//
//        ContatoDto contatoDto = ContatoDto.builder()
//                .id(1L)
//                .nome("Nome")
//                .contato("contato@example.com")
//                .build();
//        when(contatoService.createContato(any(ContatoRequest.class)))
//                .thenReturn(contatoDto);
//
//        mockMvc.perform(post("/api/v1/contatos")
//                        .content(objectMapper.writeValueAsString(contatoRequest))
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isCreated())
//                .andExpect(jsonPath("$.id", is(1)))
//                .andExpect(jsonPath("$.nome", is("Nome")))
//                .andExpect(jsonPath("$.contato", is("contato@example.com")));
//
//        verify(contatoService).createContato(any(ContatoRequest.class));
//    }

//    @Test
//    void updateContato_ReturnsOk() throws Exception {
//        Long id = 1L;
//        ContatoRequest contatoRequest = ContatoRequest.builder()
//                .nome("celular")
//                .contato("34235252")
//                .build();
//
//        ContatoDto contatoDto = ContatoDto.builder()
//                .id(id)
//                .nome("celular")
//                .contato("34235252")
//                .build();
//        when(contatoService.updateContato(eq(id), any(ContatoRequest.class)))
//                .thenReturn(contatoDto);
//
//        mockMvc.perform(put("/api/v1/contatos/{id}", id)
//                        .content(objectMapper.writeValueAsString(contatoRequest))
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.id", is(1)))
//                .andExpect(jsonPath("$.nome", is("celular")))
//                .andExpect(jsonPath("$.contato", is("34235252")));
//
//        verify(contatoService).updateContato(eq(id), any(ContatoRequest.class));
//    }

    @Test
    void getContatoById_ReturnsOk() throws Exception {
        Long id = 1L;

        ContatoDto contatoDto = ContatoDto.builder()
                .id(id)
                .nome("Contato")
                .contato("contato@example.com")
                .build();
        when(contatoService.getContatoById(eq(id)))
                .thenReturn(contatoDto);

        mockMvc.perform(get("/api/v1/contatos/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.nome", is("Contato")))
                .andExpect(jsonPath("$.contato", is("contato@example.com")));

        verify(contatoService).getContatoById(eq(id));
    }

    @Test
    void getContatoById_ReturnsNoContent_WhenNotFound() throws Exception {
        Long id = 1L;

        when(contatoService.getContatoById(eq(id)))
                .thenReturn(null);

        mockMvc.perform(get("/api/v1/contatos/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        verify(contatoService).getContatoById(eq(id));
    }

    @Test
    void deleteContato_ReturnsNoContent() throws Exception {
        Long id = 1L;

        mockMvc.perform(delete("/api/v1/contatos/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        verify(contatoService).deleteContato(eq(id));
    }
}