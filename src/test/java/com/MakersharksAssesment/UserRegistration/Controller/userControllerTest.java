package com.MakersharksAssesment.UserRegistration.Controller;

import com.MakersharksAssesment.UserRegistration.Entity.User;
import com.MakersharksAssesment.UserRegistration.Service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserService userService;
    User userOne;
    User userTwo;
    List<User> usersList = new ArrayList<>();

    @BeforeEach
    void setUp() {
        userOne = new User(1L, "Vikram",
                "vikram@gmail.com", "vikram123");
        userTwo = new User(2L, "Shweta Sharma",
                "shweta@gmail.com", "shweta123");
        usersList.add(userOne);
        usersList.add(userTwo);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testGetAllUserDetail() throws Exception{
        when(userService.getAllUserDetails()).thenReturn(usersList);
        this.mockMvc.perform(get("/api/user/fetch"))
                .andDo(print()).andExpect(status().isOk());
    }

    @Test
    void testGetUserDetailById() throws Exception{
        when(userService.getUserInfoById(1L)).thenReturn(userOne);
        this.mockMvc.perform(get("/api/user/fetch/1"))
                .andDo(print()).andExpect(status().isOk());
    }

    @Test
    void testAddUserDetail() throws Exception{
        //converting object to JSON
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter objectWriter = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = objectWriter.writeValueAsString(userOne);

        doNothing().when(userService).addUserDetail(userOne);
        this.mockMvc.perform(post("/api/user/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andDo(print()).andExpect(status().isOk());
    }
}
