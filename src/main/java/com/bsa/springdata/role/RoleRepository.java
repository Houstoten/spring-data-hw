package com.bsa.springdata.role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.UUID;

public interface RoleRepository extends JpaRepository<Role, UUID> {

    @Transactional
    @Modifying
    @Query(value = "delete from roles r where r.id not in (select u2r.role_id from user2role u2r)", nativeQuery = true)
//    @Query("delete from Role r where r not in (select u.roles from User u)")// Не хочет работать и я не знаю почему }:^(
    void deleteRole(String roleCode);
}
