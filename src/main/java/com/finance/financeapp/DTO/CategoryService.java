package com.finance.financeapp.DTO;

import com.finance.financeapp.entities.Category;
import com.finance.financeapp.entities.User;
import com.finance.financeapp.repositories.UserRepository;
import com.finance.financeapp.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    public CategoryService(CategoryRepository categoryRepository, UserRepository userRepository) {
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
    }

    public Category createCategory(CategoryDTO dto, String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Category category = new Category();
        category.setName(dto.getName());
        category.setUser(user);
        return categoryRepository.save(category);
    }

    public List<Category> getUserCategories(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return categoryRepository.findByUser(user);
    }

    public Category updateCategory(Long id, CategoryDTO dto, String username) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));
        if (!category.getUser().getUsername().equals(username)) {
            throw new RuntimeException("You do not own this category");
        }
        category.setName(dto.getName());
        return categoryRepository.save(category);
    }

    public void deleteCategory(Long id, String username) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));
        if (!category.getUser().getUsername().equals(username)) {
            throw new RuntimeException("You do not own this category");
        }
        categoryRepository.deleteById(id);
    }
}
