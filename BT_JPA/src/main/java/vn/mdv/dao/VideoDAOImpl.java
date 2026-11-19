package vn.mdv.dao;

import java.util.List;

import jakarta.persistence.EntityManager;
import vn.mdv.connection.DBconnection;
import vn.mdv.dao.VideoDAO;
import vn.mdv.models.Video;

public class VideoDAOImpl implements VideoDAO {

	@Override
	public List<Video> findAll() {

		EntityManager em = DBconnection.getEntityManager();

		try {
			String jpql = "SELECT v FROM Video v";

			return em.createQuery(jpql, Video.class).getResultList();

		} finally {
			em.close();
		}
	}

	@Override
	public Video findById(String videoId) {

		EntityManager em = DBconnection.getEntityManager();

		try {
			return em.find(Video.class, videoId);

		} finally {
			em.close();
		}
	}

	@Override
	public List<Video> findByTitle(String title) {

		EntityManager em = DBconnection.getEntityManager();

		try {
			String jpql = "SELECT v FROM Video v WHERE v.title LIKE :title";

			return em.createQuery(jpql, Video.class).setParameter("title", "%" + title + "%").getResultList();

		} finally {
			em.close();
		}
	}

	@Override
	public List<Video> findByCategory(int cateId) {

		EntityManager em = DBconnection.getEntityManager();

		try {
			String jpql = "SELECT v FROM Video v WHERE v.category.cateId = :cateId";

			return em.createQuery(jpql, Video.class).setParameter("cateId", cateId).getResultList();

		} finally {
			em.close();
		}
	}

	@Override
	public void insert(Video video) {

		EntityManager em = DBconnection.getEntityManager();

		try {
			em.getTransaction().begin();

			em.persist(video);

			em.getTransaction().commit();

		} catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();

		} finally {
			em.close();
		}
	}

	@Override
	public void update(Video video) {

		EntityManager em = DBconnection.getEntityManager();

		try {
			em.getTransaction().begin();

			em.merge(video);

			em.getTransaction().commit();

		} catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();

		} finally {
			em.close();
		}
	}

	@Override
	public void delete(String videoId) {

		EntityManager em = DBconnection.getEntityManager();

		try {
			em.getTransaction().begin();

			Video video = em.find(Video.class, videoId);

			if (video != null) {
				em.remove(video);
			}

			em.getTransaction().commit();

		} catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();

		} finally {
			em.close();
		}
	}

	@Override
	public List<Video> findByUser(int userId) {

		EntityManager em = DBconnection.getEntityManager();

		try {
			String jpql = "SELECT v FROM Video v WHERE v.user.id = :userId";

			return em.createQuery(jpql, Video.class).setParameter("userId", userId).getResultList();

		} finally {
			em.close();
		}
	}

	@Override
	public List<Video> findByUserWithPagination(int userId, int page, int pageSize) {

		EntityManager em = DBconnection.getEntityManager();

		try {
			String jpql = "SELECT v FROM Video v WHERE v.user.id = :userId";

			return em.createQuery(jpql, Video.class).setParameter("userId", userId)
					.setFirstResult((page - 1) * pageSize).setMaxResults(pageSize).getResultList();

		} finally {
			em.close();
		}
	}

	@Override
	public long countByUser(int userId) {

		EntityManager em = DBconnection.getEntityManager();

		try {
			String jpql = "SELECT COUNT(v) FROM Video v WHERE v.user.id = :userId";

			return em.createQuery(jpql, Long.class).setParameter("userId", userId).getSingleResult();

		} finally {
			em.close();
		}
	}

	@Override
	public List<Video> findAllWithPagination(int page, int pageSize) {

		EntityManager em = DBconnection.getEntityManager();

		try {
			String jpql = "SELECT v FROM Video v ORDER BY v.createdDate DESC";

			return em.createQuery(jpql, Video.class).setFirstResult((page - 1) * pageSize).setMaxResults(pageSize)
					.getResultList();

		} finally {
			em.close();
		}
	}

	@Override
	public long countAll() {

		EntityManager em = DBconnection.getEntityManager();

		try {
			String jpql = "SELECT COUNT(v) FROM Video v";

			return em.createQuery(jpql, Long.class).getSingleResult();

		} finally {
			em.close();
		}
	}

	@Override
	public List<Video> findActiveWithPagination(int page, int pageSize) {
		EntityManager em = DBconnection.getEntityManager();

		try {
			String jpql = "SELECT v FROM Video v " + "WHERE v.active = 1 " + "ORDER BY v.createdDate DESC";

			return em.createQuery(jpql, Video.class).setFirstResult((page - 1) * pageSize).setMaxResults(pageSize)
					.getResultList();

		} finally {
			em.close();
		}
	}

	@Override
	public long countActive() {
		EntityManager em = DBconnection.getEntityManager();

		try {
			String jpql = "SELECT COUNT(v) FROM Video v " + "WHERE v.active = 1";

			return em.createQuery(jpql, Long.class).getSingleResult();

		} finally {
			em.close();
		}
	}

	@Override
	public List<Video> findTop10Latest() {
		EntityManager em = DBconnection.getEntityManager();

		try {
			String jpql = "SELECT v FROM Video v " + "WHERE v.active = 1 " + "ORDER BY v.createdDate DESC";

			return em.createQuery(jpql, Video.class).setMaxResults(10).getResultList();

		} finally {
			em.close();
		}
	}

	@Override
	public void incrementViews(String videoId) {
		EntityManager em = DBconnection.getEntityManager();

		try {
			em.getTransaction().begin();

			em.createQuery("UPDATE Video v SET v.views = v.views + 1 WHERE v.videoId = :videoId")
					.setParameter("videoId", videoId).executeUpdate();

			em.getTransaction().commit();

		} catch (Exception e) {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			throw e;
		} finally {
			em.close();
		}
	}
}