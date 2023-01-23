package com.tweteroo.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tweteroo.api.dto.UserDTO;
import com.tweteroo.api.model.User;
import com.tweteroo.api.repository.UserRepository;

@Service
public class UserService {
    
    @Autowired
    private UserRepository repository;

    public User save(UserDTO userDTO) {
        var user = repository.findByUsername(userDTO.username());

        if(user.isPresent()) {
            return null;
        }

        return repository.save(new User(userDTO));
    }
}
