package com.tcc.faltaoque.repository;

import com.tcc.faltaoque.entity.Pantry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PantryRepository extends JpaRepository<Pantry, String> {
}
