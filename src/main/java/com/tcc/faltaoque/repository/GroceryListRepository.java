package com.tcc.faltaoque.repository;

import com.tcc.faltaoque.entity.GroceryList;
import java.util.List;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface GroceryListRepository extends JpaRepository<GroceryList, String> {

	boolean existsByIdAndPantryId(String id, String pantryId);

	List<GroceryList> findByPantryId(String pantryId);
}
