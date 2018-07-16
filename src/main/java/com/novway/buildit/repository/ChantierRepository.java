package com.novway.buildit.repository;

import com.novway.buildit.entity.Chantier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 */
@Repository
public interface ChantierRepository extends JpaRepository<Chantier, Long> {
}
