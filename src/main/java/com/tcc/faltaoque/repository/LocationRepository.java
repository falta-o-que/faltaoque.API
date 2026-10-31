package com.tcc.faltaoque.repository;

import com.tcc.faltaoque.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends JpaRepository <Location, String> {
}
