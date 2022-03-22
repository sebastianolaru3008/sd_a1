package com.tabs.assignment1.repository;

import com.tabs.assignment1.model.abstraction_objects.DestinationDAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.UUID;

public class DestinationRepository {

    private static final EntityManagerFactory entityManagerFactory =
            Persistence.createEntityManagerFactory("ro.assignment1.sd");

    public DestinationDAO insertDestination(String destinationName) throws Exception {
        EntityManager entityManager = entityManagerFactory.createEntityManager();


        DestinationDAO destination = new DestinationDAO();
        destination.setId(UUID.randomUUID().toString());
        destination.setName(destinationName);

        entityManager.getTransaction().begin();
        entityManager.persist(destination);
        entityManager.getTransaction().commit();
        entityManager.close();


        return destination;
    }

    public DestinationDAO removeDestination(String destinationId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();
        DestinationDAO destination = entityManager.find(DestinationDAO.class, destinationId);
        entityManager.remove(destination);
        entityManager.getTransaction().commit();
        entityManager.close();

        return destination;
    }

    public List<DestinationDAO> selectAllDestinations() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<DestinationDAO> criteriaQuery = criteriaBuilder.createQuery(DestinationDAO.class);
        Root<DestinationDAO> root = criteriaQuery.from(DestinationDAO.class);
        criteriaQuery.select(root);

        TypedQuery<DestinationDAO> allQuery = entityManager.createQuery(criteriaQuery);
        List<DestinationDAO> destinations = allQuery.getResultList();

        entityManager.getTransaction().commit();
        entityManager.close();

        return destinations;
    }
}
