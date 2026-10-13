package com.tcc.faltaoque.repository;

import com.tcc.faltaoque.entity.PantryInvite;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface PantryInviteRepository extends JpaRepository<PantryInvite, String> {

}
