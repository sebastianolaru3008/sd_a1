package com.tabs.assignment1.service;

import com.tabs.assignment1.model.abstraction_objects.PackageDAO;
import com.tabs.assignment1.repository.BookingRepository;

import java.util.List;

public class BookingService {

    private final BookingRepository bookingRepository = new BookingRepository();

    public PackageDAO createBooking(String userId, String packageId) {
        return bookingRepository.insertBooking(userId, packageId);
    }

    public List<PackageDAO> readAllUserBookings(String userId) {
        return bookingRepository.selectAllUserBookings(userId);
    }
}
