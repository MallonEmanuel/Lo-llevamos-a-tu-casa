package unpsjb.labprog.tp2.springmvc.dao;

import java.util.List;

import unpsjb.labprog.tp2.springmvc.model.Provider;

public interface ProviderDao extends GenericDao<Provider, Long>,Finder{

	List<Provider> filterByName(String name);

}
