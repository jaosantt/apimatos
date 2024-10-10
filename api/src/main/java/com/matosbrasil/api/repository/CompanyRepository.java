package com.matosbrasil.api.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.matosbrasil.api.model.Company;

public interface CompanyRepository extends JpaRepository<Company, UUID> {
    boolean existsByDocument(String document);
    Company getByDocument(String document);
}
