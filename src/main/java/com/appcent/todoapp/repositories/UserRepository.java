package com.appcent.todoapp.repositories;

import com.appcent.todoapp.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User,Long> {

    List<User> findAll();
    User findByUsername(String username);

    Optional<User> findById(Long id);

}
