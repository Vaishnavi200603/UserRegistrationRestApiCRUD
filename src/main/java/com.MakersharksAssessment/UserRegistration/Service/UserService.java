package com.MakersharksAssesment.UserRegistration.Service;

import com.MakersharksAssesment.UserRegistration.Entity.User;
import java.util.List;

public interface UserService{
    List<User> getAllUserDetails();
    User getUserInfoById(Long id);
    void addUserDetail(User user);
}
