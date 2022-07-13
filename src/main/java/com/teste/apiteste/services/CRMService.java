package com.teste.apiteste.services;

import com.teste.apiteste.models.CRM;
import com.teste.apiteste.repositories.CRMRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
}
