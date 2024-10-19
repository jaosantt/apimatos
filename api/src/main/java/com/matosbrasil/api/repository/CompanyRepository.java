package com.matosbrasil.api.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.matosbrasil.api.enums.TypeCompany;
import com.matosbrasil.api.model.Company;

public interface CompanyRepository extends JpaRepository<Company, UUID> {
    boolean existsByDocumentAndType(String document, TypeCompany type);
    Company getByDocumentAndType(String document, TypeCompany type);
}
