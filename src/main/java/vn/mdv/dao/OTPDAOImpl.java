package vn.mdv.dao;

import java.time.LocalDateTime;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import vn.mdv.connection.DBconnection;
import vn.mdv.models.OTPVerification;

public class OTPDAOImpl implements OTPDAO {

    @Override
    public boolean insert(OTPVerification otp) {

        EntityManager em = DBconnection.getEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();

            em.persist(otp);

            transaction.commit();

            return true;

        } catch (Exception e) {

            if (transaction.isActive()) {
                transaction.rollback();
            }

            e.printStackTrace();
            return false;

        } finally {
            em.close();
        }
    }

    @Override
    public OTPVerification getValidOTP(int userId, String purpose) {

        EntityManager em = DBconnection.getEntityManager();

        try {
            return em.createQuery(
                """
                SELECT o FROM OTPVerification o
                WHERE o.user.id = :userId
                  AND o.purpose = :purpose
                  AND o.isUsed = false
                  AND o.expiresAt > :now
                ORDER BY o.createdAt DESC
                """,
                OTPVerification.class
            )
            .setParameter("userId", userId)
            .setParameter("purpose", purpose)
            .setParameter("now", LocalDateTime.now())
            .setMaxResults(1)
            .getResultStream()
            .findFirst()
            .orElse(null);

        } catch (Exception e) {

            e.printStackTrace();
            return null;

        } finally {
            em.close();
        }
    }

    @Override
    public boolean update(OTPVerification otp) {

        EntityManager em = DBconnection.getEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();

            em.merge(otp);

            transaction.commit();

            return true;

        } catch (Exception e) {

            if (transaction.isActive()) {
                transaction.rollback();
            }

            e.printStackTrace();
            return false;

        } finally {
            em.close();
        }
    }
}