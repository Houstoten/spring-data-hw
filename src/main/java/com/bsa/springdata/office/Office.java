package com.bsa.springdata.office;

import com.bsa.springdata.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

// TODO: Map table offices to this entity
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "offices")
public class Office {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID"
            , strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", nullable = false, updatable = false)
    private UUID id;

    @Column
    private String city;

    @Column
    private String address;

    @OneToMany(mappedBy = "office"
            , cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH}
            , fetch = FetchType.LAZY)
    private List<User> users;
}
