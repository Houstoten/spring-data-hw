package com.bsa.springdata.user;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    List<User> findByLastNameStartsWithIgnoreCaseOrderByLastNameAsc(String lastName, Pageable pageable);

   // @Query("select u from User u where u.office in (select o from Office o where o.city= :city) order by u.lastName asc")
    List<User> findByOfficeCityOrderByLastNameAsc(String city);

    List<User> findByExperienceGreaterThanEqualOrderByExperienceDesc(int experience);

//    @Query("select u from User u where u.office in (select o from Office o where o.city= :city)" +
//            "and u.team in(select t from Team t where t.room= :room)")
    List<User> findByOfficeCityAndTeamRoom(String city, String room, Sort ascending);

    int deleteByExperienceLessThan(int experience);
}
