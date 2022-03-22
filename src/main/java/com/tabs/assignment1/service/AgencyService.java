package com.tabs.assignment1.service;

import com.tabs.assignment1.model.abstraction_objects.AgencyDAO;
import com.tabs.assignment1.model.transfer_objects.AgencyDTO;
import com.tabs.assignment1.repository.AgencyRepository;

public class AgencyService {

    private final AgencyRepository agencyRepository = new AgencyRepository();

    public AgencyDAO createAgency(AgencyDTO agencyData) {
        return agencyRepository.insertAgency(agencyData);
    }

    public AgencyDAO readAgencyById(String agencyId) {
        return agencyRepository.selectAgency(agencyId);
    }

}
