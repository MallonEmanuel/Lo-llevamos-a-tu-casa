package unpsjb.labprog.tp2.springmvc.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import unpsjb.labprog.tp2.springmvc.model.Country;

@Repository
@Transactional
public class CountryDaoImp extends GenericDaoSpringHibernateTemplate<Country, Long> implements CountryDao {

	public CountryDaoImp() {
		super(Country.class);
	}

	@Override
	public Country findByName(String name) {
		String[] params = {"name"};
		Object[] values = {name};
		List<Country> list = super.findByNameQuery("Country.findByName", params, values);
		return list.isEmpty()? null: list.get(0);
	}
	@Transactional
	@Override
	public void deleteIfExists(String name) {
		Country country = findByName(name);
		if (country != null)
			delete(country);
	
	}

}