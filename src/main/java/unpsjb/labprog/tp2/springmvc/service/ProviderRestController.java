package unpsjb.labprog.tp2.springmvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import unpsjb.labprog.tp2.springmvc.dao.ProviderDao;
import unpsjb.labprog.tp2.springmvc.model.Provider;

@Service
@RestController
public class ProviderRestController {
	@Autowired
	ProviderDao providerDao;
	

	/**
	 * Retorna todos los proveedores
	 */
	@RequestMapping(value = "/providers/", method = RequestMethod.GET)
	public ResponseEntity<List<Provider>> listAll() {
		List<Provider> providers = providerDao.findAll();
		if (providers.isEmpty()) {
			return new ResponseEntity<List<Provider>>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<List<Provider>>(providers, HttpStatus.OK);
	}
	
	/**
	 * Retorna un proveedor a travez de su id 
	 */
	@RequestMapping(value = "/provider/{id}", method = RequestMethod.GET)
	public ResponseEntity<Provider> findById(@PathVariable("id") long id) {
		Provider provider = providerDao.find(id);
		if (provider == null) {
			return new ResponseEntity<Provider>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<Provider>(provider, HttpStatus.OK);
	}

	
	/**
	 * Retorna todos los clientes filtrados por nombre 
	 */
	@RequestMapping(value = "/providers/filterByName/{name}", method = RequestMethod.GET)
	public ResponseEntity<List<Provider>> filterByName(@PathVariable("name") String name) {
		System.out.println(name);
		List<Provider> providers = providerDao.filterByName(name);
		if (providers.isEmpty()) {
			return new ResponseEntity<List<Provider>>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<List<Provider>>(providers, HttpStatus.OK);
	}

	
}
