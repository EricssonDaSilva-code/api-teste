package com.teste.apiteste.controllers;

import com.teste.apiteste.dto.CrmDto;
import com.teste.apiteste.dto.UserDto;
import com.teste.apiteste.models.CRM;
import com.teste.apiteste.models.UserModel;
import com.teste.apiteste.services.CRMService;
import com.teste.apiteste.services.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {
    @Autowired
    final UserService userService;

    final CRMService crmService;

    public UserController(UserService userService, CRMService crmService) {
        this.userService = userService;
        this.crmService = crmService;
    }

    @PostMapping
    public ResponseEntity<Object> saveUser(@RequestBody @Valid UserDto userDto) {
        if (userService.existsByEmail(userDto.getEmail())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflic: This E-mail already exists.");
        }
        if (userService.existsByMobilePhone(userDto.getMobilePhone())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("This number phone is already existis");
        }
        UserModel userModel = new UserModel();
        BeanUtils.copyProperties(userDto, userModel);
        userModel.setRegistrationDate(LocalDateTime.now(ZoneId.of("UTC")));
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(userModel));
    }

    @PostMapping("/crm")
    public ResponseEntity<Object> saveCrm(@RequestBody @Valid CrmDto crmDto) {
        if (crmService.existsByCrm(crmDto.getCrm())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: This CRM already exists.");
        }
        CRM crm = new CRM();
        BeanUtils.copyProperties(crmDto, crm);
        crm.setRegistrationDate(LocalDateTime.now(ZoneId.of("UTC")));
        return ResponseEntity.status(HttpStatus.CREATED).body(crmService.saveCrm(crm));
    }

    @GetMapping
    public ResponseEntity<List<UserModel>> getAllUsers() {
        return ResponseEntity.status(HttpStatus.OK).body(userService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneUser(@PathVariable(value = "id") long id) {
        Optional<UserModel> userModelOptional = userService.findById(id) ;
        return userModelOptional.<ResponseEntity<Object>>map(userModel -> ResponseEntity.status(HttpStatus.OK).body(userModel)).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found."));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Object> getUserByEmail(@PathVariable(value = "name") String name) {
        Optional<UserModel> userModelOptional = userService.findByName(name);
        return userModelOptional.<ResponseEntity<Object>>map(userModel -> ResponseEntity.status(HttpStatus.OK).body(userModel)).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found."));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateUser(@PathVariable(value = "id") long id,
                                             @RequestBody @Valid UserDto userDto) {
        Optional<UserModel> userModelOptional = userService.findById(id);
        if (userModelOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
        UserModel userModel = new UserModel();
        BeanUtils.copyProperties(userDto, userModel);
        userModel.setId(userModelOptional.get().getId());
        userModel.setRegistrationDate(userModelOptional.get().getRegistrationDate());
        return ResponseEntity.status(HttpStatus.OK).body(userService.save(userModel));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable(value = "id") long id) {

        Optional<UserModel> userModelOptional = userService.findById(id);
        if (userModelOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
        userService.delete(userModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("User deleted successfuly.");
    }


}
