package com.proyecto.pcstore.pcstore.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.proyecto.pcstore.pcstore.domain.Article;
import com.proyecto.pcstore.pcstore.repository.ArticleRepository;

@Service
public class ArticlePersistService {

	ArticleRepository articleRepossitory;

	public ArticlePersistService(ArticleRepository articleRepossitory) {
		this.articleRepossitory = articleRepossitory;
	}

	public Article getArticle(Long id) {
		return articleRepossitory.getReferenceById(id);
	}

	public Long add(Article article) {
		Article savedArticle = articleRepossitory.save(article);
		return savedArticle.getId();
	}

	public List<Article> getArticles() {
		return articleRepossitory.findAll();
	}

	public void modify(Long id, Article article) {
		Article savedArticle = articleRepossitory.getReferenceById(id);
		savedArticle.setName(article.getName());
		savedArticle.setPrice(article.getPrice());
		savedArticle.setDescription(article.getDescription());
		savedArticle.setCategory(article.getCategory());
		savedArticle.setImage(article.getImage());
		articleRepossitory.save(savedArticle);
	}

	public void delete(Long id) {
		articleRepossitory.delete(articleRepossitory.getReferenceById(id));
	}
	
	public Optional<Article> findById(Long id) {
		return articleRepossitory.findById(id);
	}

	public List<Article> findByNameContainingIgnoreCase(String name){
		return articleRepossitory.findByNameContainingIgnoreCase(name);
	}

	public List<Article> findByCategorieContainingIgnoreCase(String categorie){
		return articleRepossitory.findByCategoryContainingIgnoreCase(categorie);
	}
}