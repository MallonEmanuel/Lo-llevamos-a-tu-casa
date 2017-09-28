package unpsjb.labprog.tp2.springmvc.bussines;

import java.util.GregorianCalendar;

import unpsjb.labprog.tp2.springmvc.model.ArticleOrder;
import unpsjb.labprog.tp2.springmvc.model.Customer;

public class ReceiptServ {
	private Customer customer;
	private boolean gr;
	private int days;

	private DateUtil dateUtil;
	private RandomGenerator randomGenerator;
	/**
	 * Contructor de clase
	 * @param days 
	 */
	public ReceiptServ(int days){
		this.days = days;
		dateUtil = Factory.getDateUtil();
		randomGenerator = Factory.getRandomGenerator();
	}
	/**
	 * Este metodo indica si se debe actualizar el articulo pedido actual
	 * o no 
	 */
	public boolean generateReceipt(ArticleOrder articleOrder, GregorianCalendar today) {
		
		// Pasado la cantidad maxima de dias
		if (today.after(dateUtil.getNextValidDay(today, days))) {
			customer = articleOrder.getOrder().getCustomer();
			return gr = true;
		}
		// Si el cliente del art anterior cambia
		if (articleOrder.getOrder().getCustomer().equals(customer)) {
			return gr;
		} else {
			customer = articleOrder.getOrder().getCustomer();
			if (randomGenerator.generate(0, 10) < 8) {
				return gr = true;
			} else {
				return gr = false;
			}
		}
	}

	
	
}
