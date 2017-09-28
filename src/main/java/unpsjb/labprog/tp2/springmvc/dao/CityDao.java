package unpsjb.labprog.tp2.springmvc.dao;

import java.util.List;

import unpsjb.labprog.tp2.springmvc.model.City;

public interface CityDao extends GenericDao<City, Long>{
	public City findByName(String name);

	public void deleteIfExists(String name);

	List<City> findByProvinceId(long provinceId);
}
