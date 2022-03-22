package com.tabs.assignment1.repository;

import com.tabs.assignment1.model.abstraction_objects.AgencyDAO;
import com.tabs.assignment1.model.transfer_objects.AgencyDTO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.UUID;

public class AgencyRepository {
    private static final EntityManagerFactory entityManagerFactory =
            Persistence.createEntityManagerFactory("ro.assignment1.sd");

    public AgencyDAO insertAgency(AgencyDTO agencyData) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        AgencyDAO newAgency = new AgencyDAO();
        newAgency.setId(UUID.randomUUID().toString());
        newAgency.setName(agencyData.getName());

        entityManager.getTransaction().begin();
        entityManager.persist(newAgency);
        entityManager.getTransaction().commit();
        entityManager.close();

        return newAgency;

    }

    public AgencyDAO selectAgency(String agencyId) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();


        entityManager.getTransaction().begin();
        AgencyDAO agency = entityManager.find(AgencyDAO.class, agencyId);
        entityManager.getTransaction().commit();
        entityManager.close();

        return agency;
    }
}
