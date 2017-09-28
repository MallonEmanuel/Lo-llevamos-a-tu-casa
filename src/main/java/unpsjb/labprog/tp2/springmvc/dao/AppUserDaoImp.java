package unpsjb.labprog.tp2.springmvc.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import unpsjb.labprog.tp2.springmvc.model.AppUser;


@Repository
//@Transactional
public class AppUserDaoImp extends GenericDaoSpringHibernateTemplate<AppUser, Long> implements AppUserDao {

	public AppUserDaoImp() {
		super(AppUser.class);
	}

	@Override
	public AppUser findByUsername(String username) {
		String[] params = {"username"};
		Object[] values = {username};
		List<AppUser> list = super.findByNameQuery("AppUser.findByName", params, values);
		return list.isEmpty()? null: list.get(0);
	}
	@Transactional
	@Override
	public void deleteIfExists(String username) {
		AppUser appUser = findByUsername(username);
		if (appUser != null)
			delete(appUser);
		
	}
}
