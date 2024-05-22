package com.metaphorce.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.metaphorce.common.dtos.UserDTO;
import com.metaphorce.user.controllers.UserController;
import com.metaphorce.user.services.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testCreateUser() throws Exception {
        UserDTO userDTO = new UserDTO("1", "John Doe", "john@example.com");
        Mockito.when(userService.createUser(Mockito.any(UserDTO.class))).thenReturn(userDTO);

        mockMvc.perform(post("/api/v1/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is("1")))
                .andExpect(jsonPath("$.name", is("John Doe")))
                .andExpect(jsonPath("$.email", is("john@example.com")));
    }

    @Test
    public void testGetAllUsers() throws Exception {
        UserDTO userDTO = new UserDTO("1", "John Doe", "john@example.com");
        Page<UserDTO> userPage = new PageImpl<>(Arrays.asList(userDTO));

        Mockito.when(userService.getAllUsers(Mockito.any(Pageable.class))).thenReturn(userPage);

        mockMvc.perform(get("/api/v1/users")
                        .param("page", "0")
                        .param("size", "10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content", hasSize(1)))
                .andExpect(jsonPath("$.content[0].id", is("1")))
                .andExpect(jsonPath("$.content[0].name", is("John Doe")))
                .andExpect(jsonPath("$.content[0].email", is("john@example.com")))
                .andExpect(jsonPath("$.pageable.pageNumber", is(0)))
                .andExpect(jsonPath("$.pageable.pageSize", is(10)))
                .andExpect(jsonPath("$.totalElements", is(1)))
                .andExpect(jsonPath("$.totalPages", is(1)))
                .andExpect(jsonPath("$.last", is(true)));
    }

    @Test
    public void testGetUserById() throws Exception {
        UserDTO userDTO = new UserDTO("1", "John Doe", "john@example.com");
        Mockito.when(userService.getUserById("1")).thenReturn(userDTO);

        mockMvc.perform(get("/api/v1/users/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is("1")))
                .andExpect(jsonPath("$.name", is("John Doe")))
                .andExpect(jsonPath("$.email", is("john@example.com")));
    }

    @Test
    public void testUpdateUser() throws Exception {
        UserDTO userDTO = new UserDTO("1", "John Doe", "john@example.com");
        Mockito.when(userService.updateUser(Mockito.anyString(), Mockito.any(UserDTO.class))).thenReturn(userDTO);

        mockMvc.perform(put("/api/v1/users/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is("1")))
                .andExpect(jsonPath("$.name", is("John Doe")))
                .andExpect(jsonPath("$.email", is("john@example.com")));
    }

    @Test
    public void testDeleteUser() throws Exception {
        Mockito.doNothing().when(userService).deleteUser("1");

        mockMvc.perform(delete("/api/v1/users/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testGetUsersName() throws Exception {
        String[] names = {"John Doe", "Jane Doe"};
        Page<String> namePage = new PageImpl<>(Arrays.asList(names));

        Mockito.when(userService.getUsersName(Mockito.any(Pageable.class))).thenReturn(namePage);

        mockMvc.perform(get("/api/v1/users/names")
                        .param("page", "0")
                        .param("size", "10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content", hasSize(2)))
                .andExpect(jsonPath("$.content[0]", is("John Doe")))
                .andExpect(jsonPath("$.content[1]", is("Jane Doe")))
                .andExpect(jsonPath("$.pageable.pageNumber", is(0)))
                .andExpect(jsonPath("$.pageable.pageSize", is(10)))
                .andExpect(jsonPath("$.totalElements", is(2)))
                .andExpect(jsonPath("$.totalPages", is(1)))
                .andExpect(jsonPath("$.last", is(true)));
    }
}

