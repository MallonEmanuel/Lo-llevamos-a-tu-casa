package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import unpsjb.labprog.tp2.springmvc.dao.AppUserDao;
import unpsjb.labprog.tp2.springmvc.model.AppUser;

public class JUnitAppUserDao extends JUnitDao {

	
	@Autowired
	protected AppUserDao appUserDao;
	protected int usersCount;

	private String JORGE = "JORGE_";
	
	private String EMANUEL = "EMANUEL_";
	private String EMANUEL_EMAIL = "emanuelsm18@gmail.com";
	@Before
	public void before() {
		usersCount = appUserDao.count();
		appUserDao.save(new AppUser(JORGE));
		appUserDao.save(new AppUser(EMANUEL));
	}

	@After
	public void after() {
		appUserDao.deleteIfExists(EMANUEL);
		appUserDao.deleteIfExists(JORGE);
	}

	@Test
	public void testFindByName() {
		assertTrue(appUserDao.findByUsername(EMANUEL).getUsername().equals(EMANUEL));
	}


	@Test
	public void testSave() {
		AppUser appUser = appUserDao.findByUsername(EMANUEL);
		assertTrue(appUserDao.exists(appUser));
	}

	@Test
	public void testDelete() {
		AppUser appUser = appUserDao.findByUsername(EMANUEL);
		appUserDao.delete(appUser);
		assertNull(appUserDao.findByUsername(EMANUEL));
	}

	@Test
	public void testUpdate() {
		AppUser appUser = appUserDao.findByUsername(EMANUEL);
		long id = appUser.getId();
		appUser.setEmail(EMANUEL_EMAIL);
		appUserDao.update(appUser);
		assertTrue(appUserDao.find(id).getEmail().equals(EMANUEL_EMAIL));
	}

	@Test
	public void testFindAllUser() {
		assertEquals(usersCount + 2, appUserDao.count());
	}
	
}
