package com.tabs.assignment1.repository;

import com.tabs.assignment1.model.abstraction_objects.UserDAO;
import com.tabs.assignment1.model.transfer_objects.UserDTO;
import org.mindrot.jbcrypt.BCrypt;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.UUID;

public class UserRepository {

    private static final EntityManagerFactory entityManagerFactory =
            Persistence.createEntityManagerFactory("ro.assignment1.sd");

    private String hashPassword(String plainTextPassword, String passwordSalt) {
        return BCrypt.hashpw(plainTextPassword, passwordSalt);
    }

    public UserDAO insertUser(UserDTO userData) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        String salt = BCrypt.gensalt();
        String hashedPassword = hashPassword(userData.getPassword(), salt);

        UserDAO newUser = new UserDAO();
        newUser.setId(UUID.randomUUID().toString());
        newUser.setUsername(userData.getUsername());
        newUser.setPasswordHash(hashedPassword);

        entityManager.getTransaction().begin();
        entityManager.persist(newUser);
        entityManager.getTransaction().commit();
        entityManager.close();


        return newUser;

    }

    public UserDAO selectUser(UserDTO userData) throws Exception {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        UserDAO loggedUser;

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<UserDAO> criteriaQuery = criteriaBuilder.createQuery(UserDAO.class);
        Root<UserDAO> root = criteriaQuery.from(UserDAO.class);

        criteriaQuery
                .select(root)
                .where(criteriaBuilder.equal(root.get("username"), userData.getUsername()));

        loggedUser = entityManager.createQuery(criteriaQuery).getSingleResult();

        entityManager.getTransaction().commit();
        entityManager.close();


        return loggedUser;
    }
}
