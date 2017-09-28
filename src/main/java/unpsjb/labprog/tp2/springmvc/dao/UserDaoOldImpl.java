package unpsjb.labprog.tp2.springmvc.dao;

import java.util.List;

import org.hibernate.FlushMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import unpsjb.labprog.tp2.springmvc.model.AppUser;

@Repository
@Transactional
public class UserDaoOldImpl implements UserDaoOld {

	@Autowired
	private HibernateTemplate hibernateTemplate;


	@SuppressWarnings("unchecked")
	public AppUser findById(long id) {
		AppUser user = new AppUser();
		user.setId(id);
//		List<AppUser> users = hibernateTemplate.findByExample(user);
		List<AppUser> users = (List<AppUser>) hibernateTemplate.find("select u from AppUser u where u.id="+id);
		System.out.println("Sise ********** "+users.size() + "**** id : "+id);
		for (int i = 0; i < users.size(); i++) {
			System.out.println("user "+i +" : "+users.get(i));
		}
		return users != null ? users.get(0) : null;
	}

	public AppUser findByName(String name) {
		AppUser user = new AppUser();
		user.setUsername(name);
		List<AppUser> users = hibernateTemplate.findByExample(user);
		return users != null ? users.get(0) : null;
	}
	
	public void saveUser(AppUser user) {
		hibernateTemplate.getSessionFactory().getCurrentSession().setFlushMode(FlushMode.AUTO);
		hibernateTemplate.persist(user);
	}

	public void updateUser(AppUser user) {		
		hibernateTemplate.merge(user);
	}

	public void deleteUserById(long id) {
		AppUser user = new AppUser();
		user.setId(id);
		hibernateTemplate.delete(user);
	}
	public List<AppUser> findAllUsers() {
		return hibernateTemplate.loadAll(AppUser.class);
		//return users;
	}

	public void deleteAllUsers() {
		hibernateTemplate.getSessionFactory().getCurrentSession().createQuery("DELETE FROM AppUser").executeUpdate();
	}
	
	public boolean isUserExist(AppUser user) {
		List<AppUser> foundUser = (List<AppUser>) hibernateTemplate.findByExample(user);
		System.out.println(foundUser);
		return foundUser != null && !foundUser.isEmpty();
	}

	
}
