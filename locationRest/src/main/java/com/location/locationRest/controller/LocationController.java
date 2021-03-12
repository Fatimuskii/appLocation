package com.location.locationRest.controller;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.location.locationRest.model.entity.Location;
import com.location.locationRest.model.repo.ILocationRepository;

@RestController
public class LocationController {

	@Autowired
	ILocationRepository locationRepo;
	
	@RequestMapping(value = "/create", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Object> create(@RequestParam (value="uniqueId") String uniqueId, @RequestParam(value="latitude") String latitude, @RequestParam(value="longitude") String longitude) {
		// Check if student exists
		Optional<Location> res = locationRepo.findByUniqueId(uniqueId);

		System.out.println("Se ha recibido la petición de Android");
		if (res != null && !res.isPresent()) { // if not exist we create it
			Location newLocation= new Location();
			newLocation.setUniqueId(uniqueId);
			newLocation.setLatitude(latitude);
			newLocation.setLongitude(longitude);
			Date time = new Date();
			newLocation.setDatedCreated(time);
			Location id = locationRepo.save(newLocation);
			System.out.println("Location was created successfully");
			return new ResponseEntity<>(id, HttpStatus.CREATED);
		} else {
			String message = "Location with uniqueid " + uniqueId + " exists already.";
			return new ResponseEntity<Object>(message, HttpStatus.EXPECTATION_FAILED);
		}

	}
}
