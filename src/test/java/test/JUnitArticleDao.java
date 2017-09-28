package test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import unpsjb.labprog.tp2.springmvc.dao.ArticleDao;
import unpsjb.labprog.tp2.springmvc.model.Article;

public class JUnitArticleDao extends JUnitDao {

	@Autowired
	protected ArticleDao articleDao;
	protected int articleCount;
	
	private String MARTILLO ="MARTILLO";
	private String CLAVOS = "CLAVOS";
	private String DESCRIPTION_CLAVOS = "FERRETERIA";
	
	@Before
	public void before() {
		articleCount = articleDao.count();
		articleDao.save(new Article(MARTILLO));
		articleDao.save(new Article(CLAVOS));
	}

	@After
	public void after() {
		articleDao.deleteIfExists(MARTILLO);
		articleDao.deleteIfExists(CLAVOS);
	}

	@Test
	public void testFindByName() {
		assertTrue(articleDao.findByName(MARTILLO).getName().equals(MARTILLO));
	}

	@Test
	public void testSave() {
		Article article = articleDao.findByName(MARTILLO);
		assertTrue(articleDao.exists(article));
	}

	@Test
	public void testDelete() {
		Article article = articleDao.findByName(CLAVOS);
		articleDao.delete(article);
		assertNull(articleDao.findByName(CLAVOS));
	}

	@Test
	public void testUpdate() {
		Article article = articleDao.findByName(CLAVOS);
		article.setDescription(String.valueOf(DESCRIPTION_CLAVOS));
		articleDao.update(article);
		assertTrue(articleDao.findByName(CLAVOS).getDescription().equals(DESCRIPTION_CLAVOS));
	}

	@Test
	public void testFindAllUser() {
		assertEquals(articleCount + 2, articleDao.findAll().size());
	}

}
