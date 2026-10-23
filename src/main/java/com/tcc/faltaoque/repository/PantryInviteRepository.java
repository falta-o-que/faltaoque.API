package com.tcc.faltaoque.repository;

import com.tcc.faltaoque.entity.PantryInvite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.Optional;

public interface PantryInviteRepository extends JpaRepository<PantryInvite, String> {

    Optional<PantryInvite> findByShareInvite(String shareInvite);

    @Modifying
    @Query("delete from PantryInvite invite where invite.expiresAt < :now")
    int deleteExpired(@Param("now") LocalDateTime now);

}
