package org.example.eco_v2.product.category;

import org.example.eco_v2.common.repository.GenericRepository;
import org.example.eco_v2.product.category.entity.Category;
import org.springframework.stereotype.Repository;

import java.util.Set;
@Repository
public interface CategoryRepository extends GenericRepository<Category, String> {
    Set<Category> findAllByNameIn(Set<String>names);
}
