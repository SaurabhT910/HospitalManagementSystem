package com.hms.doclogin.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.management.AttributeNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hms.doclogin.entity.Appointment;
import com.hms.doclogin.repository.AppoinmentRepository;
import com.hms.service.AppointmentService;

@CrossOrigin(origins ="http://localhost:4200")
@RestController
@RequestMapping("/api/v2/appointment")
public class AppointmentController implements AppointmentService {

	@Autowired
	private AppoinmentRepository appoinmentRepository;
	@Override
	@PostMapping("/insert")
	public Appointment createAppoinment(@RequestBody Appointment appointment) {

		return appoinmentRepository.save(appointment);
	}
	@Override
	@GetMapping("/all")
	public List<Appointment> getAllAppointment() {
		
		return appoinmentRepository.findAll();
	}
	@Override
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteById(@PathVariable long id) throws AttributeNotFoundException {
		Appointment appointment=appoinmentRepository.findById(id).orElseThrow(
				()-> new AttributeNotFoundException("Appointment not found"+id));
		appoinmentRepository.delete(appointment);
		Map<String,Boolean> response=new HashMap<String, Boolean>();
		response.put("Deleted",Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
}
