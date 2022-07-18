package com.teste.apiteste.controllers;

import com.teste.apiteste.dto.CrmDto;
import com.teste.apiteste.models.CRM;
import com.teste.apiteste.services.CRMService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/crm")
@CrossOrigin("*")
public class CRMControler {
    final CRMService crmService;
    

    public CRMControler(CRMService crmService) {
        this.crmService = crmService;
    }

    @PostMapping
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
    public ResponseEntity<List<CRM>> getAllCrm() {
        return ResponseEntity.status(HttpStatus.OK).body(crmService.findAll());
    }
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateCrm(@PathVariable(value = "id") long id,
                                             @RequestBody @Valid CrmDto crmDto) {
        Optional<CRM> crmOptional = crmService.findById(id);
        if (crmOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("CRM not found");
        }
        CRM crm = new CRM();
        BeanUtils.copyProperties(crmDto, crm);
        crm.setId(crmOptional.get().getId());
        crm.setRegistrationDate(crmOptional.get().getRegistrationDate());
        return ResponseEntity.status(HttpStatus.OK).body(crmService.save(crm));
    }


}
