package unpsjb.labprog.tp2.springmvc.dao;

import org.springframework.stereotype.Repository;

import unpsjb.labprog.tp2.springmvc.model.Order;
@Repository
public class OrderDaoImp extends GenericDaoSpringHibernateTemplate<Order, Long> implements OrderDao {

	public OrderDaoImp() {
		super(Order.class);
	}

	
	
}
