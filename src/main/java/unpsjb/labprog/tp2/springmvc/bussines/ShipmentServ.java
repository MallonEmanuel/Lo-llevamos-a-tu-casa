package unpsjb.labprog.tp2.springmvc.bussines;

import java.util.GregorianCalendar;

import unpsjb.labprog.tp2.springmvc.model.Address;
import unpsjb.labprog.tp2.springmvc.model.ArticleOrder;

public class ShipmentServ {

	private Address address;
	private boolean gr;
	private int days;

	private DateUtil dateUtil;
	private RandomGenerator randomGenerator;
	/**
	 * Contructor de clase
	 * @param days 
	 */
	public ShipmentServ(int days) {
		this.days = days;
		dateUtil = Factory.getDateUtil();
		randomGenerator = Factory.getRandomGenerator();
	}
	/**
	 * Este metodo indica si se debe actualizar el articulo pedido actual
	 * o no 
	 */
	public boolean generateShipment(ArticleOrder articleOrder, GregorianCalendar today) {
		
		// Pasado la cantidad maxima de dias
		if (today.after(dateUtil.getNextValidDay(today, days))) {
			address = articleOrder.getOrder().getAddress();
			return gr = true;
		}
		// Si la dir del art pedido es igual a la ant..
		if (articleOrder.getOrder().getAddress().equals(address)) {
			return gr;
		} else {
			address = articleOrder.getOrder().getAddress();
			if (randomGenerator.generate(0, 10) < 8) {
				return gr = true;
			} else {
				return gr = false;
			}
		}
	}
	@Override
	public String toString() {
		return "ShipmentServ [address=" + address + ", gr=" + gr + ", days=" + days + "]";
	}
	

}
