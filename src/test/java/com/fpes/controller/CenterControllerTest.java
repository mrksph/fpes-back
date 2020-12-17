package com.fpes.controller;

import com.fpes.exception.EntityNotFoundException;
import com.fpes.mapper.CenterCommentMapper;
import com.fpes.mapper.CenterMapper;
import com.fpes.mapper.CenterRatingMapper;
import com.fpes.model.Center;
import com.fpes.service.CenterService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CenterController.class)
@ActiveProfiles("test")
@WithMockUser(username = "test")
class CenterControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private CenterService service;
    @MockBean
    private CenterCommentMapper centerCommentMapper;
    @MockBean
    private CenterMapper centerMapper;
    @MockBean
    private CenterRatingMapper mapper;

    @Test
    void whenGetByIdNotFound_shouldReturnNotFound() throws Exception {
        //given
        long id = -1;
        when(service.findSingleCenter(id))
                .thenThrow(new EntityNotFoundException(id));
        MockHttpServletRequestBuilder request = get("/centers/" + id)
                .accept("application/json")
                .contentType("application/json");

        //when
        ResultActions perform = mockMvc.perform(request);
        //then
        perform.andExpect(status().isNotFound())
                .andReturn();
    }

    @Test
    void whenGetByIdFound_shouldReturnOk() throws Exception {
        //given
        long id = 1;
        when(service.findSingleCenter(id)).thenReturn(mock(Center.class));

        MockHttpServletRequestBuilder request = get("/centers/" + id)
                .accept("application/json")
                .contentType("application/json");

        //when
        ResultActions perform = mockMvc.perform(request);

        //then
        perform.andExpect(status().isOk())
                .andReturn();
    }

    @Test
    void whenGetByIdFound_shouldReturnResponse() throws Exception {
        //given
        long validId = 1;
        Center center = new Center();
        center.setName("");

        when(service.findSingleCenter(validId))
                .thenReturn(center);

        MockHttpServletRequestBuilder request = get("/centers/" + validId)
                .accept("application/json")
                .contentType("application/json");
        //when
        ResultActions perform = mockMvc.perform(request);

        //then
        perform
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(""))
                .andReturn();
    }
}