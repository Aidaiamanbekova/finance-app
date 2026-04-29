package com.finance.financeapp.repositories;


import com.finance.financeapp.entities.Category;
import com.finance.financeapp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findByUser(User user);
}