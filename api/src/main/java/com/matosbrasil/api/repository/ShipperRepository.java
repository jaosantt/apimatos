package com.matosbrasil.api.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.matosbrasil.api.model.Shipper;

public interface ShipperRepository extends JpaRepository<Shipper, UUID>{

}
