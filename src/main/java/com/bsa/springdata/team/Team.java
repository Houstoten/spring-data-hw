package com.bsa.springdata.team;

import com.bsa.springdata.project.Project;
import com.bsa.springdata.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

// TODO: Map table teams to this entity
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "teams")
public class Team {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID"
            , strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column
    private String name;

    @Column
    private String room;

    @Column
    private String area;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH}
            , fetch = FetchType.LAZY)
    @JoinColumn(name = "projectId", nullable = false)
    private Project project;

    @OneToMany(mappedBy = "team", fetch = FetchType.LAZY)
    private List<User> users;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH}
            , fetch = FetchType.LAZY)
    @JoinColumn(name = "technologyId", nullable = false)
    private Technology technology;
}
