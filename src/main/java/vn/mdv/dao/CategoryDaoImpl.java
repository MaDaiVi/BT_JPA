package vn.mdv.dao;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import vn.mdv.connection.DBconnection;
import vn.mdv.models.Category;

public class CategoryDaoImpl implements CategoryDao {

    @Override
    public List<Category> findAll() {
        EntityManager em = DBconnection.getEntityManager();

        try {
            return em.createQuery(
                    "SELECT c FROM Category c", Category.class)
                    .getResultList();

        } catch (Exception e) {
            e.printStackTrace();
            return null;

        } finally {
            em.close();
        }
    }

    @Override
    public Category findById(int cateId) {
        EntityManager em = DBconnection.getEntityManager();

        try {
            return em.find(Category.class, cateId);

        } catch (Exception e) {
            e.printStackTrace();
            return null;

        } finally {
            em.close();
        }
    }

    @Override
    public boolean insert(Category category) {
        EntityManager em = DBconnection.getEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();

            em.persist(category);

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
    public boolean update(Category category) {
        EntityManager em = DBconnection.getEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();

            Category existingCategory =
                    em.find(Category.class, category.getCateId());

            if (existingCategory == null) {
                transaction.rollback();
                return false;
            }

            existingCategory.setCateName(category.getCateName());
            existingCategory.setIcons(category.getIcons());

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
    public boolean delete(int cateId) {
        EntityManager em = DBconnection.getEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();

            Category category = em.find(Category.class, cateId);

            if (category == null) {
                transaction.rollback();
                return false;
            }

            em.remove(category);

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
    public Category findByName(String cateName) {
        EntityManager em = DBconnection.getEntityManager();

        try {
            List<Category> categories = em.createQuery(
                    "SELECT c FROM Category c WHERE c.cateName = :cateName",
                    Category.class)
                    .setParameter("cateName", cateName)
                    .setMaxResults(1)
                    .getResultList();

            return categories.isEmpty() ? null : categories.get(0);

        } catch (Exception e) {
            e.printStackTrace();
            return null;

        } finally {
            em.close();
        }
    }

    @Override
    public List<Category> search(String keyword) {
        EntityManager em = DBconnection.getEntityManager();

        try {
            return em.createQuery(
                    "SELECT c FROM Category c WHERE c.cateName LIKE :keyword",
                    Category.class)
                    .setParameter("keyword", "%" + keyword + "%")
                    .getResultList();

        } catch (Exception e) {
            e.printStackTrace();
            return null;

        } finally {
            em.close();
        }
    }
}