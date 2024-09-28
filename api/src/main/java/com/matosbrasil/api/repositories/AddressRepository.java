package com.matosbrasil.api.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.matosbrasil.api.domain.company.Address;

public interface AddressRepository extends JpaRepository<Address, UUID>{

}
