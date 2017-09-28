package unpsjb.labprog.tp2.springmvc.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import unpsjb.labprog.tp2.springmvc.model.Provider;

@Repository
public class ProviderDaoImp extends GenericDaoSpringHibernateTemplate<Provider, Long> implements ProviderDao{

	public ProviderDaoImp() {
		super(Provider.class);
		// TODO Auto-generated constructor stub
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> findByRandom(int limit){
		Session session = getHibernateTemplate().getSessionFactory().openSession(); 
		Query query = session.createQuery("SELECT e FROM Provider e ORDER BY RANDOM()");
		query.setMaxResults(limit);
		List<Object> list = query.list();
		session.close();
		return list;
	}

	@Override
	public List<Provider> filterByName(String name) {
		String[] params = {"name"};
		Object[] values = {name};
		List<Provider> list = super.findByNameQuery("Provider.filterByName", params, values);
		return list;
	}

	
}
