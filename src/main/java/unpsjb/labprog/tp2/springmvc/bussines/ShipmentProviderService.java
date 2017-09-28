package unpsjb.labprog.tp2.springmvc.bussines;

import java.util.Date;
import java.util.GregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import unpsjb.labprog.tp2.springmvc.dao.ProviderDao;
import unpsjb.labprog.tp2.springmvc.dao.ShipmentProviderDao;
import unpsjb.labprog.tp2.springmvc.model.Provider;
import unpsjb.labprog.tp2.springmvc.model.ShipmentProvider;

@Component("shipment_provider_single")
public class ShipmentProviderService {

	private ShipmentProvider shipmentProvider = null;
	private GregorianCalendar gregorianCalendar = null;
	
	private ItemRandom providerRandom;
	
	@Autowired
	ShipmentProviderDao shipmentProviderDao;
	@Autowired
	ProviderDao providerDao;
	
	
	public ShipmentProviderService() {
	}
	public void init(){
		this.providerRandom = new ItemRandom(providerDao, 10);
	}
	
	/**
	 * Obtiene el Remito con el que se esta trabajando, en caso que el domicilio
	 * cambie, se genera un nuevo remito
	 */
	public ShipmentProvider getShipmentPovider(GregorianCalendar today) {

		if (!today.equals(gregorianCalendar) || shipmentProvider == null) {
			shipmentProvider = generateShipmentProvider((Provider) providerRandom.getItem(), today.getTime());
			gregorianCalendar = (GregorianCalendar) today.clone();
		}
		return shipmentProvider;
	}

	/**
	 * Este metodo genera un nuevo remito, asignando una direccion y una fech de
	 * armado
	 */
	public ShipmentProvider generateShipmentProvider(Provider provider,Date date) {
		ShipmentProvider shipmentProvider = new ShipmentProvider(provider, date);
		shipmentProvider.setId(shipmentProviderDao.save(shipmentProvider));
		return shipmentProvider;
	}

}
