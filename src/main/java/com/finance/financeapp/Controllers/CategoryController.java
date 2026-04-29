package com.finance.financeapp.Controllers;

import com.finance.financeapp.DTO.CategoryDTO;
import com.finance.financeapp.entities.Category;
import com.finance.financeapp.DTO.CategoryService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    private String getCurrentUsername() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    @PostMapping
    public Category create(@RequestBody CategoryDTO dto) {
        return categoryService.createCategory(dto, getCurrentUsername());
    }

    @GetMapping
    public List<Category> list() {
        return categoryService.getUserCategories(getCurrentUsername());
    }

    @PutMapping("/{id}")
    public Category update(@PathVariable Long id, @RequestBody CategoryDTO dto) {
        return categoryService.updateCategory(id, dto, getCurrentUsername());
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        categoryService.deleteCategory(id, getCurrentUsername());
    }
}
