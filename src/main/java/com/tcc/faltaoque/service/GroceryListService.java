package com.tcc.faltaoque.service;

import com.tcc.faltaoque.entity.GroceryList;
import com.tcc.faltaoque.entity.User;
import com.tcc.faltaoque.entity.Location;
import com.tcc.faltaoque.entity.Pantry;
import com.tcc.faltaoque.repository.GroceryListRepository;
import com.tcc.faltaoque.repository.UserRepository;
import com.tcc.faltaoque.repository.LocationRepository;
import com.tcc.faltaoque.repository.PantryRepository;
import com.tcc.faltaoque.mapper.GroceryListMapper;
import com.tcc.faltaoque.dto.request.GroceryListRequest;
import com.tcc.faltaoque.dto.response.GroceryListResponse;
import com.tcc.faltaoque.exception.GroceryListNotFoundException;
import com.tcc.faltaoque.exception.LocationNotFoundException;
import com.tcc.faltaoque.exception.PantryNotFoundException;
import com.tcc.faltaoque.exception.InvalidCredentialsException;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GroceryListService {

	private final GroceryListRepository groceryListRepository;
	private final UserRepository userRepository;
	private final LocationRepository locationRepository;
	private final PantryRepository pantryRepository;

	public List<GroceryListResponse> listAllGroceryLists() {
		return groceryListRepository.findAll()
			.stream()
			.map(groceryList -> GroceryListMapper.toResponse(
				groceryList,
				groceryList.getLocation(),
				groceryList.getPantry()))
			.collect(Collectors.toList());
	}

	public GroceryListResponse findGroceryListById(String id) {
		GroceryList groceryList = groceryListRepository.findById(id)
			.orElseThrow(() -> new GroceryListNotFoundException(id));
		return GroceryListMapper.toResponse(
			groceryList,
			groceryList.getLocation(),
			groceryList.getPantry());
	}

	public GroceryListResponse createGroceryList(String userEmail,
	GroceryListRequest groceryListRequest) {
		User user = userRepository.findByEmail(userEmail)
			.orElseThrow(() -> new InvalidCredentialsException());

		Location location = locationRepository.findById(groceryListRequest.location())
			.orElseThrow(() -> new LocationNotFoundException(groceryListRequest.location()));

		Pantry pantry = pantryRepository.findById(groceryListRequest.pantryId())
			.orElseThrow(() -> new PantryNotFoundException(groceryListRequest.pantryId()));

		if(!pantryRepository.existsByIdAndUsers_Email(pantry.getId(), user.getEmail())) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN,
				"Você não participa desta despensa!");
		}

		GroceryList groceryList = GroceryListMapper.toEntity(groceryListRequest, location, pantry);
		groceryListRepository.save(groceryList);

		return GroceryListMapper.toResponse(
			groceryList,
			groceryList.getLocation(),
			groceryList.getPantry());
	}

	public GroceryListResponse updateGroceryList(String userEmail, String id,
	GroceryListRequest groceryListRequest) {
		User user = userRepository.findByEmail(userEmail)
			.orElseThrow(() -> new InvalidCredentialsException());

		GroceryList groceryList = groceryListRepository.findById(id)
			.orElseThrow(() -> new GroceryListNotFoundException(id));

		Location location = locationRepository.findById(groceryListRequest.location())
			.orElseThrow(() -> new LocationNotFoundException(groceryListRequest.location()));

		Pantry pantry = pantryRepository.findById(groceryListRequest.pantryId())
			.orElseThrow(() -> new PantryNotFoundException(groceryListRequest.pantryId()));

		if(!pantryRepository.existsByIdAndUsers_Email(pantry.getId(), user.getEmail())) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN,
				"Você não participa desta despensa!");
		}

		if(!groceryListRepository.existsByIdAndPantryId(id, pantry.getId())) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN,
				"Você não participa desta lista!");
		}

		groceryList.setName(groceryListRequest.name());
		groceryList.setDate(groceryListRequest.date());
		groceryList.setSuggestion(groceryListRequest.suggestion());
		groceryList.setLocation(location);
		groceryList.setPantry(pantry);
		groceryListRepository.save(groceryList);

		return GroceryListMapper.toResponse(
			groceryList,
			groceryList.getLocation(),
			groceryList.getPantry());
	}

	public void deleteGroceryList(String userEmail, String id) {
		User user = userRepository.findByEmail(userEmail)
			.orElseThrow(() -> new InvalidCredentialsException());

		GroceryList groceryList = groceryListRepository.findById(id)
			.orElseThrow(() -> new GroceryListNotFoundException(id));

		if(!pantryRepository.existsByIdAndUsers_Email(
		groceryList.getPantry().getId(), user.getEmail())) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN,
				"Você não participa desta lista!");
		}

		groceryListRepository.deleteById(id);
	}
}
