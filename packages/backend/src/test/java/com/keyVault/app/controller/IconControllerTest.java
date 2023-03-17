package com.keyVault.app.controller;


import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.keyVault.app.dto.IconDTO;
import com.keyVault.app.entity.User;
import com.keyVault.app.repository.UserRepository;
import com.keyVault.app.security.TokenUtils;
import com.keyVault.app.service.IconService;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

@WebMvcTest(IconController.class)
public class IconControllerTest{

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IconService iconService;

    @Autowired
    private ObjectMapper objectMapper;
    
    @Test
    void testSaveIcon() throws Exception {
        //given
        IconDTO icon = new IconDTO("test", "test");
        given(iconService.createIcon(icon))
        .willAnswer((invocation) -> invocation.getArgument(0));
        String token = TokenUtils.createToken(323);
        //when
        ResultActions response = mockMvc.perform(post("/ui/icon")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(icon))
                );

        //then
        response.andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.url",is(icon.getUrl())))
                .andExpect(jsonPath("$.domain",is(icon.getDomain())));
    }
}
