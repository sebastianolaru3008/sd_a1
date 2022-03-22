package com.tabs.assignment1.model.abstraction_objects;

import com.tabs.assignment1.model.PackageStatus;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "package", schema = "assignment1")
@Getter
@Setter
@EqualsAndHashCode
@ToString(exclude = {
//        "id",

})
public class PackageDAO {
    @Id
    @Column(name = "id", nullable = false, length = 36)
    private String id;
    @Basic
    @Column(name = "name", nullable = false, length = 256)
    private String name;
    @Basic
    @Column(name = "price", nullable = false)
    private Integer price;
    @Basic
    @Column(name = "start_date", nullable = false)
    private Date startDate;
    @Basic
    @Column(name = "end_date", nullable = false)
    private Date endDate;
    @Basic
    @Column(name = "details", nullable = true, length = 2048)
    private String details;
    @Basic
    @Column(name = "no_of_spots", nullable = false)
    private Integer noOfSpots;
    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "destination_id", referencedColumnName = "id", nullable = false)
    private DestinationDAO destinationByDestinationId;
    @ManyToOne
    @JoinColumn(name = "agency_id", referencedColumnName = "id", nullable = false)
    private AgencyDAO agencyByAgencyId;
    @ManyToMany(mappedBy = "bookings")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<UserDAO> tourists;

    public PackageStatus getStatus() {
        if (this.tourists.isEmpty())
            return PackageStatus.NOT_BOOKED;

        if (this.tourists.size() == noOfSpots)
            return PackageStatus.BOOKED;

        return PackageStatus.IN_PROGRESS;
    }

    public String getPeriod() {
        return startDate.toString() + "/" + endDate.toString();
    }

    public void addTourist(UserDAO userDAO) {
        this.tourists.add(userDAO);
    }
}
