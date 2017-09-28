package unpsjb.labprog.tp2.springmvc.dao;

import java.util.List;

import unpsjb.labprog.tp2.springmvc.model.ArticleOrder;

public interface ArticleOrderDao extends GenericDao<ArticleOrder, Long> {

	List<ArticleOrder> findAllWithoutShipmentOrderByAddress();

	List<ArticleOrder> findAllWithShipmentDeliveredWithOutReceiptOrderByCustomer();

}
