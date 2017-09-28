package unpsjb.labprog.tp2.springmvc.dao;

import unpsjb.labprog.tp2.springmvc.model.Address;

public interface AddressDao extends GenericDao<Address, Long> {

	public Address findByName(String name);
}
