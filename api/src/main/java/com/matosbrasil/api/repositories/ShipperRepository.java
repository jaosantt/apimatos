package com.matosbrasil.api.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.matosbrasil.api.domain.company.Shipper;

public interface ShipperRepository extends JpaRepository<Shipper, UUID>{

}
