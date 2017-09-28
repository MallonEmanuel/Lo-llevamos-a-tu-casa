package unpsjb.labprog.tp2.springmvc.dao;

import unpsjb.labprog.tp2.springmvc.model.AppUser;

public interface AppUserDao extends GenericDao<AppUser, Long>{
	AppUser findByUsername(String username);
	void deleteIfExists(String username); 
}
