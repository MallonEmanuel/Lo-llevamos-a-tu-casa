package unpsjb.labprog.tp2.springmvc.dao;

import java.util.List;

import unpsjb.labprog.tp2.springmvc.model.Article;

public interface ArticleDao extends GenericDao<Article, Long>,Finder{
	public Article findByName(String name);
	public void deleteIfExists(String name);
	List<Article> findAllForRefresh(float value);
	public List<Article> filterByName(String name);
}
