package unpsjb.labprog.tp2.springmvc.dao;

import java.util.List;

import unpsjb.labprog.tp2.springmvc.model.Receipt;

public interface ReceiptDao extends GenericDao<Receipt, Long> {

	List<Receipt> getAllNonPaymentOrderByDeliveryDateDesc();
}
