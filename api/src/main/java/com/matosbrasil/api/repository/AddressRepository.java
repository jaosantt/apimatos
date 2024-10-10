package com.matosbrasil.api.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.matosbrasil.api.model.Address;

public interface AddressRepository extends JpaRepository<Address, UUID>{

}
