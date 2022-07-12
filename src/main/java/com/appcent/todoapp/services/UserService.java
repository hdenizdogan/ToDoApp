package com.appcent.todoapp.services;

import com.appcent.todoapp.model.User;
import com.appcent.todoapp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.security.auth.login.AccountNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class UserService {

    @Autowired
    UserRepository userRepository;


    public User register(User user){
        return userRepository.save(user);
    }

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public User findById(Long id){
        Optional<User> optionalUser = userRepository.findById(id);
        User user = optionalUser.get();
        return user;
    }

    public User findByUsernameAndPassword(String username, String password) throws AccountNotFoundException {
        try{
            User user = userRepository.findByUsername(username);
            if(user.getPassword().equals(password)){
                return user;
            }
        }
        catch(Exception e){
            throw new AccountNotFoundException();
        }
        return null;
    }

    public ArrayList<String> addToDo(String string,Long id){
        User user = findById(id);
        ArrayList<String> toDoList = user.getToDoList();
        toDoList.add(string);
        user.setToDoList(toDoList);
        userRepository.save(user);
        return toDoList;
    }

    public ArrayList<String> updateToDo(String string, Long id, int index){
        User user = findById(id);
        ArrayList<String> toDoList = user.getToDoList();
        toDoList.set(index,string);
        user.setToDoList(toDoList);
        userRepository.save(user);
        return toDoList;
    }

    public ArrayList<String> deleteToDo(Long id,int index){
        User user = findById(id);
        ArrayList<String> toDoList = user.getToDoList();
        toDoList.remove(index);
        user.setToDoList(toDoList);
        userRepository.save(user);
        return toDoList;
    }
    public ArrayList<String> deleteAllToDo(Long id){
        User user = findById(id);
        ArrayList<String> toDoList = user.getToDoList();
        toDoList.clear();
        user.setToDoList(toDoList);
        userRepository.save(user);
        return toDoList;
    }
}
