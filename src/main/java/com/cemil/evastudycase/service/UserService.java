package com.cemil.evastudycase.service;

import com.cemil.evastudycase.model.User;
import com.cemil.evastudycase.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository repository;

    @Autowired
    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public User saveUser(User user) {
        return repository.save(user);
    }

    public List<User> saveUsers(List<User> userList) {
        return repository.saveAll(userList);
    }

    public User getUserById(long id) {
        return repository.findById(id).orElse(null);
    }

    public List<User> getUsers() {
        return repository.findAll();
    }

    public void deleteUser(User user) {
        repository.delete(user);
    }

    public String deleteUserById(long id) {
        repository.deleteById(id);
        return "User with id:" + id + " has been deleted.";
    }

}
