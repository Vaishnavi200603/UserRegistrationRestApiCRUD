package com.MakersharksAssesment.UserRegistration.Service.Impl;

import com.MakersharksAssesment.UserRegistration.Entity.User;
import com.MakersharksAssesment.UserRegistration.Repository.UserRepository;
import com.MakersharksAssesment.UserRegistration.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserDetailImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    public UserDetailImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    @Override
    public List<User> getAllUserDetails() {
        return userRepository.findAll();
    }

    @Override
    public User getUserInfoById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public void addUserDetail(User user) {
        userRepository.save(user);
    }
}
