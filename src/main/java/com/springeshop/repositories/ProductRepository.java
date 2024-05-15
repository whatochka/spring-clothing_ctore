package com.springeshop.repositories;

import com.springeshop.data.domain.Category;
import com.springeshop.data.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Override
    @NonNull
    List<Product> findAll();

    @Query(value = "SELECT p FROM Product p WHERE " +
            "p.name ILIKE %:searchTerm% OR " +
            "p.url_link ILIKE %:searchTerm% OR " +
            "p.manufacturer.name ILIKE %:searchTerm% OR " +
            "p.category.name ILIKE %:searchTerm% OR " +
            "levenshtein(p.name, :searchTerm) <= :maxDistance OR " +
            "levenshtein(p.category.name, :searchTerm) <= :maxDistance OR " +
            "levenshtein(p.manufacturer.name, :searchTerm) <= :maxDistance")
    List<Product> findBySearchTermFuzzy(
            @Param("searchTerm") String searchTerm,
            @Param("maxDistance") int maxDistance);

    Product findFirstByName(String name);

    List<Product> findAllByNameIsContaining(String name);

    List<Product> findAllByCategoryName(String category);
}
