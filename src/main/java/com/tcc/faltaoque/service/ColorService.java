package com.tcc.faltaoque.service;

import com.tcc.faltaoque.entity.Color;
import com.tcc.faltaoque.repository.ColorRepository;
import com.tcc.faltaoque.mapper.ColorMapper;
import com.tcc.faltaoque.dto.request.ColorRequest;
import com.tcc.faltaoque.dto.response.ColorResponse;
import com.tcc.faltaoque.exception.ColorNotFoundException;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ColorService {

	private final ColorRepository colorRepository;

	public List<ColorResponse> listAllColors() {
		return colorRepository.findAll()
			.stream()
			.map(ColorMapper::toResponse)
			.collect(Collectors.toList());
	}

	public ColorResponse findColorById(int id) {
		Color color = colorRepository.findById(id)
			.orElseThrow(() -> new ColorNotFoundException(id));
		return ColorMapper.toResponse(color);
	}

	public ColorResponse addColor(ColorRequest colorRequest) {
		Color color = ColorMapper.toEntity(colorRequest);
		colorRepository.save(color);
		return ColorMapper.toResponse(color);
	}

	public ColorResponse updateColor(int id, ColorRequest colorRequest) {
		Color color = colorRepository.findById(id)
			.orElseThrow(() -> new ColorNotFoundException(id));
			color.setName(colorRequest.name());
			color.setHexCode(colorRequest.hexCode());

		colorRepository.save(color);
		return ColorMapper.toResponse(color);
	}

	public void deleteColor(int id) {
		Color color = colorRepository.findById(id)
			.orElseThrow(() -> new ColorNotFoundException(id));
		colorRepository.deleteById(id);
	}
}
