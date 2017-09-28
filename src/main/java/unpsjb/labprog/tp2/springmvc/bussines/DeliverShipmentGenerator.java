package unpsjb.labprog.tp2.springmvc.bussines;
	
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import unpsjb.labprog.tp2.springmvc.dao.ShipmentDao;
import unpsjb.labprog.tp2.springmvc.model.Shipment;

@Component
public class DeliverShipmentGenerator {


	@Autowired
	ShipmentDao shipmentDao;

	private RandomGenerator randomGenerator;
	private DateUtil dateUtil;
	private MyDate myDate;
	
	public DeliverShipmentGenerator() {
	}
	
	public void init() {
		randomGenerator = Factory.getRandomGenerator();
		dateUtil = Factory.getDateUtil();
		myDate = Factory.getMyDate();
	}

	public int generate() {
		List<Shipment> shipmentList = shipmentDao.findAllWithOutDeliver();
		int count =0;
		for (Shipment shipment : shipmentList) {
			
			if (myDate.getGregorianCalendar().before(dateUtil.getNextValidDay(myDate.getGregorianCalendar(), 3))) {
//				if(today.plus(1).isAfter(shipment.getReleaseDate().getTime()) && randomGenerator.generate(1, 3) == 1){
				if(randomGenerator.generate(1, 3) == 1){
					refreshShipment(shipment);
					count++;
				}
			}else{ 
				refreshShipment(shipment);
				count++;
			}
		}
		shipmentDao.bulk(shipmentList);
		return count;
	}


	private Shipment refreshShipment(Shipment shipment) {
		shipment.setDeliver(true);
		shipment.setDeliverDate(myDate.getGregorianCalendar().getTime());
		return shipment;
	}

}
