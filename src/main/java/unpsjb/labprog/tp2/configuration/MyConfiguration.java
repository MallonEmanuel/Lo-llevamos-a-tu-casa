package unpsjb.labprog.tp2.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyConfiguration {

	// Configuration Order Generator
	@Value("${order_generator-generate_article_for_order-quantity}")
	private int quantity;
	@Value("${order_generator.generate_article_for_order.min_article}")
	private int minArticle;
	@Value("${order_generator.generate_article_for_order.max_article}")
	private int maxArticle;
	@Value("${order_generator.customer_random.customer_limit}")
	private int customerLimit;
	@Value("${order_generator.article_random.article_limit}")
	private int articleLimit;
	

	@Value("${order_generator.generate_max_luck}")
	private int maxLuck;
	@Value("${order_generator.generate_luck_distinct_address}")
	private int luckDistinctAddress;
	@Value("${order_generator.generate_luck_same_address}")
	private int luckSameAddress;
	
//	Configuracion RefreshStock
	@Value("${refresh_stock.min_stock}")
	private float minStock;
	@Value("${refresh_stock.min_repo_stock}")
	private int minRepoStock;
	@Value("${refresh_stock.max_repo_stock}")
	private int maxRepoStock;
	
}
