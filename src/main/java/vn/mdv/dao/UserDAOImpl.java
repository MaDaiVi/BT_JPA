
package vn.mdv.dao;

import java.sql.Timestamp;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import vn.mdv.connection.DBconnection;
import vn.mdv.models.User;

public class UserDAOImpl implements UserDAO {

	@Override
	public User get(String userName) {

		EntityManager em = DBconnection.getEntityManager();

		try {
			return em.createQuery("SELECT u FROM User u WHERE u.userName = :userName", User.class)
					.setParameter("userName", userName).getResultStream().findFirst().orElse(null);

		} catch (Exception e) {
			e.printStackTrace();
			return null;

		} finally {
			em.close();
		}
	}

	@Override
	public boolean register(User user) {

		EntityManager em = DBconnection.getEntityManager();
		EntityTransaction transaction = em.getTransaction();

		try {
			transaction.begin();

			// 1. Kiểm tra username, email, phone đã tồn tại
			Long count = em.createQuery("""
					SELECT COUNT(u) FROM User u
					WHERE u.userName = :username
					   OR (:email IS NOT NULL AND u.email = :email)
					   OR (:phone IS NOT NULL AND u.phone = :phone)
					""", Long.class).setParameter("username", user.getUserName()).setParameter("email", user.getEmail())
					.setParameter("phone", user.getPhone()).getSingleResult();

			if (count > 0) {
				transaction.rollback();
				return false;
			}

			// 2. Tự động tăng ID
			Integer maxId = em.createQuery("SELECT MAX(u.id) FROM User u", Integer.class).getSingleResult();

			int newId = (maxId == null) ? 1 : maxId + 1;

			// 3. Thiết lập thông tin tài khoản
			user.setId(newId);
			user.setRoleid(5);
			user.setAvatar(null);
			user.setActive(false);
			user.setCreatedDate(new Timestamp(System.currentTimeMillis()));

			// 4. Thêm User vào database
			em.persist(user);

			// 5. Lưu transaction
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
	public boolean activate(int userId) {

	    EntityManager em = DBconnection.getEntityManager();
	    EntityTransaction transaction = em.getTransaction();

	    try {
	        transaction.begin();

	        User user = em.find(User.class, userId);

	        if (user == null) {
	            transaction.rollback();
	            return false;
	        }

	        user.setActive(true);

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
	public User getById(int userId) {

	    EntityManager em = DBconnection.getEntityManager();

	    try {
	        return em.find(User.class, userId);

	    } catch (Exception e) {
	        e.printStackTrace();
	        return null;

	    } finally {
	        em.close();
	    }
	}
	
	@Override
	public User getByEmail(String email) {

	    EntityManager em = DBconnection.getEntityManager();

	    try {
	        return em.createQuery(
	            "SELECT u FROM User u WHERE u.email = :email",
	            User.class
	        )
	        .setParameter("email", email)
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
	public boolean updatePassword(int userId, String hashedPassword) {

	    EntityManager em = DBconnection.getEntityManager();
	    EntityTransaction transaction = em.getTransaction();

	    try {
	        transaction.begin();

	        User user = em.find(User.class, userId);

	        if (user == null) {
	            transaction.rollback();
	            return false;
	        }

	        user.setPassWord(hashedPassword);

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
