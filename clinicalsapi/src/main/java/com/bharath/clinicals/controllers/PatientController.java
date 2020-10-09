package com.bharath.clinicals.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bharath.clinicals.model.ClinicalData;
import com.bharath.clinicals.model.Patient;
import com.bharath.clinicals.repos.PatientRepository;
import com.bharath.clinicals.util.BMICalculator;

@RestController
@RequestMapping("/api")
@CrossOrigin
class PatientController {

	@Autowired
	private PatientRepository repository;

	Map<String, String> filters = new HashMap<>();

	@GetMapping("/patients")
	public List<Patient> getPatients() {
		return repository.findAll();

	}

	@GetMapping("/patients/{id}")
	public Patient getPatient(@PathVariable("id") int id) {
		return repository.findById(id).get();

	}

	@PostMapping("/patients")
	public Patient savePatient(@RequestBody Patient patient) {
		return repository.save(patient);
	}

	@GetMapping("/patients/analyze/{id}")
	public Patient analyze(@PathVariable("id") int id) {
		Patient patient = repository.findById(id).get();
		List<ClinicalData> clinicalData = patient.getClinicalData();
		List<ClinicalData> duplicateClinicalData = new ArrayList<>(clinicalData);
		for (ClinicalData eachEntry : duplicateClinicalData) {
			if (filters.containsKey(eachEntry.getComponentName())) {
				clinicalData.remove(eachEntry);
				continue;
			} else {
				filters.put(eachEntry.getComponentName(), null);
			}
			BMICalculator.calculateBMI(clinicalData, eachEntry);
		}
		filters.clear();
		return patient;
	}

}
