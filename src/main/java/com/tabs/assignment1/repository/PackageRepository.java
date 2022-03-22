package com.tabs.assignment1.repository;

import com.tabs.assignment1.model.abstraction_objects.AgencyDAO;
import com.tabs.assignment1.model.abstraction_objects.DestinationDAO;
import com.tabs.assignment1.model.abstraction_objects.PackageDAO;
import com.tabs.assignment1.model.transfer_objects.FilterDTO;
import com.tabs.assignment1.model.transfer_objects.PackageDTO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.UUID;

public class PackageRepository {

    private static final EntityManagerFactory entityManagerFactory =
            Persistence.createEntityManagerFactory("ro.assignment1.sd");

    public PackageDAO insertPackage(PackageDTO packageData) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        PackageDAO newPackage = new PackageDAO();
        newPackage.setId(UUID.randomUUID().toString());
        newPackage.setName(packageData.getName());
        newPackage.setPrice(packageData.getPrice());
        newPackage.setStartDate(packageData.getStartDate());
        newPackage.setEndDate(packageData.getEndDate());
        newPackage.setDetails(packageData.getDetails());
        newPackage.setNoOfSpots(packageData.getNoOfSpots());

        DestinationDAO destination = entityManager.find(DestinationDAO.class, packageData.getDestinationId());
        newPackage.setDestinationByDestinationId(destination);
        AgencyDAO agency = entityManager.find(AgencyDAO.class,
                packageData.getAgencyId());
        newPackage.setAgencyByAgencyId(agency);

        System.out.println(newPackage);


        entityManager.getTransaction().begin();
        entityManager.persist(newPackage);
        entityManager.getTransaction().commit();
        entityManager.close();


        return newPackage;
    }

    public PackageDAO updatePackage(PackageDTO packageData, String packageId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        PackageDAO currentPackage = entityManager.find(PackageDAO.class, packageId);
        currentPackage.setName(packageData.getName());
        currentPackage.setPrice(packageData.getPrice());
        currentPackage.setStartDate(packageData.getStartDate());
        currentPackage.setEndDate(packageData.getEndDate());
        currentPackage.setDetails(packageData.getDetails());
        currentPackage.setNoOfSpots(packageData.getNoOfSpots());

        DestinationDAO destination = entityManager.find(DestinationDAO.class,
                packageData.getDestinationId());
        currentPackage.setDestinationByDestinationId(destination);

        AgencyDAO agency = entityManager.find(AgencyDAO.class, packageData.getAgencyId());
        currentPackage.setAgencyByAgencyId(agency);

        System.out.println(entityManager.find(PackageDAO.class, packageId));

        entityManager.getTransaction().commit();
        entityManager.close();

        return currentPackage;
    }

    public PackageDAO removePackage(String packageId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        PackageDAO currentPackage = entityManager.find(PackageDAO.class, packageId);
        entityManager.remove(currentPackage);

        entityManager.getTransaction().commit();
        entityManager.close();

        return currentPackage;
    }

    public List<PackageDAO> selectAllPackages() {

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<PackageDAO> criteriaQuery = criteriaBuilder.createQuery(PackageDAO.class);
        Root<PackageDAO> root = criteriaQuery.from(PackageDAO.class);
        criteriaQuery.select(root);

        TypedQuery<PackageDAO> allQuery = entityManager.createQuery(criteriaQuery);
        List<PackageDAO> packages = allQuery.getResultList();

        entityManager.getTransaction().commit();
        entityManager.close();

        return packages;
    }


    private CriteriaQuery<PackageDAO> computeFilterQuery(EntityManager entityManager,
                                                         CriteriaBuilder criteriaBuilder,
                                                         FilterDTO filter) {

        CriteriaQuery<PackageDAO> criteriaQuery = criteriaBuilder.createQuery(PackageDAO.class);
        Root<PackageDAO> root = criteriaQuery.from(PackageDAO.class);
        Predicate criteriaPredicate = criteriaBuilder.conjunction();

        if (filter.getAgency() != null) {
            AgencyDAO agency = entityManager.find(AgencyDAO.class, filter.getAgency().getId());
            criteriaPredicate = criteriaBuilder.and(
                    criteriaPredicate, criteriaBuilder.equal(
                            root.get("agencyByAgencyId"),
                            filter.getAgency()
                    )
            );
        }

        if (filter.getDestination() != null) {
            criteriaPredicate = criteriaBuilder.and(
                    criteriaPredicate, criteriaBuilder.equal(
                            root.get("destinationByDestinationId"),
                            filter.getDestination()
                    )
            );
        }

        if (filter.getMinPrice() != null) {
            criteriaPredicate = criteriaBuilder.and(
                    criteriaPredicate, criteriaBuilder.ge(
                            root.get("price"),
                            filter.getMinPrice()
                    )
            );
        }

        if (filter.getMaxPrice() != null) {
            criteriaPredicate = criteriaBuilder.and(
                    criteriaPredicate, criteriaBuilder.le(
                            root.get("price"),
                            filter.getMaxPrice()
                    )
            );
        }

        if (filter.getStartPeriod() != null) {
            criteriaPredicate = criteriaBuilder.and(
                    criteriaPredicate, criteriaBuilder.greaterThanOrEqualTo(
                            root.get("startDate"),
                            filter.getStartPeriod()
                    )
            );
        }

        if (filter.getEndPeriod() != null) {
            criteriaPredicate = criteriaBuilder.and(
                    criteriaPredicate, criteriaBuilder.lessThanOrEqualTo(
                            root.get("endDate"),
                            filter.getEndPeriod()
                    )
            );
        }

        criteriaQuery
                .select(root)
                .where(criteriaPredicate);

        return criteriaQuery;


    }

    public List<PackageDAO> selectAllFilteredPackages(FilterDTO filter) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();


        entityManager.getTransaction().begin();

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<PackageDAO> criteriaQuery = computeFilterQuery(entityManager,
                criteriaBuilder, filter);
        TypedQuery<PackageDAO> allQuery = entityManager.createQuery(criteriaQuery);
        List<PackageDAO> packages = allQuery.getResultList();

        entityManager.getTransaction().commit();
        entityManager.close();

        return packages;
    }
}
