package com.tcc.faltaoque.repository;

import com.tcc.faltaoque.entity.Pantry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface PantryRepository extends JpaRepository<Pantry, String> {

    boolean existsByIdAndUsers_Email(String id, String email);

    Optional<Pantry> findFirstByShareInvite_Id(String inviteId);

    //desvicula o convite da despensa antes de ser expirado/apagado (FK pantries.share_invite_id)
    @Modifying
    @Query("update Pantry p set p.shareInvite = null where p.shareInvite.id in "
            + "(select i.id from PantryInvite i where i.expiresAt < :now)")
    int detachExpiredInvites(@Param("now") LocalDateTime now);
}
