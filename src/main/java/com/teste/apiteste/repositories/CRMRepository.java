package com.teste.apiteste.repositories;

import com.teste.apiteste.models.CRM;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CRMRepository extends JpaRepository<CRM, Long> {


    boolean existsByCrm(String crm);
}
