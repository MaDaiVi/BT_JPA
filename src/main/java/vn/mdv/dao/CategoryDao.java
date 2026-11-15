package vn.mdv.dao;

import java.util.List;

import vn.mdv.models.Category;

public interface CategoryDao {
    List<Category> findAll();

    Category findById(int cateId);
    
    Category findByName(String cateName);
    
    List<Category> search(String keyword);

    boolean insert(Category category);

    boolean update(Category category);

    boolean delete(int cateId);
}