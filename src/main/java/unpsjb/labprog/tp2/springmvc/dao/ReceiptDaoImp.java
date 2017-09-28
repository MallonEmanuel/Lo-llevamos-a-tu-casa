package unpsjb.labprog.tp2.springmvc.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import unpsjb.labprog.tp2.springmvc.model.Receipt;

@Repository
public class ReceiptDaoImp extends GenericDaoSpringHibernateTemplate<Receipt, Long> implements ReceiptDao {

	public ReceiptDaoImp() {
		super(Receipt.class);
	}
	@Override
	public List<Receipt> getAllNonPaymentOrderByDeliveryDateDesc(){
		String[] params = {};
		Object[] values = {};
		return super.findByNameQuery("Receipt.getAllNonPaymentOrderByDeliveryDateDesc", params, values);				
	}
}
