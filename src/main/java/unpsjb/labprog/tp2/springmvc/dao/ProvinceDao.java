package unpsjb.labprog.tp2.springmvc.dao;

import unpsjb.labprog.tp2.springmvc.model.Province;

public interface ProvinceDao extends GenericDao<Province, Long>{
	public Province findByName(String name);
	public void deleteIfExists(String name);
	
}
