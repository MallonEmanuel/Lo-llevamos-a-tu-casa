package unpsjb.labprog.tp2.springmvc.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import unpsjb.labprog.tp2.springmvc.model.City;

@Repository
//@Transactional
public class CityDaoImp extends GenericDaoSpringHibernateTemplate<City, Long> implements CityDao{
		
	public CityDaoImp() {
		super(City.class);
	}
	@Override
	public City findByName(String name) {
		String[] params = {"name"};
		Object[] values = {name};
		List<City> list = super.findByNameQuery("City.findByName", params, values);
		return list.isEmpty()? null: list.get(0);
	}
	@Transactional
	@Override
	public void deleteIfExists(String name) {
		City city = findByName(name);
		if (city != null)
			delete(city);
	}
	@Override
	public List<City> findByProvinceId(long provinceId){
		String[] params = {"province_id"};
		Object[] values = {provinceId};
		return (List<City>) super.findByNameQuery("City.findByIdProvince", params, values);
	}
}
