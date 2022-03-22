package com.tabs.assignment1.controller;

import com.tabs.assignment1.model.abstraction_objects.DestinationDAO;
import com.tabs.assignment1.model.abstraction_objects.PackageDAO;
import com.tabs.assignment1.model.abstraction_objects.UserDAO;
import com.tabs.assignment1.model.transfer_objects.FilterDTO;
import com.tabs.assignment1.model.transfer_objects.UserDTO;
import com.tabs.assignment1.service.BookingService;
import com.tabs.assignment1.service.DestinationsService;
import com.tabs.assignment1.service.PackageService;
import com.tabs.assignment1.service.UserService;

import java.util.List;

public class UserController {

    final private UserService userService = new UserService();
    final private PackageService packageService = new PackageService();
    final private DestinationsService destinationService = new DestinationsService();
    final private BookingService bookingService = new BookingService();

    public static void main(String[] args) throws Exception {
        UserController userController = new UserController();

        UserDTO userData = new UserDTO("victor", "puma");

        UserDAO user = userController.registerUser(userData);
        System.out.println("Insert: " + user.toString());

//        UserDAO loggedUser = userController.loginUser(userData);
//        System.out.println("Login: " + loggedUser);

        List<PackageDAO> packages = userController.getAllPackages();
        System.out.println(packages);

//        FilterDTO filter = new FilterDTO();
//        filter.setAgency(loggedUser.getAgencyByAgencyId());
//        List<PackageDAO> filteredPackages = userController.getAllFilteredPackages(filter);
//        System.out.println(filteredPackages);
//
//        PackageDAO packageDAO;
//        packageDAO = userController.addBooking(loggedUser.getId(), "9979f405-19b6-4dd1-bb33-fefc9588760b");

    }

    public UserDAO registerUser(UserDTO userData) throws Exception {
        return userService.createUser(userData);
    }

    public UserDAO loginUser(UserDTO userData) throws Exception {
        return userService.loginUser(userData);
    }

    public List<PackageDAO> getAllPackages() {
        return packageService.readAllPackages();
    }

    public List<DestinationDAO> getAllDestinations() throws Exception {
        return destinationService.readAllDestiantions();
    }

    public List<PackageDAO> getAllFilteredPackages(FilterDTO filter) throws Exception {
        return packageService.readAllFilteredPackages(filter);
    }

    public PackageDAO addBooking(String userId, String packageId) {
        return bookingService.createBooking(userId, packageId);
    }

    public List<PackageDAO> getAllUserBookings(String userId) {
        return bookingService.readAllUserBookings(userId);
    }
}
