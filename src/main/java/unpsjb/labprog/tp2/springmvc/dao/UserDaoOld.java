package unpsjb.labprog.tp2.springmvc.dao;

import java.util.List;

import unpsjb.labprog.tp2.springmvc.model.AppUser;



public interface UserDaoOld {
	
	AppUser findById(long id);
	
	AppUser findByName(String name);
	
	void saveUser(AppUser user);
	
	void updateUser(AppUser user);
	
	void deleteUserById(long id);

	List<AppUser> findAllUsers(); 
	
	void deleteAllUsers();
	
	public boolean isUserExist(AppUser user);	
}
