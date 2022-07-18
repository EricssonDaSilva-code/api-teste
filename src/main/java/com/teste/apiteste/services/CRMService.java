package com.teste.apiteste.services;

import com.teste.apiteste.models.CRM;
import com.teste.apiteste.repositories.CRMRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class CRMService {
    final CRMRepository crmRepository;

    public CRMService(CRMRepository crmRepository) {
        this.crmRepository = crmRepository;
    }


    public boolean existsByCrm(String crm) {
        return crmRepository.existsByCrm(crm);
    }
    @Transactional
    public Object saveCrm(CRM crm) {
        return crmRepository.save(crm);
    }

    public List<CRM> findAll() {
        return crmRepository.findAll();
    }

    public Optional<CRM> findById(long id) {
        return crmRepository.findById(id);
    }

    public Object save(CRM crm) {
        return crmRepository.save(crm);
    }
}
