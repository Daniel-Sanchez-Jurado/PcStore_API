package com.proyecto.pcstore.pcstore.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.pcstore.pcstore.domain.Article;
import com.proyecto.pcstore.pcstore.error.CustomError;
import com.proyecto.pcstore.pcstore.service.ArticlePersistService;

@RestController
public class ArticlesController {
	
	@Autowired
	ArticlePersistService articlePersistService;
	
	@GetMapping(path = "/articles")
	List<Article> getArticles(@RequestParam(required = false) String name, @RequestParam(required = false) String categorie) {
		if (name != null) {
			return articlePersistService.findByNameContainingIgnoreCase(name);
		} else {
			if (categorie != null){
				return  articlePersistService.findByCategorieContainingIgnoreCase(categorie);
			} else {
				return articlePersistService.getArticles();
			}
		}
	}
	
	@GetMapping(path = "/articles/{id}")
	Article getArticles(@PathVariable Long id) {
		if (articlePersistService.findById(id).isPresent()) {
			return articlePersistService.findById(id).get();
		}
		else
		{
			throw new CustomError("No existe el articulo con el id " + id);
		}
	}
	
	@PostMapping(path = "/articles")
	Long addArticle(@RequestBody Article article) {
		return articlePersistService.add(article);
	}

	@PutMapping(path = "/articles/{id}")
	Article modifyArticle(@RequestBody Article article, @PathVariable Long id) {
		articlePersistService.modify(id, article);
		return article;
	}

	@DeleteMapping(path = "/articles/{id}")
	String deleteArticle(@PathVariable Long id) {
		articlePersistService.delete(id);
		return ("OK");
	}
}
