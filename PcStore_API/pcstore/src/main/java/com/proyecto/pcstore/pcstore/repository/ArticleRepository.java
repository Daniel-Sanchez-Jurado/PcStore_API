package com.proyecto.pcstore.pcstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.pcstore.pcstore.domain.Article;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Long>{
    List<Article> findByNameContainingIgnoreCase(String name);
    List<Article> findByCategorieContainingIgnoreCase(String categorie);
}
