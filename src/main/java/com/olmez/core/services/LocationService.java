package com.olmez.core.services;

import java.util.List;

import com.olmez.core.model.Location;

public interface LocationService {

    List<Location> getLocations();

    boolean addLocation(Location location);

    Location getLocationById(Long id);

    boolean deleteLocation(Long id);

    Location updateLocation(Long existingLocationId, Location givenLocation);

}
