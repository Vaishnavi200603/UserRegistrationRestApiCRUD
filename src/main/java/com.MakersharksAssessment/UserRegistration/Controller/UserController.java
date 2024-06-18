package com.MakersharksAssesment.UserRegistration.Controller;

import com.MakersharksAssesment.UserRegistration.Entity.User;
import com.MakersharksAssesment.UserRegistration.Exceptions.UserNotFoundException;
import com.MakersharksAssesment.UserRegistration.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;
    public UserController(UserService userService){
        this.userService = userService;
    }
    @GetMapping("/fetch")
    public ResponseEntity<List<User>> getAllUserDetail(){
        return new ResponseEntity<>(userService.getAllUserDetails(), HttpStatus.OK);
    }

    @GetMapping("/fetch/{id}")
    public ResponseEntity<User> getUserDetailById(@PathVariable Long id){
        User user = userService.getUserInfoById(id);
        if(user == null) {
            throw new UserNotFoundException("Requested User Not Exist");
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
//        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/register")
    public ResponseEntity<String> addUserDetail(@RequestBody User user) {
        userService.addUserDetail(user);
        return new ResponseEntity<>("User Detail Added Successfully", HttpStatus.OK);
    }
}
