package com.tcc.faltaoque.repository;

import com.tcc.faltaoque.entity.Location;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface LocationRepository extends JpaRepository<Location, String> {

}
