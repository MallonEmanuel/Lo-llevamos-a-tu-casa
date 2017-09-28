package unpsjb.labprog.tp2.springmvc.bussines;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import unpsjb.labprog.tp2.springmvc.dao.ReceiptDao;
import unpsjb.labprog.tp2.springmvc.model.ArticleOrder;
import unpsjb.labprog.tp2.springmvc.model.Customer;
import unpsjb.labprog.tp2.springmvc.model.Receipt;
@Component("receipt_service")
public class ReceiptService {

	private Receipt receipt;
	private Customer customerPrevious = null;
	
	@Autowired
	ReceiptDao receiptDao;
	
	private int count;
	/**
	 * Constructor de Clase
	 */
	public ReceiptService(){
	}
	
	public void restart(){
		count = 0;
	}
	/**
	 * Obtiene la Factura con la que se esta trabajando, en caso que el cliente
	 * cambie, se genera una nueva factura
	 */
	public Receipt getReceipt(ArticleOrder articleOrder, Date date) {

		if (!articleOrder.getOrder().getCustomer().equals(customerPrevious)) {
			receipt = generateReceipt(articleOrder.getOrder().getCustomer(), date);
			customerPrevious = articleOrder.getOrder().getCustomer();
			count++;
		}
		return receipt;
	}

	/**
	 * Este metodo genera una nueva factura, asignando un cliente y una fecha
	 */
	public Receipt generateReceipt(Customer customer, Date date) {
		Receipt receipt = new Receipt(customer, date);
		receipt.setId(receiptDao.save(receipt));
		return receipt;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	
	
}
