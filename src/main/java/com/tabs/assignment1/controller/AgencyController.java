package com.tabs.assignment1.controller;

import com.tabs.assignment1.model.abstraction_objects.AgencyDAO;
import com.tabs.assignment1.model.abstraction_objects.DestinationDAO;
import com.tabs.assignment1.model.abstraction_objects.PackageDAO;
import com.tabs.assignment1.model.transfer_objects.AgencyDTO;
import com.tabs.assignment1.model.transfer_objects.FilterDTO;
import com.tabs.assignment1.model.transfer_objects.PackageDTO;
import com.tabs.assignment1.service.AgencyService;
import com.tabs.assignment1.service.DestinationsService;
import com.tabs.assignment1.service.PackageService;
import com.tabs.assignment1.utils.ExceptionUtil;

import java.util.ArrayList;
import java.util.List;

public class AgencyController {

    private final DestinationsService destinationsService = new DestinationsService();
    private final PackageService packageService = new PackageService();
    private final AgencyService agencyService = new AgencyService();

    public static void main(String[] args) {
        AgencyController agencyController = new AgencyController();
        UserController userController = new UserController();


//        AgencyDTO agencyData = new AgencyDTO();
//        agencyData.setName("UTCN");
//        AgencyDAO agency = agencyController.addAgency(agencyData);
//
////        AgencyDAO agency = agencyController.getAgency("6b5d9098-a7fd-4484-b421-5feb8d79a09f");
//
//
//        DestinationDAO destination = agencyController.addDestination("Constanta");
//        PackageDTO packageData1 = new PackageDTO(
//                "Vama 3 nopti la hamac",
//                100,
//                Date.valueOf("2021-05-01"),
//                Date.valueOf("2021-05-04"),
//                "dormi de rupi",
//                10,
//                destination.getId(),
//                agency.getId());
//        PackageDAO currentPackage = agencyController.addPackage(packageData1);
//        System.out.println("Current package: " + currentPackage.toString());
//        PackageDTO packageData2 = new PackageDTO(
//                "Eforie Sud 3 nopti la hamac",
//                100,
//                Date.valueOf("2021-05-01"),
//                Date.valueOf("2021-05-04"),
//                "partyyy",
//                10,
//                destination.getId(),
//                agency.getId());
//        currentPackage = agencyController.addPackage(packageData2);
//        System.out.println("Current package: " + currentPackage.toString());
//        PackageDTO packageData3 = new PackageDTO(
//                "Neptun 3 nopti la hamac",
//                100,
//                Date.valueOf("2021-05-01"),
//                Date.valueOf("2021-05-04"),
//                "la relaxare",
//                10,
//                destination.getId(),
//                agency.getId());
//        currentPackage = agencyController.addPackage(packageData3);
//        System.out.println("Current package: " + currentPackage.toString());
//
//        List<PackageDAO> packagesList = agencyController.getAllAgencyPackages(agency);
//        System.out.println(packagesList);
//        packagesList = userController.getAllPackages();
//        System.out.println(packagesList);
////
////        packageData1.setDetails("prost somn");
//        PackageDAO updatedPackage = agencyController.editPackage(packageData1, currentPackage.getId());
//        System.out.println("Updated package: " + updatedPackage.toString());
        PackageDAO deletedPackage = agencyController.deletePackage("03b6925b-55dd-4bc8-97ee-299f1a7cd7e6");
        System.out.println("Deleted package: " + deletedPackage.toString());
//        DestinationDAO deletedDestination = agencyController.deleteDestination("2c05383a-11c2-4e07-a593-c0bdbbd16cb6");
//        System.out.println("Deleted destination: " + deletedDestination.toString());
    }

    public AgencyDAO addAgency(AgencyDTO agencyData) {
        try {
            return agencyService.createAgency(agencyData);
        } catch (Exception e) {
            ExceptionUtil.printExceptionStacktrace(e);
        }
        return null;
    }

    public AgencyDAO getAgency(String agencyId) {
        try {
            return agencyService.readAgencyById(agencyId);
        } catch (Exception e) {
            ExceptionUtil.printExceptionStacktrace(e);
        }
        return null;
    }

    public DestinationDAO addDestination(String destinationName) {
        try {
            return destinationsService.createDestiantion(destinationName);
        } catch (Exception e) {
            ExceptionUtil.printExceptionStacktrace(e);
        }
        return null;
    }

    public DestinationDAO deleteDestination(String destinationId) {
        try {
            return destinationsService.removeDestination(destinationId);
        } catch (Exception e) {
            ExceptionUtil.printExceptionStacktrace(e);
        }
        return null;
    }

    public PackageDAO addPackage(PackageDTO packageData) {

        try {
            return packageService.createPackage(packageData);
        } catch (Exception e) {
            ExceptionUtil.printExceptionStacktrace(e);
        }
        return null;
    }

    public PackageDAO editPackage(PackageDTO packageData, String packageId) {

        try {
            return packageService.updatePackage(packageData, packageId);
        } catch (Exception e) {
            ExceptionUtil.printExceptionStacktrace(e);
        }
        return null;
    }

    public PackageDAO deletePackage(String packageId) {

        try {
            return packageService.deletePackage(packageId);
        } catch (Exception e) {
            ExceptionUtil.printExceptionStacktrace(e);
        }
        return null;
    }

    public List<PackageDAO> getAllAgencyPackages(AgencyDAO agencyDAO) {
        try {
            FilterDTO filter = new FilterDTO();
            filter.setAgency(agencyDAO);
            return packageService.readAllFilteredPackages(filter);
        } catch (Exception e) {
            ExceptionUtil.printExceptionStacktrace(e);
        }
        return new ArrayList<>();
    }

    public List<DestinationDAO> getAllDestinations() throws Exception {
        return destinationsService.readAllDestiantions();
    }
}
