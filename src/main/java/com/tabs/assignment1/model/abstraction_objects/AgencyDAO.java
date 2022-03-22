package com.tabs.assignment1.model.abstraction_objects;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "agency", schema = "assignment1", catalog = "")
@Getter
@Setter
@EqualsAndHashCode
@ToString(exclude = {
//        "id",
        "userById",
        "packagesById"
})
public class AgencyDAO {
    @Id
    @Column(name = "id", nullable = false, length = 36)
    private String id;
    @Column(name = "name", nullable = false, length = 255, unique = true)
    private String name;
    @OneToOne(mappedBy = "agencyByAgencyId", cascade = CascadeType.REMOVE)
    private UserDAO userById;
    @OneToMany(mappedBy = "agencyByAgencyId", cascade = CascadeType.REMOVE)
    private Collection<PackageDAO> packagesById;
}
