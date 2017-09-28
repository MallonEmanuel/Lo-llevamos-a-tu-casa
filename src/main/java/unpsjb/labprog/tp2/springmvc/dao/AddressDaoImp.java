package unpsjb.labprog.tp2.springmvc.dao;

import org.springframework.stereotype.Repository;

import unpsjb.labprog.tp2.springmvc.model.Address;

@Repository
//@Transactional
public class AddressDaoImp extends GenericDaoSpringHibernateTemplate<Address, Long> implements AddressDao {

	public AddressDaoImp() {
		super(Address.class);
	}

	@Override
	public Address findByName(String name) {
//		Address address = new Address(name, null);
		return null;
	}
	

}
