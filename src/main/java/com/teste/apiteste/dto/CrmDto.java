package com.teste.apiteste.dto;

import com.teste.apiteste.models.CRM;

import javax.validation.constraints.NotBlank;

public class CrmDto extends CRM {
    @NotBlank
    private String crm;
    @NotBlank
    private String uf;
    @NotBlank
    private String specialty;

    public String getCrm() {
        return crm;
    }


    public void setCrm(String crm) {
        this.crm = crm;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }
}
