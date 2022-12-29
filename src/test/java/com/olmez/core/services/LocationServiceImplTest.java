package com.olmez.core.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.olmez.core.model.Location;
import com.olmez.core.repositories.LocationRepository;
import com.olmez.core.services.impl.LocationServiceImpl;

@ExtendWith(MockitoExtension.class)
class LocationServiceImplTest {

    @InjectMocks
    private LocationServiceImpl service;
    @Mock
    private LocationRepository locationRepository;

    private Location location;

    @Test
    void testGetLocations() {
        location = new Location("location", 43.43, -80.80);
        location.setId(2L);
        when(locationRepository.findAll()).thenReturn(List.of(location));

        var locations = service.getLocations();
        assertThat(locations).isNotEmpty();
        assertThat(locations.get(0)).isEqualTo(location);
    }

}
