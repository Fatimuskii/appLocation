package com.location.locationRest.model.repo;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.location.locationRest.model.entity.Location;

@Repository
public interface ILocationRepository extends CrudRepository<Location, Integer>{

	Optional<Location> findByUniqueId(String uniqueId);

}
