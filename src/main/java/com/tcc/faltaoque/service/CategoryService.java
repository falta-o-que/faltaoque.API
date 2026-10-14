package com.tcc.faltaoque.service;

import com.tcc.faltaoque.entity.Category;
import com.tcc.faltaoque.repository.CategoryRepository;
import com.tcc.faltaoque.mapper.CategoryMapper;
import com.tcc.faltaoque.dto.request.CategoryRequest;
import com.tcc.faltaoque.dto.response.CategoryResponse;
import com.tcc.faltaoque.exception.CategoryNotFoundException;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryService {

	private final CategoryRepository categoryRepository;

	public List<CategoryResponse> listAllCategories() {
		return categoryRepository.findAll()
			.stream()
			.map(CategoryMapper::toEntity)
			.collect(Collectors.toList());
	}

	public CategoryResponse findCategoryById(String id) {
		return CategoryMapper.toEntity(categoryRepository.findById(id)
			.orElseThrow(() -> new CategoryNotFoundException(id)));
	}

	public CategoryResponse createCategory(CategoryRequest categoryRequest) {
		Category category = CategoryMapper.toRequest(categoryRequest);

		categoryRepository.save(category);
		return CategoryMapper.toEntity(category);
	}

	public CategoryResponse updateCategory(String id, CategoryRequest categoryRequest) {
		Category category = categoryRepository.findById(id)
			.orElseThrow(() -> new CategoryNotFoundException(id));

		category.setName(categoryRequest.name());

		categoryRepository.save(category);
		return CategoryMapper.toEntity(category);
	}

	public void deleteCategory(String id) {
		Category category = categoryRepository.findById(id)
			.orElseThrow(() -> new CategoryNotFoundException(id));

		categoryRepository.deleteById(id);
	}
}
