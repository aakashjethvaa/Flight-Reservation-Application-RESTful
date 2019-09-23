package com.aakash.flightreservation.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aakash.flightreservation.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{

}
