package com.bsa.springdata.project;

import com.bsa.springdata.project.dto.ProjectSummaryDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface ProjectRepository extends JpaRepository<Project, UUID> {

    @Query("select p from Project p where p in (select t.project from Team t where t.technology.name like ?1)")
    List<Project> findTop5ByTechnology(String technology, PageRequest of);

    @Query("select p from Project p left join p.teams t group by p " +
            "order by p.teams.size desc, " +
            "sum(t.users.size) desc," +
            "p.name desc")
    Page<Project> findTheBiggest(Pageable pageable);

    @Query(value = "select distinct p.name, " +
            "count(distinct t.id) as teamsNumber, " +
            "count(distinct u.id) as developersNumber, " +
            "string_agg(distinct tech.name, ',' order by tech.name desc) as technologies " +
            "from teams t " +
            "join users u on t.id=u.team_id " +
            "join technologies tech on tech.id = t.technology_id " +
            "join projects p on p.id = t.project_id " +
            "group by p.name order by p.name", nativeQuery = true)
    List<ProjectSummaryDto> getSummary();

    @Query("select count(distinct p) from Team t " +
            "join t.project p " +
            "join t.users u " +
            "join u.roles r where r.name= :role")
    int getCountWithRole(String role);
}