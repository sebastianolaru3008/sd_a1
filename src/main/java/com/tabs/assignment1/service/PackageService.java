package com.tabs.assignment1.service;

import com.tabs.assignment1.model.abstraction_objects.PackageDAO;
import com.tabs.assignment1.model.transfer_objects.FilterDTO;
import com.tabs.assignment1.model.transfer_objects.PackageDTO;
import com.tabs.assignment1.repository.PackageRepository;

import java.sql.Date;
import java.util.List;

public class PackageService {

    private final PackageRepository packageRepository = new PackageRepository();

    private void packageDataValidation(PackageDTO packageData) throws Exception {
        if (packageData.getPrice() < 0)
            throw new Exception("Negative price not accepted.");

        String name = packageData.getName();
        if (name.isEmpty())
            throw new Exception("Empty package name not accepted.");

        Date startDate = packageData.getStartDate();
        Date endDate = packageData.getEndDate();
        if (startDate.after(endDate) || startDate.equals(endDate))
            throw new Exception("Start date should be always before end date.");

        if (packageData.getNoOfSpots() <= 0)
            throw new Exception("Negative number of spots not accepted.");

        if (packageData.getDestinationId() == null)
            throw new Exception("Null destination! Please provide one.");
    }

    private void packageFilterValidation(FilterDTO filterData) throws Exception {
        if ((filterData.getMinPrice() != null && filterData.getMinPrice() < 0) || (filterData.getMinPrice() != null && filterData.getMaxPrice() < 0))
            throw new Exception("Negative price not accepted.");

        if (filterData.getMinPrice() > filterData.getMaxPrice())
            throw new Exception("Inverse range not accepted.");


        Date startDate = filterData.getStartPeriod();
        Date endDate = filterData.getEndPeriod();
        if (startDate == null || endDate == null || startDate.after(endDate))
            throw new Exception("Start date should be always before end date.");

    }

    public PackageDAO createPackage(PackageDTO packageData) throws Exception {
        packageDataValidation(packageData);
        return packageRepository.insertPackage(packageData);
    }

    public PackageDAO updatePackage(PackageDTO packageData, String packageId) throws Exception {
        packageDataValidation(packageData);
        return packageRepository.updatePackage(packageData, packageId);
    }

    public PackageDAO deletePackage(String packageId) {
        return packageRepository.removePackage(packageId);
    }

    public List<PackageDAO> readAllPackages() {
        return packageRepository.selectAllPackages();
    }

    public List<PackageDAO> readAllFilteredPackages(FilterDTO filter) throws Exception {
//        packageFilterValidation(filter);
        return packageRepository.selectAllFilteredPackages(filter);
    }
}
