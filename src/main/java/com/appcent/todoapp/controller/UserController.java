package com.appcent.todoapp.controller;

import com.appcent.todoapp.model.User;
import com.appcent.todoapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.AccountNotFoundException;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/register")
    public User register(User user){
        return userService.register(user);
    }

    @GetMapping("/login")
    public String login(String username, String password, HttpServletResponse response) throws AccountNotFoundException {
        User user = userService.findByUsernameAndPassword(username,password);
        response.setHeader("id",user.getId().toString());
        return "Login successful";
    }
    @GetMapping("/show")
    public ArrayList<String> showToDoList(Long id){
        User user = userService.findById(id);
        return user.getToDoList();
    }

    @PostMapping("/add")
    public ArrayList<String> addToDoList(String toDo,Long id){
        return userService.addToDo(toDo,id);
    }

    @PostMapping("/update")
    public ArrayList<String> updateToDoList(String toDo, Long id, int index){
        return userService.updateToDo(toDo,id,index);
    }

    @PostMapping("/delete")
    public ArrayList<String> deleteToDoList(Long id,int index){
        return userService.deleteToDo(id,index);
    }

    @PostMapping("/delete/all")
    public ArrayList<String> deleteAllToDoList(Long id){
        return userService.deleteAllToDo(id);
    }
}
