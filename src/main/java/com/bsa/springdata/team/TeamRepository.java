package com.bsa.springdata.team;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

public interface TeamRepository extends JpaRepository<Team, UUID> {
    int countByTechnologyName(String newTechnology);

    Optional<Boolean> findByName(String hipsters_facebook_javaScript);

    @Transactional
    @Modifying
    @Query("update Team t set t.technology = " +
            "(select tech from Technology tech where tech.name like :newTechnologyName) " +
            "where t.users.size < :devsNumber " +
            "and t in (select t1 from Team t1 where t1.technology.name like :oldTechnologyName)")
    void updateTechnology(int devsNumber, String oldTechnologyName, String newTechnologyName);

    @Transactional
    @Modifying
    @Query(value = "update teams set name = concat_ws('_', name, " +
            "(select name from projects where id = teams.project_id), " +
            "(select name from technologies where id = teams.technology_id)) " +
            "where name like :hipsters", nativeQuery = true)
    void normalizeName(String hipsters);
}
