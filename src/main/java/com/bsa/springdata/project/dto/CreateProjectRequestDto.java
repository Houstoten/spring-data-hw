package com.bsa.springdata.project.dto;

import com.bsa.springdata.project.Project;
import com.bsa.springdata.team.Team;
import com.bsa.springdata.team.Technology;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateProjectRequestDto {
    private final String projectName;
    private final String projectDescription;
    private final String tech;
    private final String techDescription;
    private final String techLink;
    private final String teamArea;
    private final String teamRoom;
    private final String teamName;

    public static Team mapToTeam(CreateProjectRequestDto req){
        return Team.builder()
                .name(req.teamName)
                .area(req.teamArea)
                .room(req.teamRoom)
                .project(Project.builder()
                        .name(req.projectName)
                        .description(req.projectDescription)
                        .build())
                .technology(Technology.builder()
                        .name(req.tech)
                        .description(req.techDescription)
                        .link(req.techLink)
                        .build())
                .build();
    }
}
