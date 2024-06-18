package com.MakersharksAssesment.UserRegistration.Repository;

import com.MakersharksAssesment.UserRegistration.Entity.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class UserRepositoryTest {
    //given-when-then
    @Autowired
    private UserRepository userRepository;
    User user;

    @BeforeEach
    void setUp(){
        user = new User
                (1L, "Vikram Jai Singh",
                        "vikram@gmail.com", "vikram123");
        userRepository.save(user);
    }

    @AfterEach
    void tearDown(){
        user = null;
        userRepository.deleteAll();
    }


}
