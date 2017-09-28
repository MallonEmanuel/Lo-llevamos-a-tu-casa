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

import unpsjb.labprog.tp2.springmvc.dao.CustomerDao;
import unpsjb.labprog.tp2.springmvc.model.Customer;

@Service
@RestController
public class CustomerRestController {

	@Autowired
	CustomerDao customerDao;
	
	/**
	 * Retorna todos los clientes
	 */
	@RequestMapping(value = "/customers/", method = RequestMethod.GET)
	public ResponseEntity<List<Customer>> listAll() {
		List<Customer> customers = customerDao.findAll();
		if (customers.isEmpty()) {
			return new ResponseEntity<List<Customer>>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<List<Customer>>(customers, HttpStatus.OK);
	}
	
	/**
	 * Retorna un cliente a travez de su id 
	 */
	@RequestMapping(value = "/customer/{id}", method = RequestMethod.GET)
	public ResponseEntity<Customer> findById(@PathVariable("id") long id) {
		Customer customer = (Customer) customerDao.find(id);
		if (customer == null) {
			return new ResponseEntity<Customer>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<Customer>(customer, HttpStatus.OK);
	}

	
	/**
	 * Retorna todos los clientes filtrados por nombre 
	 */
	@RequestMapping(value = "/customers/filterByName/{name}", method = RequestMethod.GET)
	public ResponseEntity<List<Customer>> filterByName(@PathVariable("name") String name) {
		System.out.println(name);
		List<Customer> customers = (List<Customer>) customerDao.filterByName(name);
		if (customers.isEmpty()) {
			return new ResponseEntity<List<Customer>>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<List<Customer>>(customers, HttpStatus.OK);
	}
	/**
	 * Retorna todos los cliente filtrados por nombre,apellido y cuit
	 */
	@RequestMapping(value = "/customers/filter/{name}/{surname}/{cuit}", method = RequestMethod.GET)
	public ResponseEntity<List<Customer>> filter(@PathVariable("name") String name,
			@PathVariable("surname") String surname, @PathVariable("cuit") String cuit) {
		System.out.println(name);
		System.out.println(surname);
		System.out.println(cuit);
		List<Customer> customers = (List<Customer>) customerDao.filter(validate(name), validate(surname),
				validate(cuit));
		if (customers.isEmpty()) {
			return new ResponseEntity<List<Customer>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Customer>>(customers, HttpStatus.OK);
	}
	/**
	 * Persiste un cliente el la base de datos su no existe 
	 */
	@RequestMapping(value = "/customer/", method = RequestMethod.POST)
	public ResponseEntity<Void> create(@RequestBody Customer customer, UriComponentsBuilder ucBuilder) {
		customer.setActive(true);
		System.out.println("Creating Customer " + customer);

		if (customerDao.exists(customer)) {
			System.out.println("A User with name " + customer.getName() + " already exist");
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}

		customerDao.save(customer);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/customer/{id}").buildAndExpand(customer.getId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}
	
	private String validate(String s) {
		if (s.isEmpty() || s == null || s.equals("undefined")) {
			return "TODOS";
		}
		return s;
	}
	/**
	 * Modifica un cliente
	 */
	   @RequestMapping(value = "/customer/{id}", method = RequestMethod.PUT)
	    public ResponseEntity<Customer> update(@PathVariable("id") long id, @RequestBody Customer customer) {
	        System.out.println("Updating Customer " + id);
	         
	        Customer currentCustomer = customerDao.find(id);
	         
	        if (customer == null) {
	            System.out.println("Customer with id " + id + " not found");
	            return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
	        }
	 
	        currentCustomer.setName(customer.getName());
	        currentCustomer.setSurname(customer.getSurname());
	        currentCustomer.setCuit(customer.getCuit());
	        
	        customerDao.update(currentCustomer);
	        return new ResponseEntity<Customer>(currentCustomer, HttpStatus.OK);
	    }
	 
	
	
	
	/**
	 * Borra un cliente de la base de datos
	 */
	@RequestMapping(value = "/customer/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Customer> delete(@PathVariable("id") long id) {
		System.out.println("Fetching & Deleting Customer with id " + id);

		Customer customer = customerDao.find(id);
		if (customer == null) {
			System.out.println("Unable to delete. User with id " + id + " not found");
			return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
		}

		customerDao.delete(customer);
		return new ResponseEntity<Customer>(HttpStatus.NO_CONTENT);
	}
	
	
}
