package vn.mdv.test;

import java.util.List;

import vn.mdv.models.Category;
import vn.mdv.services.CategoryService;
import vn.mdv.services.CategoryServiceImpl;

public class TestCategoryService {

    public static void main(String[] args) {

        CategoryService categoryService = new CategoryServiceImpl();

        List<Category> categories = categoryService.findAll();

        if (categories != null && !categories.isEmpty()) {
            for (Category category : categories) {
                System.out.println("ID: " + category.getCateId());
                System.out.println("Tên: " + category.getCateName());
                System.out.println("----------------------");
            }
        } else {
            System.out.println("Không tìm thấy danh mục!");
        }
    }
}