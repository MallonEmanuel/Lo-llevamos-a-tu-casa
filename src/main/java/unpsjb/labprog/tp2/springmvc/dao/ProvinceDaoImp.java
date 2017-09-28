package unpsjb.labprog.tp2.springmvc.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import unpsjb.labprog.tp2.springmvc.model.Province;


@Repository
public class ProvinceDaoImp extends GenericDaoSpringHibernateTemplate<Province, Long> implements ProvinceDao{
	
	public ProvinceDaoImp() {
		super(Province.class);
	}
	@Override
	public Province findByName(String name) {
		String[] params = {"name"};
		Object[] values = {name};
		List<Province> list = super.findByNameQuery("Province.findByName", params, values);
		return list.isEmpty()? null: list.get(0);
		// TODO Comprobar que el new State(name,null) funciona!!
	}
	@Transactional
	@Override
	public void deleteIfExists(String name) {
		Province province = findByName(name);
		if (province != null)
			delete(province);	
	}
	
}
