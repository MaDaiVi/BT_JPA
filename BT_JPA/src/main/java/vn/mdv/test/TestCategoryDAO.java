package vn.mdv.test;

import java.util.List;

import vn.mdv.dao.CategoryDao;
import vn.mdv.dao.CategoryDaoImpl;
import vn.mdv.models.Category;

public class TestCategoryDAO {

    public static void main(String[] args) {

        CategoryDao categoryDao = new CategoryDaoImpl();

        // 1. Tìm danh mục theo ID
        System.out.println("===== FIND BY ID =====");

        Category category = categoryDao.findById(1);

        if (category != null) {
            System.out.println("Tên: " + category.getCateName());
        } else {
            System.out.println("Không tìm thấy danh mục!");
        }

        // 2. Tìm danh mục theo tên
        System.out.println("\n===== FIND BY NAME =====");

        category = categoryDao.findByName("Laptop");

        if (category != null) {
            System.out.println("ID: " + category.getCateId());
            System.out.println("Tên: " + category.getCateName());
        } else {
            System.out.println("Không tìm thấy danh mục!");
        }

        // 3. Tìm kiếm theo từ khóa
        System.out.println("\n===== SEARCH =====");

        List<Category> categories = categoryDao.search("Máy");

        if (categories != null && !categories.isEmpty()) {
            for (Category c : categories) {
                System.out.println("ID: " + c.getCateId());
                System.out.println("Tên: " + c.getCateName());
            }
        } else {
            System.out.println("Không tìm thấy danh mục!");
        }
    }
}