package com.tabs.assignment1.model.transfer_objects;

import com.tabs.assignment1.model.abstraction_objects.AgencyDAO;
import com.tabs.assignment1.model.abstraction_objects.DestinationDAO;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class FilterDTO {

    private AgencyDAO agency;
    private DestinationDAO destination;
    private Integer minPrice;
    private Integer maxPrice;
    private Date startPeriod;
    private Date endPeriod;

    
}
