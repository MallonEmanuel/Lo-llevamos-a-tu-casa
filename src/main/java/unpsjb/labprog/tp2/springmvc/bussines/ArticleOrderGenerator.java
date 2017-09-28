package unpsjb.labprog.tp2.springmvc.bussines;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import unpsjb.labprog.tp2.springmvc.dao.ArticleDao;
import unpsjb.labprog.tp2.springmvc.model.Article;
import unpsjb.labprog.tp2.springmvc.model.ArticleOrder;
import unpsjb.labprog.tp2.springmvc.model.Order;

@Component
public class ArticleOrderGenerator {

	private ItemRandom articleRandom;

	@Value("${order_generator.article_random.article_limit}")
	private int articleLimit;

	@Autowired
	ArticleDao articleDao;

	private RandomGenerator randomGenerator;

	/**
	 * Contructor de Clase
	 */
	public ArticleOrderGenerator() {
	}

	/**
	 * Inicializacion de Clases auxiliares
	 */
	public void init() {
		this.randomGenerator = Factory.getRandomGenerator();
		this.articleRandom = new ItemRandom(articleDao, articleLimit);
	}

	/**
	 * Este metodo se ocupa de generar articulos para un pedido
	 * @return una lista de articulos asignados al pedido
	 */
	public List<ArticleOrder> generateArticlesForOrder(Order order, int minArticle, int maxArticle, int quantity) {
		int countArticle = randomGenerator.generate(minArticle, maxArticle);
		List<ArticleOrder> articleOrders = new ArrayList<>();
		for (int i = 0; i < countArticle; i++) {
			Article article = (Article) articleRandom.getItem();
			Float products = new Float(randomGenerator.generate(1, quantity));
			articleOrders.add(new ArticleOrder(order, article, article.getPrice(), products));
		}
		return articleOrders;
	}

}
