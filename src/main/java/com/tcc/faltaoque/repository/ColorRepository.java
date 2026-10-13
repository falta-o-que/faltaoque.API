package com.tcc.faltaoque.repository;

import com.tcc.faltaoque.entity.Color;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface ColorRepository extends JpaRepository<Color, Integer> {

}
