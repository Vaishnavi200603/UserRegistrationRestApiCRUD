package com.MakersharksAssesment.UserRegistration.Service.Impl;

import com.MakersharksAssesment.UserRegistration.Entity.User;
import com.MakersharksAssesment.UserRegistration.Repository.UserRepository;
import com.MakersharksAssesment.UserRegistration.Service.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserDetailImplTest {
    @Mock
    private UserRepository userRepository;
    private UserService userService;
    //when entire test case get executed, then it's auto close all the resources
    AutoCloseable autoCloseable;
    User user;

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        userService = new UserDetailImpl(userRepository);
        user = new User(1L, "Vikram Jai Singh",
                        "vikram@gmail.com", "vikram123");
    }

    @AfterEach
    void tearDown() throws Exception{
        autoCloseable.close();
    }

    @Test
    void testGetAllUserDetails() {
        mock(User.class);
        mock(UserRepository.class);
        when(userRepository.findAll()).thenReturn(
                new ArrayList<User>(Collections.singleton(user))
        );
        assertEquals(userService.getAllUserDetails().get(0).getEmail(),
                user.getEmail());
    }

    @Test
    void testGetUserInfoById() {
        mock(User.class);
        mock(UserRepository.class);
        when(userRepository.findById(1L)).thenReturn(Optional.ofNullable(user));
        assertEquals(userService.getUserInfoById(1L).getUserName(),
                user.getUserName());
    }

    @Test
    void testAddUserDetail() {
        mock(User.class);
        mock(UserRepository.class);
        when(userRepository.save(user)).thenReturn(user);
        userService.addUserDetail(user);

        //verification
        ArgumentCaptor<User> argumentCaptor = ArgumentCaptor.forClass(User.class);
        verify(userRepository, times(1)).save(argumentCaptor.capture());
        User capturedUser = argumentCaptor.getValue();

        //assert
        assertNotNull(capturedUser);
        assertEquals(user.getUserName(), capturedUser.getUserName());
    }
}
