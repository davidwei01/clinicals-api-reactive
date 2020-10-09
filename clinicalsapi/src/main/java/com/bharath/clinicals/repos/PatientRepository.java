package com.bharath.clinicals.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bharath.clinicals.model.Patient;

public interface PatientRepository extends JpaRepository<Patient, Integer> {

}
