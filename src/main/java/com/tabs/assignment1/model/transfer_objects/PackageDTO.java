package com.tabs.assignment1.model.transfer_objects;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class PackageDTO {

    private String name;
    private Integer price;
    private Date startDate;
    private Date endDate;
    private String details;
    private Integer noOfSpots;
    private String destinationId;
    private String agencyId;

    public PackageDTO(String name, Integer price, Date startDate, Date endDate, String details,
                      Integer noOfSpots, String destinationId, String agencyId) {
        this.name = name;
        this.price = price;
        this.startDate = startDate;
        this.endDate = endDate;
        this.details = details;
        this.noOfSpots = noOfSpots;
        this.destinationId = destinationId;
        this.agencyId = agencyId;
    }

    public PackageDTO() {

    }
}
