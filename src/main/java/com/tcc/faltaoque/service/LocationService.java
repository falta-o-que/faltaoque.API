package com.tcc.faltaoque.service;

import com.tcc.faltaoque.entity.Location;
import com.tcc.faltaoque.repository.LocationRepository;
import com.tcc.faltaoque.mapper.LocationMapper;
import com.tcc.faltaoque.dto.request.LocationRequest;
import com.tcc.faltaoque.dto.response.LocationResponse;
import com.tcc.faltaoque.exception.LocationNotFoundException;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LocationService {

	private final LocationRepository locationRepository;

	public List<LocationResponse> listAllLocations() {
		return locationRepository.findAll()
			.stream()
			.map(LocationMapper::toResponse)
			.collect(Collectors.toList());
	}

	public LocationResponse findLocationById(String id) {
		Location location = locationRepository.findById(id)
			.orElseThrow(() -> new LocationNotFoundException(id));
		return LocationMapper.toResponse(location);
	}

	public LocationResponse createLocation(LocationRequest locationRequest) {
		Location location = LocationMapper.toEntity(locationRequest);
		locationRepository.save(location);
		return LocationMapper.toResponse(location);
	}

	public LocationResponse updateLocation(String id, LocationRequest locationRequest) {
		Location location = locationRepository.findById(id)
			.orElseThrow(() -> new LocationNotFoundException(id));
			location.setLocalName(locationRequest.localName());
			location.setCustomName(locationRequest.customName());
			location.setCep(locationRequest.cep());
			location.setLatitude(locationRequest.latitude());
			location.setLongitude(locationRequest.longitude());

		locationRepository.save(location);
		return LocationMapper.toResponse(location);
	}

	public void deleteLocation(String id) {
		Location location = locationRepository.findById(id)
			.orElseThrow(() -> new LocationNotFoundException(id));
		locationRepository.deleteById(id);
	}

}
