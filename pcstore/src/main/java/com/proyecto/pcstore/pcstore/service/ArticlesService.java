package com.proyecto.pcstore.pcstore.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.proyecto.pcstore.pcstore.domain.Article;

@Service
public class ArticlesService {

	List<Article> articles = new ArrayList<Article>();

	//Artículo hardcodeado
	public ArticlesService() {
		Article article = new Article();
		article.setName("Ratón");
		article.setPrice((float) 15.95);
		article.setCategory("Ratones");
		article.setDescription("Ratón inalámbrico");
		articles.add(article);
	}

	public Article getArticle(int id) {
		return articles.get(id - 1);
	}

	public int add(Article article) {
		articles.add(article);
		return articles.size();
	}

	public List<Article> getArticles() {
		return articles;
	}
	
	public void modify(int id, Article article) {
		articles.set(id - 1, article);
	}	

	public void detele(int id) {
		articles.remove(id - 1);
	}

	public List<Article> searchByName(String name) {

		List<Article> articleResult = new ArrayList<Article>();
		for (Article article : articles) {
			if (article.getName().contains(name)) {
				articleResult.add(article);
			}
		}
		return articleResult;
	}
}
