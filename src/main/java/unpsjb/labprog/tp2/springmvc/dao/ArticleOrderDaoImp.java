package unpsjb.labprog.tp2.springmvc.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import unpsjb.labprog.tp2.springmvc.model.ArticleOrder;

@Repository
public class ArticleOrderDaoImp extends GenericDaoSpringHibernateTemplate<ArticleOrder, Long>implements ArticleOrderDao {

	public ArticleOrderDaoImp() {
		super(ArticleOrder.class);
	}


	@Override
	public List<ArticleOrder> findAllWithoutShipmentOrderByAddress() {
		String[] params = {};
		Object[] values = {};
		return super.findByNameQuery("ArticleOrder.findAllWithoutShipmentOrderByAddress", params, values);		
	}
	
	@Override
	public List<ArticleOrder> findAllWithShipmentDeliveredWithOutReceiptOrderByCustomer(){
		String[] params = {};
		Object[] values = {};
		return super.findByNameQuery("ArticleOrder.findAllWithShipmentDeliveredWithOutReceiptOrderByCustomer", params, values);
	}
	
}
