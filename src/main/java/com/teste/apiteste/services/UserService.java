package com.teste.apiteste.services;

import com.teste.apiteste.models.UserModel;
import com.teste.apiteste.repositories.UserRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public UserModel save(UserModel userModel) {
        return userRepository.save(userModel);
    }

    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    public boolean existsByMobilePhone(String mobilePhone) {
        return userRepository.existsByMobilePhone(mobilePhone);
    }

    public List<UserModel> findAll() {
        return userRepository.findAll();
    }

    public Optional<UserModel> findById(long id) {
        return userRepository.findById(id);
    }

    @Transactional
    public void delete(UserModel userModel) {
        userRepository.delete(userModel);
    }


    public Optional<UserModel> findByName(String name) {
        return userRepository.findByName(name);
    }
}
