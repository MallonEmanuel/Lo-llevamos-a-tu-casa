package unpsjb.labprog.tp2.springmvc.bussines;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import unpsjb.labprog.tp2.springmvc.dao.ArticleOrderDao;
import unpsjb.labprog.tp2.springmvc.dao.CustomerDao;
import unpsjb.labprog.tp2.springmvc.dao.OrderDao;
import unpsjb.labprog.tp2.springmvc.model.Address;
import unpsjb.labprog.tp2.springmvc.model.ArticleOrder;
import unpsjb.labprog.tp2.springmvc.model.Customer;
import unpsjb.labprog.tp2.springmvc.model.Order;

@Component("order_generator")
public class OrderGenerator {

	private RandomGenerator randomGenerator;

	@Value("${order_generator-generate_article_for_order-quantity}")
	private int quantity;

	@Value("${order_generator.generate_article_for_order.min_article}")
	private int minArticle;
	@Value("${order_generator.generate_article_for_order.max_article}")
	private int maxArticle;

	@Value("${order_generator.customer_random.customer_limit}")
	private int customerLimit;

	@Autowired
	OrderDao orderDao;
	@Autowired
	ArticleOrderDao articleOrderDao;
	@Autowired
	CustomerDao customerDao;

	private ItemRandom customerRandom;

	@Autowired
	private ArticleOrderGenerator articleOrderGenerator;

	@Value("${order_generator.generate_max_luck}")
	private int maxLuck;
	@Value("${order_generator.generate_luck_distinct_address}")
	private Integer luckDistinctAddress;
	@Value("${order_generator.generate_luck_same_address}")
	private Integer luckSameAddress;

	Counter counter;
	private MyDate myDate;

	/**
	 * Contructor de clase
	 */
	public OrderGenerator() {
	}

	/**
	 * Inicializacion de Clases auxiliares
	 */
	public void init() {
		this.randomGenerator = Factory.getRandomGenerator();
		this.customerRandom = new ItemRandom(customerDao, customerLimit);
		this.myDate = Factory.getMyDate();
		articleOrderGenerator.init();

		counter = new Counter();
	}

	/**
	 * Este metodo se ocupa de generar una cantidad de pedidos para un dia
	 * especifico
	 */
	public int generate(int orders) {
		int count = 0;
		for (counter.setValue(0); counter.getValue() < orders;) {
			int luck = randomGenerator.getRandom().nextInt(maxLuck);
			count += generateOrders(luck, counter);
		}
		// articleOrderDao.bulk(articleOrders);
		return count;
	}

	/**
	 * Este metodo se ocupa de sortear que tipo de generacion de pedidos se
	 * realizara, para un cliente en deteminada fecha.
	 * 
	 * @return una lista de los articulos pedidos.
	 */
	private int generateOrders(int luck, Counter counter) {

		if (luck == luckDistinctAddress) {
			counter.add(2);
			generateMultipleOrderDistinctAddress(getCustomerWithMultipleAddress());
			return 2;
		} else if (luck == luckSameAddress) {
			counter.add(2);
			generateMultipleOrderSameAddress((Customer) customerRandom.getItem());
			return 2;
		} else {
			counter.add(1);
			generateSingleOrder((Customer) customerRandom.getItem());
			return 1;
		}
	}

	/**
	 * Este metodo genera un cliente que tenga mas de 2 domicilios
	 */

	private Customer getCustomerWithMultipleAddress() {
		Customer customer = (Customer) customerRandom.getItem();
		while (customer.getAddress().size() < 2) {
			customer = (Customer) customerRandom.getItem();
		}
		return customer;
	}

	/**
	 * Genera un pedidos para un cliente, para alguno de sus domicilios
	 * 
	 * @return una lista de los articulos pedidos.
	 */

	private List<ArticleOrder> generateSingleOrder(Customer customer) {
		Address address = (Address) randomGenerator.getRandomItem(customer.getAddress());
		return generateOrder(customer, address);
	}

	/**
	 * Genera multiples pedidos para un cliente, para el mismo domicilios
	 * 
	 * @return una lista de los articulos pedidos.
	 */
	private List<ArticleOrder> generateMultipleOrderSameAddress(Customer customer) {
		Address address = (Address) randomGenerator.getRandomItem(customer.getAddress());
		List<ArticleOrder> articleOrders = generateOrder(customer, address);
		articleOrders.addAll(generateOrder(customer, address));
		return articleOrders;
	}

	/**
	 * Genera multiples pedidos para un cliente, con distintos domicilios
	 * 
	 * @return una lista de los articulos pedidos.
	 */
	private List<ArticleOrder> generateMultipleOrderDistinctAddress(Customer customer) {
		Address address1 = (Address) randomGenerator.getRandomItem(customer.getAddress());
		Address address2 = (Address) randomGenerator.getRandomItemDistinct(customer.getAddress(), address1);
		List<ArticleOrder> articleOrders = generateOrder(customer, address1);
		articleOrders.addAll(generateOrder(customer, address2));
		return articleOrders;
	}

	/**
	 * Genera multiples pedidos para un cliente.
	 * 
	 * @return una lista de los articulos pedidos.
	 */
	@SuppressWarnings({ "null", "unused" })
	private List<ArticleOrder> generateMultipleOrder(Customer customer, List<Address> address) {
		List<ArticleOrder> articleOrders = null;
		for (int i = 0; i < address.size(); i++) {
			articleOrders.addAll(generateOrder(customer, address.get(i)));
		}
		return articleOrders;
	}

	/**
	 * Genera y persiste un pedido para una dirección, dado un dia especifico
	 * 
	 * @return una lista de los articulos pedidos.
	 */
	private List<ArticleOrder> generateOrder(Customer customer, Address address) {
		Order order = new Order(customer, address, myDate.getGregorianCalendar().getTime());
		order.setId(orderDao.save(order));
		List<ArticleOrder> articleOrders = articleOrderGenerator.generateArticlesForOrder(order, minArticle, maxArticle,
				quantity);
		articleOrderDao.bulk(articleOrders);
		return articleOrders;
	}

}
