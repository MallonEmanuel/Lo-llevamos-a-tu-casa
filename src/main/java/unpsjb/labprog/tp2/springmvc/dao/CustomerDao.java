package unpsjb.labprog.tp2.springmvc.dao;

import java.util.List;

import unpsjb.labprog.tp2.springmvc.model.Customer;

public interface CustomerDao extends GenericDao<Customer, Long>, Finder {

	public Customer findByName(String name);

	public void deleteIfExists(String name);

	public List<Customer> filterByName(String name);

	public List<Customer> filter(String name, String surname, String cuit);

}
