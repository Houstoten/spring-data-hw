package com.bsa.springdata.role;

import com.bsa.springdata.user.User;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

// TODO: Map table roles to this entity
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(exclude = "users")
@ToString(exclude = "users")
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID"
            , strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column
    private String name;

    @Column
    private String code;

    @ManyToMany(mappedBy = "roles", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH})
    @Builder.Default
    private Set<User> users = new HashSet<>();
}
