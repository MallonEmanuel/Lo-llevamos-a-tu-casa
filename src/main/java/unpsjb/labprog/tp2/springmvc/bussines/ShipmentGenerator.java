package unpsjb.labprog.tp2.springmvc.bussines;

import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import unpsjb.labprog.tp2.springmvc.dao.ArticleDao;
import unpsjb.labprog.tp2.springmvc.dao.ArticleOrderDao;
import unpsjb.labprog.tp2.springmvc.dao.ShipmentDao;
import unpsjb.labprog.tp2.springmvc.model.ArticleOrder;
import unpsjb.labprog.tp2.springmvc.model.Shipment;

@Component("shipment_shipment")
public class ShipmentGenerator {

	@Autowired
	ArticleDao articleDao;
	@Autowired
	ArticleOrderDao articleOrderDao;
	@Autowired
	ShipmentDao shipmentDao;

	@Autowired
	ShipmentService shipmentService;

	ShipmentServ shipmentServ;
	private MyDate myDate;
	/**
	 * Contructor de Clase
	 */
	public ShipmentGenerator() {
	}

	public void init() {
		shipmentServ = new ShipmentServ(1);
		myDate = Factory.getMyDate();
	}

	/**
	 * Este metodo se ocupa de generar remitos.
	 * @return 
	 */
	public int generate() {
		Shipment shipmentBuild = null;
		List<ArticleOrder> articleOrderList = articleOrderDao.findAllWithoutShipmentOrderByAddress();
		shipmentService.restart();
		for (ArticleOrder articleOrder : articleOrderList) {
			if (shipmentServ.generateShipment(articleOrder, (GregorianCalendar) myDate.getGregorianCalendar())) {
				if (articleOrder.getArticle().getStock() >= articleOrder.getQuantity()) {
					shipmentBuild = shipmentService.getShipment(articleOrder, myDate.getGregorianCalendar().getTime());
					articleOrder = refreshStock(articleOrder);
					articleOrder.setShipment(shipmentBuild);
				}else{
					System.out.println("El dia : "+myDate.getGregorianCalendar().getTime()+ " el stock : "+ articleOrder.getArticle().getStock() + 
							"no supera la cantidad pedida de el articulo "+articleOrder.getArticle().getId() +" con cantidad : "+articleOrder.getQuantity());
				}
			}
		}
		articleOrderDao.bulk(articleOrderList);
		return shipmentService.getCount();
	}

	/**
	 * Este metodo asigna un remito al articulo pedido y actualiza el stock del
	 * articulo.
	 */
	private ArticleOrder refreshStock(ArticleOrder articleOrder) {
		float newStock = articleOrder.getArticle().getStock() - articleOrder.getQuantity();
		articleOrder.getArticle().setStock(newStock);
		return articleOrder;
	}
}