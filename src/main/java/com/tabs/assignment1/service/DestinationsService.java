package com.tabs.assignment1.service;

import com.tabs.assignment1.model.abstraction_objects.DestinationDAO;
import com.tabs.assignment1.repository.DestinationRepository;

import java.util.List;

public class DestinationsService {

    private final DestinationRepository destinationRepository = new DestinationRepository();

    public DestinationDAO createDestiantion(String destinationName) throws Exception {
        if (destinationName.isEmpty())
            throw new Exception("Empty username not permitted!");

        return destinationRepository.insertDestination(destinationName);
    }

    public List<DestinationDAO> readAllDestiantions() {
        return destinationRepository.selectAllDestinations();
    }

    public DestinationDAO removeDestination(String destinationId) {
        return destinationRepository.removeDestination(destinationId);
    }
}
