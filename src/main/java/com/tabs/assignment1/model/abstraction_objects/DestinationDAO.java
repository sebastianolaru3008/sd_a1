package com.tabs.assignment1.model.abstraction_objects;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "destination", schema = "assignment1", catalog = "")
@Getter
@Setter
@EqualsAndHashCode
@ToString(exclude = {
//        "id",
        "packagesById"
})
public class DestinationDAO {
    @Id
    @Column(name = "id", nullable = false, length = 36)
    private String id;
    @Basic
    @Column(name = "name", nullable = false, length = 256, unique = true)
    private String name;
    @OneToMany(mappedBy = "destinationByDestinationId", cascade = CascadeType.ALL)
    private Collection<PackageDAO> packagesById;
}
