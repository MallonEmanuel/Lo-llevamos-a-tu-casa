package unpsjb.labprog.tp2.springmvc.bussines;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import unpsjb.labprog.tp2.springmvc.dao.ShipmentDao;
import unpsjb.labprog.tp2.springmvc.model.Address;
import unpsjb.labprog.tp2.springmvc.model.ArticleOrder;
import unpsjb.labprog.tp2.springmvc.model.Shipment;

@Component("shipment_single")
public class ShipmentService {

	private Shipment shipment = null;
	private Address addressPrevious = null;

	@Autowired
	ShipmentDao shipmentDao;

	private int count=0;
	/**
	 * Contructor de clase
	 */
	public ShipmentService() {
	}
	
	public void restart(){
		count=0;
	}
	/**
	 * Obtiene el Remito con el que se esta trabajando, en caso que el domicilio
	 * cambie, se genera un nuevo remito
	 */
	public Shipment getShipment(ArticleOrder articleOrder, Date date) {

		if (!articleOrder.getOrder().getAddress().equals(addressPrevious)) {
			shipment = generateShipment(articleOrder.getOrder().getAddress(), date);
			addressPrevious = articleOrder.getOrder().getAddress();
			count ++;
		}
		return shipment;
	}

	/**
	 * Este metodo genera un nuevo remito, asignando una direccion y una fech de
	 * armado
	 */
	public Shipment generateShipment(Address address, Date date) {
		Shipment shipment = new Shipment(address, date);
		shipment.setId(shipmentDao.save(shipment));
		return shipment;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	
}
