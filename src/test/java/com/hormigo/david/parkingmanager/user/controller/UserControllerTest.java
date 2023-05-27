package com.hormigo.david.parkingmanager.user.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hormigo.david.parkingmanager.user.domain.Role;
import com.hormigo.david.parkingmanager.user.domain.User;
import com.hormigo.david.parkingmanager.user.domain.UserDao;
import com.hormigo.david.parkingmanager.user.service.UserService;
import com.hormigo.david.parkingmanager.user.service.UserServiceImpl;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Optional;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserService userService;

    @Test
    public void testSingleUserRead() throws Exception { // GET /api/users/{id}
        ObjectMapper mapper = new ObjectMapper();
        User user = new User("dhorram948@g.educaand.es", "David", "Hormigo", "Ramírez", Role.PROFESSOR);
        String json = mapper.writeValueAsString(user);
        when(userService.getUser(2)).thenReturn(Optional.of(user));
        this.mockMvc.perform(get("/api/users/2"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(json));
    }

    @Test
    public void testAllUserRead() throws Exception { // GET /api/users
        ObjectMapper mapper = new ObjectMapper();
        User user = new User("dhorram948@g.educaand.es", "David", "Hormigo", "Ramírez", Role.PROFESSOR);
        ArrayList<User> usuarios = new ArrayList<>();
        usuarios.add(user);
        String json = mapper.writeValueAsString(usuarios);
        json = "{ \"_embedded\": {\"userList\":" + json + "}}";
        when(userService.getAll()).thenReturn(usuarios);
        this.mockMvc.perform(get("/api/users"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(json));
    }

    @Test
    public void testBorrarUser() throws Exception { // DELETE /api/users
        this.mockMvc.perform(delete("/api/users/{id}", 2)).andExpect(status().isNoContent());
    }

    @Test
    public void testComprobarTodoCorrecto() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        UserDao dao = new UserDao("da@correo.es", "David", "Hormigo", "Ramírez", Role.PROFESSOR);
        String json = mapper.writeValueAsString(dao);
        when(this.userService.register(any(UserDao.class)))
                .thenReturn(new User("da@correo.es", "David", "Hormigo", "Ramírez", Role.PROFESSOR));
        this.mockMvc.perform(post("/api/users")
                .contentType("application/json").content(json))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    public void testEditarUser() throws Exception { // PATCH /api/users/{id}

    }

    @Test
    public void testEmailDuplicado() throws Exception { // POST /api/users (test positivo y negativos)
        
    }

    @Test
    public void testEmailNulo() throws Exception { // POST /api/users (test positivo y negativos)

    }

    @Test
    public void testNombreNulo() throws Exception { // POST /api/users (test positivo y negativos)

    }

    @Test
    public void testApellidoNulo() throws Exception { // POST /api/users (test positivo y negativos)

    }
}
