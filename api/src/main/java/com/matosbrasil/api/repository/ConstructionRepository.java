package com.matosbrasil.api.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.matosbrasil.api.model.Construction;

public interface ConstructionRepository extends JpaRepository<Construction, UUID>{
    boolean existsByDocument(String document);
    Construction getByDocument(String document);
}