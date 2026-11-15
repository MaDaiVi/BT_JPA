package vn.mdv.services;

import java.util.List;

import vn.mdv.dao.CategoryDao;
import vn.mdv.dao.CategoryDaoImpl;
import vn.mdv.models.Category;

public class CategoryServiceImpl implements CategoryService {

    private CategoryDao categoryDao = new CategoryDaoImpl();

    @Override
    public List<Category> findAll() {
        return categoryDao.findAll();
    }

    @Override
    public Category findById(int cateId) {
        return categoryDao.findById(cateId);
    }

    @Override
    public boolean insert(Category category) {
        if (category == null || category.getCateName() == null
                || category.getCateName().trim().isEmpty()) {
            return false;
        }

        return categoryDao.insert(category);
    }

    @Override
    public boolean update(Category category) {
        if (category == null || category.getCateId() <= 0
                || category.getCateName() == null
                || category.getCateName().trim().isEmpty()) {
            return false;
        }

        return categoryDao.update(category);
    }

    @Override
    public boolean delete(int cateId) {
        if (cateId <= 0) {
            return false;
        }

        return categoryDao.delete(cateId);
    }

    @Override
    public Category findByName(String cateName) {
        return categoryDao.findByName(cateName);
    }

    @Override
    public List<Category> search(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return categoryDao.findAll();
        }

        return categoryDao.search(keyword.trim());
    }
}