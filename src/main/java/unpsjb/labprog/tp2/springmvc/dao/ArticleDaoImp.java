package unpsjb.labprog.tp2.springmvc.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import unpsjb.labprog.tp2.springmvc.model.Article;

@Repository
//@Transactional
public class ArticleDaoImp  extends GenericDaoSpringHibernateTemplate<Article, Long> implements ArticleDao{
	
	public ArticleDaoImp() {
		super(Article.class);
	}

	@Override
	public Article findByName(String name) {
		String[] params = {"name"};
		Object[] values = {name};
		List<Article> list = super.findByNameQuery("Article.findByName", params, values);
		return list.isEmpty()? null: list.get(0);
	}
	@Transactional
	@Override
	public void deleteIfExists(String name) {
		Article article = findByName(name);
		if (article != null)
			delete(article);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> findByRandom(int limit){
		Session session = getHibernateTemplate().getSessionFactory().openSession(); 
		Query query = session.createQuery("SELECT e FROM Article e ORDER BY RANDOM()");
		query.setMaxResults(limit);
		List<Object> list = query.list();
		session.close();
		return list;
	}
	@Override
	public List<Article> findAllForRefresh(float stock){
		String[] params = {"stock"};
		Object[] values = {stock};
		return super.findByNameQuery("Article.findAllForRefresh", params, values);				
	}

	@Override
	public List<Article> filterByName(String name) {	
		String[] params = {"name"};
		Object[] values = {name};
		List<Article> list = super.findByNameQuery("Article.filterByName", params, values);
		return list;
	}
	
}
