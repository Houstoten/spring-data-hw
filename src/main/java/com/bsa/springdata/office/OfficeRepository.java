package com.bsa.springdata.office;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OfficeRepository extends JpaRepository<Office, UUID> {

    @Query("select distinct(u.office) from User u where u.team in" +
            " (select t from Team t where t.technology in" +
            " (select tech from Technology tech where tech.name= :technology))")
    List<Office> getByTechnology(String technology);

    @Transactional
    @Modifying
    @Query("update Office o set o.address= ?2 where o.address= ?1 and o in (select u.office from User u)")
    int updateAddress(String oldAddress, String newAddress);

    Office getByAddress(String newAddress);
}
