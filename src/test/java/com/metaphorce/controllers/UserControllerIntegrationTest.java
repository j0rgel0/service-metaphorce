package com.metaphorce.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.metaphorce.common.dtos.UserDTO;
import com.metaphorce.security.utils.JwtUtils;
import com.metaphorce.user.respositories.UserRepository;
import com.metaphorce.user.services.UserService;
import com.metaphorce.user.services.impl.UserServiceImpl;
import io.github.cdimascio.dotenv.Dotenv;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Import(UserServiceImpl.class)
@Transactional
@ActiveProfiles("test")
class UserControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtils jwtUtils;

    private List<String> createdUserEmails;

    @BeforeEach
    public void setup() {
        createdUserEmails = new ArrayList<>();
    }

    @AfterEach
    public void tearDown() {
        for (String email : createdUserEmails) {
            userRepository.deleteByEmail(email);
        }
        createdUserEmails.clear();
    }

    @DynamicPropertySource
    static void dynamicPropertySource(DynamicPropertyRegistry registry) {
        Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();
        dotenv.entries().forEach(entry ->
                registry.add(entry.getKey(), entry::getValue));
    }


    @Test
    @WithMockUser(username = "admin", roles = {"ADMINISTRATOR"})
    public void testCreateUser() throws Exception {
        String email = "jorge.perez+" + UUID.randomUUID().toString() + "@example.com";
        UserDTO userDTO = new UserDTO(null, "Jorge Perez", email, "securePassword", LocalDateTime.now(), null, false, "ADMIN");

        mockMvc.perform(post("/api/v1/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", notNullValue()))
                .andExpect(jsonPath("$.name", is("Jorge Perez")))
                .andExpect(jsonPath("$.email", is(email)))
                .andExpect(jsonPath("$.role", is("ADMIN")));
        createdUserEmails.add(email); // Ensure cleanup
    }


    @Test
    public void testUpdateUser() throws Exception {
        String email = "jorge.perez+" + UUID.randomUUID().toString() + "@example.com";
        UserDTO userDTO = new UserDTO(null, "Jorge Perez", email, "securePassword", LocalDateTime.now(), null, false, "ADMIN");
        UserDTO createdUser = userService.createUser(userDTO);
        createdUserEmails.add(email);

        String newEmail = "jorge.arturo+" + UUID.randomUUID().toString() + "@example.com";
        UserDTO updatedUserDTO = new UserDTO(createdUser.getId(), "Jorge Arturo", email, "securePassword", LocalDateTime.now(), null, false, "ADMIN");

        mockMvc.perform(put("/api/v1/users/" + createdUser.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedUserDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(createdUser.getId())))
                .andExpect(jsonPath("$.name", is("Jorge Arturo")))
                .andExpect(jsonPath("$.email", is(newEmail)));
    }

    @Test
    public void testDeleteUser() throws Exception {
        String email = "jorge.perez+" + UUID.randomUUID().toString() + "@example.com";
        UserDTO userDTO = new UserDTO(null, "Jorge Perez", email, "securePassword", LocalDateTime.now(), null, false, "ADMIN");
        UserDTO createdUser = userService.createUser(userDTO);
        createdUserEmails.add(email);

        mockMvc.perform(delete("/api/v1/users/" + createdUser.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}
