package unpsjb.labprog.tp2.springmvc.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import unpsjb.labprog.tp2.springmvc.model.Customer;

@Repository
public class CustomerDaoImp extends GenericDaoSpringHibernateTemplate<Customer, Long> implements CustomerDao {

	public CustomerDaoImp() {
		super(Customer.class);
	}

	@Override
	public Customer findByName(String name) {
		String[] params = {"name"};
		Object[] values = {name};
		List<Customer> list = super.findByNameQuery("Customer.findByName", params, values);
		return list.isEmpty()? null: list.get(0);
	}


	@Override
	public List<Customer> filterByName(String name) {
		String[] params = {"name"};
		Object[] values = {name};
		List<Customer> list = super.findByNameQuery("Customer.filterByName", params, values);
		return list;
	}

	@Override
	public List<Customer> filter(String name,String surname,String cuit) {
		String[] params = {"name","surname","cuit"};
		Object[] values = {name,surname,cuit};
		List<Customer> list = super.findByNameQuery("Customer.filter", params, values);
		return list;
	}

	
	
	@Transactional
	@Override
	public void deleteIfExists(String name) {
		Customer customer = findByName(name);
		if(customer != null){
			super.delete(customer);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> findByRandom(int limit) {
		Session session = getHibernateTemplate().getSessionFactory().openSession(); 
		Query query = session.createQuery("SELECT e FROM Customer e ORDER BY RANDOM()");
		query.setMaxResults(limit);
		List<Object> list = query.list();
		session.close();
		return list;
	}
}
