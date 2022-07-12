package com.teste.apiteste.repositories;

import com.teste.apiteste.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {


    boolean existsByEmail(String email);

    boolean existsByMobilePhone(String mobilePhone);


    Optional<UserModel> findByName(String name);

}
