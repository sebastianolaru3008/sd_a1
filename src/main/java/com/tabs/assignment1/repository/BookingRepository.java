package com.tabs.assignment1.repository;

import com.tabs.assignment1.model.abstraction_objects.PackageDAO;
import com.tabs.assignment1.model.abstraction_objects.UserDAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class BookingRepository {

    private static final EntityManagerFactory entityManagerFactory =
            Persistence.createEntityManagerFactory("ro.assignment1.sd");

    public PackageDAO insertBooking(String userId, String packageId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        PackageDAO bookingPackage = entityManager.find(PackageDAO.class, packageId);
        UserDAO user = entityManager.find(UserDAO.class, userId);

        bookingPackage.addTourist(user);
        user.addBooking(bookingPackage);

        entityManager.getTransaction().commit();
        entityManager.close();

        return bookingPackage;
    }

    public List<PackageDAO> selectAllUserBookings(String userId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();
        UserDAO user = entityManager.find(UserDAO.class, userId);
        entityManager.getTransaction().commit();
        entityManager.close();

        return user.getBookings();
    }
}
