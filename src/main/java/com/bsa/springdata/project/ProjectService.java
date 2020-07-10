package com.bsa.springdata.project;

import com.bsa.springdata.project.dto.CreateProjectRequestDto;
import com.bsa.springdata.project.dto.ProjectDto;
import com.bsa.springdata.project.dto.ProjectSummaryDto;
import com.bsa.springdata.team.TeamRepository;
import com.bsa.springdata.team.TechnologyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProjectService {
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private TechnologyRepository technologyRepository;
    @Autowired
    private TeamRepository teamRepository;

    public List<ProjectDto> findTop5ByTechnology(String technology) {
        // TODO: Use single query to load data. Sort by number of developers in a project
        //  Hint: in order to limit the query you can either use native query with limit or Pageable interface
        return projectRepository
                .findTop5ByTechnology(technology, PageRequest.of(0, 5))
                .stream()
                .map(ProjectDto::fromEntity)
                .collect(Collectors.toList());
    }

    public Optional<ProjectDto> findTheBiggest() {
        return projectRepository
                .findTheBiggest(PageRequest.of(0, 1))
                .get().findFirst()
                .map(ProjectDto::fromEntity);
    }

    public List<ProjectSummaryDto> getSummary() {
        return projectRepository.getSummary();
    }

    public int getCountWithRole(String role) {
        return projectRepository.getCountWithRole(role);
    }

    public UUID createWithTeamAndTechnology(CreateProjectRequestDto createProjectRequest) {
        return teamRepository.save(CreateProjectRequestDto.mapToTeam(createProjectRequest)).getProject().getId();
    }
}
