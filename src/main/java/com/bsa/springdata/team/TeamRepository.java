package com.bsa.springdata.team;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface TeamRepository extends JpaRepository<Team, UUID> {
//    void normalizeName(String name);

    int countByTechnologyName(String newTechnology);

    Optional<Boolean> findByName(String hipsters_facebook_javaScript);
}
