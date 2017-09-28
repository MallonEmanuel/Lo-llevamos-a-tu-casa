package unpsjb.labprog.tp2.springmvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import unpsjb.labprog.tp2.springmvc.dao.ArticleDao;
import unpsjb.labprog.tp2.springmvc.dao.ShipmentProviderDao;
import unpsjb.labprog.tp2.springmvc.model.ShipmentProvider;
import unpsjb.labprog.tp2.springmvc.util.FilterProvider;

@Service
@RestController
public class ShipmentProviderRestController {

	@Autowired
	ShipmentProviderDao shipmentProviderDao; 
	
	@Autowired
	ArticleDao articleDao;

	// -------------------Retrieve All
	// Users--------------------------------------------------------

	@RequestMapping(value = "/shipmentProviders/", method = RequestMethod.GET)
	public ResponseEntity<List<ShipmentProvider>> listAll() {
		List<ShipmentProvider> shipmentProviders = shipmentProviderDao.findAll();
		if (shipmentProviders.isEmpty()) {
			return new ResponseEntity<List<ShipmentProvider>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<ShipmentProvider>>(shipmentProviders, HttpStatus.OK);
	}

	

	/**
	 * Retorna un remito de proveedor a travez de su id 
	 */
	@RequestMapping(value = "/shipmentProvider/{id}", method = RequestMethod.GET)
	public ResponseEntity<ShipmentProvider> findById(@PathVariable("id") long id) {
		ShipmentProvider shipmentProvider = shipmentProviderDao.find(id);
		if (shipmentProvider == null) {
			return new ResponseEntity<ShipmentProvider>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<ShipmentProvider>(shipmentProvider, HttpStatus.OK);
	}
	
	/**
	 * Persiste un remito de proveedor en la base de datos si no existe 
	 */
	@RequestMapping(value = "/shipmentProvider/", method = RequestMethod.POST)
	public ResponseEntity<Long> create(@RequestBody ShipmentProvider shipmentProvider, UriComponentsBuilder ucBuilder) {
		shipmentProvider.setActive(true);
		System.out.println("Creating ShipmentProvider " + shipmentProvider);

//		if (shipmentProviderDao.exists(shipmentProvider)){
//			System.out.println("A ShipmentProvider with name " + shipmentProvider.getId() + " already exist");
//			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
//		}

		shipmentProvider.setId(shipmentProviderDao.save(shipmentProvider));

		System.out.println("Created ShipmentProvider " + shipmentProvider);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/shipmentProvider/{id}").buildAndExpand(shipmentProvider.getId()).toUri());
		return new ResponseEntity<Long>(shipmentProvider.getId(), HttpStatus.CREATED);
	}

	/**
	 * Modifica un cliente
	 */
	   @RequestMapping(value = "/shipmentProvider/{id}", method = RequestMethod.PUT)
	    public ResponseEntity<ShipmentProvider> update(@PathVariable("id") long id, @RequestBody ShipmentProvider shipmentProvider) {
	        System.out.println("Updating ShipmentProvider " + id);
	         
	        ShipmentProvider currentShipmentProvider = shipmentProviderDao.find(id);
	         
	        if (currentShipmentProvider== null) {
	            System.out.println("ShipmentProvider with id " + id + " not found");
	            return new ResponseEntity<ShipmentProvider>(HttpStatus.NOT_FOUND);
	        }
	 
	        currentShipmentProvider.setProvider(shipmentProvider.getProvider());
	        currentShipmentProvider.setDeliveryDate(shipmentProvider.getDeliveryDate());
	        
	        shipmentProviderDao.update(currentShipmentProvider);
	        
	        return new ResponseEntity<ShipmentProvider>(currentShipmentProvider, HttpStatus.OK);
	    }
	 


		/**
		 * Filtra ShipmentProviders
		 */
		   @RequestMapping(value = "shipmentProviders/filterAll/{id}", method = RequestMethod.PUT)
		    public ResponseEntity<List<ShipmentProvider>> filterAll(@PathVariable("id") long id, @RequestBody FilterProvider filterProvider) {
			   System.out.println("proband");
		        System.out.println("Filter Provider : "+filterProvider);
		         
		        List<ShipmentProvider> shipmentProviders = shipmentProviderDao.filter(filterProvider);
		        
		        return new ResponseEntity<List<ShipmentProvider>>(shipmentProviders, HttpStatus.OK);
		    }

	   
	
}
