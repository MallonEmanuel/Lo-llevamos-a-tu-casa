package unpsjb.labprog.tp2.springmvc.bussines;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import unpsjb.labprog.tp2.springmvc.dao.ArticleDao;
import unpsjb.labprog.tp2.springmvc.dao.ArticleOrderProviderDao;
import unpsjb.labprog.tp2.springmvc.model.Article;
import unpsjb.labprog.tp2.springmvc.model.ArticleOrderProvider;
import unpsjb.labprog.tp2.springmvc.model.ShipmentProvider;

@Component
public class RefresherStock {

	@Autowired
	ArticleDao articleDao;
	@Autowired
	ShipmentProviderService shipmentProviderService; 
	@Autowired
	ArticleOrderProviderDao articleOrderProviderDao;
	
	RandomGenerator randomGenerator;
	
	@Value("${refresh_stock.min_stock}")
	private float minStock;
	@Value("${refresh_stock.min_repo_stock}")
	private int minRepoStock;
	@Value("${refresh_stock.max_repo_stock}")
	private int maxRepoStock;
	
	private ShipmentProvider shipmentProvider;
	private MyDate myDate;
	
	public RefresherStock(){
	}
	
	public void init(){
		randomGenerator = Factory.getRandomGenerator();
		myDate = Factory.getMyDate();
		shipmentProviderService.init();
	}
	
	public int refresh(){
		List<Article> articleList = articleDao.findAllForRefresh(minStock);
		List<ArticleOrderProvider> articleOrderProviders = new ArrayList<>();
		for (Article article : articleList) {
			shipmentProvider = shipmentProviderService.getShipmentPovider(myDate.getGregorianCalendar());
			articleOrderProviders.add(refreshArticle(article,shipmentProvider));
		}
		articleOrderProviderDao.bulk(articleOrderProviders);
		return articleList.size();
	}
	
	
	public ArticleOrderProvider refreshArticle(Article article, ShipmentProvider shipmentProvider){
		float quantity = randomGenerator.generate(minRepoStock, maxRepoStock);
		float newStock =(float)article.getStock()+ quantity;
		article.setStock(newStock);
		ArticleOrderProvider articleOrderProvider = new ArticleOrderProvider(article, shipmentProvider, quantity);
		return articleOrderProvider;
	}
}
