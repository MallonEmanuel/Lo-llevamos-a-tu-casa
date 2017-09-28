package unpsjb.labprog.tp2.springmvc.dao;

import unpsjb.labprog.tp2.springmvc.model.Country;

public interface CountryDao extends GenericDao<Country, Long>{
	public Country findByName(String name);
	public void deleteIfExists(String name);
}
