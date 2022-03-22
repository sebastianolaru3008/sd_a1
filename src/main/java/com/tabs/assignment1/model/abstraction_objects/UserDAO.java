package com.tabs.assignment1.model.abstraction_objects;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "user")
@Getter
@Setter
@EqualsAndHashCode
@ToString(exclude = {
//        "id",
        "agencyByAgencyId",
        "bookings"
})
public class UserDAO {
    @Id
    @Column(name = "id", nullable = false, length = 36)
    private String id;
    @Basic
    @Column(name = "password_hash", nullable = false, length = 64)
    private String passwordHash;
    @Basic
    @Column(name = "username", nullable = false, length = 256, unique = true)
    private String username;
    @OneToOne
    @JoinColumn(name = "agency_id", referencedColumnName = "id")
    private AgencyDAO agencyByAgencyId;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "bookings",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "package_id")})
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<PackageDAO> bookings;

    public void addBooking(PackageDAO bookingPackage) {
        this.bookings.add(bookingPackage);
    }
}
