package unpsjb.labprog.tp2.springmvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import unpsjb.labprog.tp2.springmvc.dao.ArticleDao;
import unpsjb.labprog.tp2.springmvc.model.Article;

@Service
@RestController
public class ArticleRestController {

	@Autowired
	ArticleDao articleDao;
	

	/**
	 * Retorna todos los articulos
	 */
	@RequestMapping(value = "/articles/", method = RequestMethod.GET)
	public ResponseEntity<List<Article>> listAll() {
		List<Article> articles = articleDao.findAll();
		if (articles.isEmpty()) {
			return new ResponseEntity<List<Article>>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<List<Article>>(articles, HttpStatus.OK);
	}
	
	/**
	 * Retorna un articulo a travez de su id 
	 */
	@RequestMapping(value = "/article/{id}", method = RequestMethod.GET)
	public ResponseEntity<Article> findById(@PathVariable("id") long id) {
		Article article= articleDao.find(id);
		if (article == null) {
			return new ResponseEntity<Article>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<Article>(article, HttpStatus.OK);
	}

	
	/**
	 * Retorna todos los articulos filtrados por nombre 
	 */
	@RequestMapping(value = "/articles/filterByName/{name}", method = RequestMethod.GET)
	public ResponseEntity<List<Article>> filterByName(@PathVariable("name") String name) {
		
		List<Article> articles = articleDao.filterByName(name);
		if (articles.isEmpty()) {
			return new ResponseEntity<List<Article>>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<List<Article>>(articles, HttpStatus.OK);
	}

	
	
}
