package com.tcc.faltaoque.repository;

import com.tcc.faltaoque.entity.Category;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, String> {

}
